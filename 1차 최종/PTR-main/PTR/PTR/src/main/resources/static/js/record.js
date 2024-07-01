// 여기부터 작성 찾기

const user = {
  userId: "cake"
}

const today = new Date();
const todayYear = today.getFullYear();
const todayMonth = today.getMonth() + 1;
const todayDay = today.getDate();

const calendarEl = document.getElementById("calendar").querySelector("tbody");
let currentYear = todayYear;
let currentMonth = todayMonth;
let selectedDateEl = document.getElementById("selectedDate");
let recordButton = document.getElementById("recordButton");


// 저번달 달력 생성
function prevMonth() {
  currentMonth--;
  if (currentMonth < 1) {
    currentMonth = 12;
    currentYear--;
  }
  updateCalendar();
}
// 다음달 달력 생성
function nextMonth() {
  currentMonth++;
  if (currentMonth > 12) {
    currentMonth = 1;
    currentYear++;
  }
  updateCalendar();
}

// 달력 안의 내용 생성
function updateCalendar() {
  document.querySelector(
    ".content_date_year"
  ).textContent = `${currentYear}.`;
  document.querySelector(".content_date_month").textContent = currentMonth
    .toString()
    .padStart(2, "0");
  renderCalendar(currentYear, currentMonth);
  // fetchData(currentYear, currentMonth); 아직없음

  const chart_weight = document.querySelector(".chart_weight")
  chart_weight.textContent = currentYear + "년 " + currentMonth + "월 몸무게 변화";
  createChartDate()
}

// 달력 안의 내용 생성
function renderCalendar(year, month) {
  calendarEl.innerHTML = "";
  const firstDay = new Date(year, month - 1, 1).getDay();
  const lastDate = new Date(year, month, 0).getDate();

  let date = 1;
  for (let i = 0; i < 6; i++) {
    const row = document.createElement("tr");
    for (let j = 0; j < 7; j++) {
      const cell = document.createElement("td");
      if (i === 0 && j < firstDay) {
        cell.innerHTML = "";
      } else if (date > lastDate) {
        cell.innerHTML = "";
      } else {
        cell.innerHTML = date;
        const cellDate = date; // 클로저 문제 해결을 위해 별도 변수 사용
        cell.addEventListener("click", function () {
          selectDate(cell, year, month, cellDate);
        });
        date++;
      }
      row.appendChild(cell);
    }
    calendarEl.appendChild(row);
  }
}
// 달력 안의 내용 검색
function selectDate(cell, year, month, date) {
  clearSelectedDate();
  cell.classList.add("selected");
  recordButton.style.display = "block";
  cell.appendChild(recordButton);
  recordButton.onclick = function () {
    window.location.href = `management.html?year=${year}&month=${month}&date=${date}`;
  };
}
// 달력 안의 내용 삭제
function clearSelectedDate() {
  const selectedCells = document.querySelectorAll("#calendar td.selected");
  selectedCells.forEach((cell) => cell.classList.remove("selected"));
  selectedDateEl.innerText = "";
  recordButton.style.display = "none";
}




function createChartDate(){
  let date = ""

  if(currentMonth<10){
    date = `${currentYear}-0${currentMonth}-01`
  }else{
    date = `${currentYear}-${currentMonth}-01`
  }
  
  console.log(date)
  axios
  .post("http://localhost:8080/findCalenderMonth", {date:date, user})
  .then((response)=>{
    console.log("이번달 캘린더 정보: ", response.data)
    createChartWeight(response.data)

    // 칼로리 차트
    axios
    .post("http://localhost:8080/findCalendarDietPlanByCalendars", response.data)
    .then((calendarDietPlan)=>{
      console.log("calendarDietPlan: ", calendarDietPlan.data)
      axios
      .post("http://localhost:8080/findCalendarDietRecordByCalendars", response.data)
      .then((calendarDietRecord)=>{
        console.log("이번달 캘린더 정보: ", calendarDietRecord.data)
        createChartDiet(calendarDietPlan.data, calendarDietRecord.data)
      })
      .catch((error)=>{
        console.log("에러: ", error)
      })      
    })
    .catch((error)=>{
      console.log("에러: ", error)
    })    

    // 운동 차트
    axios
    .post("http://localhost:8080/findCalendarExercisePlanByCalendars", response.data)
    .then((calendarExercisePlan)=>{
      console.log("calendarExercisePlan ", calendarExercisePlan.data)
      axios
      .post("http://localhost:8080/findCalendarExerciseRecordByCalendars", response.data)
      .then((calendarExerciseRecord)=>{
        console.log("이번달 캘린더 정보: ", calendarExerciseRecord.data)
        createChartExercise(calendarExercisePlan.data, calendarExerciseRecord.data)
      })
      .catch((error)=>{
        console.log("에러: ", error)
      })      
    })
    .catch((error)=>{
      console.log("에러: ", error)
    })


  })
  .catch((error)=>{
    console.log("에러: ", error)
  })
}

let myBarChartWeight = null; // 전역 변수로 차트를 저장할 변수 선언
let myBarChartDiet = null;
let myBarChartExercise = null;

function createChartWeight(calendar){
  const lastDate = new Date(currentYear, currentMonth, 0).getDate();
  let days = [];
  for(let i=1; i<=lastDate; i++){
    days.push(`${i}일`)
  }
  console.log(days)


  let minY = 1000;
  let maxY = 0;

  let weightGoals = [];
  let weightRecords = [];


  for (let i = 0; i < lastDate; i++) {
    weightGoals.push(null)
    weightRecords.push(null)
  }

  calendar.forEach((calendar)=>{
    if(calendar.weightGoal !== -1){
      const date = new Date(calendar.date);
      const index = date.getDate() -1
      weightGoals[index] = calendar.weightGoal;
      if(calendar.weightGoal<minY){
        minY = calendar.weightGoal
      }
      if(calendar.weightGoal>maxY){
        maxY = calendar.weightGoal
      }
    }
    if(calendar.weightRecord !== -1){
      const date = new Date(calendar.date);
      const index = date.getDate() -1
      weightRecords[index] = calendar.weightRecord;
      if(calendar.weightRecord<minY){
        minY = calendar.weightRecord
      }
      if(calendar.weightRecord>maxY){
        maxY = calendar.weightRecord
      }
    }
  })

  const weightData = {
    labels: days,
    datasets: [
      {
        label: '목표 몸무게(kg)',
        data: weightGoals,
        fill: false,
        borderColor: 'rgb(254, 192, 9)',
        tension: 0
      },
      {
        label: '달성 몸무게(kg)',
        data: weightRecords,
        fill: false,
        borderColor: 'rgb(44, 57, 74)',
        tension: 0
      }
    ]
  };
  
  // 기존 차트가 있으면 파괴
  if (myBarChartWeight) {
    myBarChartWeight.destroy();
  }

  const barChartweight = document.getElementById("barChartweight").getContext("2d");
  myBarChartWeight = new Chart(barChartweight, {
    type: 'line',
    data: weightData,
    options: {
      scales: {
        y: {
          beginAtZero: false, // Y축이 0부터 시작하지 않도록 설정
          min: minY -1,
          max: maxY +1,
          ticks: {
            stepSize: 0.5 // Y축 눈금 간격 설정
          }  
        }
      }
    }
  });






  // new Chart(barChartweight, {
  //   type: "line",
  //   data: {
  //     labels: days,
  //     datasets: [
  //       {
  //         label: "섭취량",
  //         data: [7, 8, 9, 10],
  //         backgroundColor: "rgba(254, 192, 9, 1)", // 막대 색상을 불투명하게 설정
  //         borderColor: "rgba(254, 192, 9, 1)",
  //         borderWidth: 1,
  //         pointRadius: 0,
  //         pointHoverRadius: 0,
  //       },
  //     ],
  //   },
  //   options: {
  //     responsive: true,
  //     maintainAspectRatio: false,
  //     scales: {
  //       y: {
  //         beginAtZero: true,
  //         min: 0,
  //         max: 1000,
  //         stepSize: 20,
  //         grid: {
  //           display: false, // y축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: false, // y축의 숫자를 표시하지 않음
  //           callback: function (value) {
  //             return value + "kcal";
  //           },
  //         },
  //       },
  //       x: {
  //         grid: {
  //           display: false, // x축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: true, // x축의 숫자를 표시하지 않음
  //         },
  //       },
  //     },
  //     plugins: {
  //       legend: {
  //         display: false, // 범례를 표시하지 않음
  //       },
  //     },
  //   },
  // });
}

function createChartDiet(dietPlans, dietRecords){
  console.log("캘린더", calendar)
  const lastDate = new Date(currentYear, currentMonth, 0).getDate();
  let days = [];
  for(let i=1; i<=lastDate; i++){
    days.push(`${i}일`)
  }
  console.log(days)


  let minY = 1000;
  let maxY = 0;

  let weightGoals = [];
  let weightRecords = [];


  for (let i = 0; i < lastDate; i++) {
    weightGoals.push(null)
    weightRecords.push(null)
  }

  calendar.forEach((calendar)=>{
    if(calendar.weightGoal !== -1){
      const date = new Date(calendar.date);
      const index = date.getDate() -1
      weightGoals[index] = calendar.weightGoal;
      if(calendar.weightGoal<minY){
        minY = calendar.weightGoal
      }
      if(calendar.weightGoal>maxY){
        maxY = calendar.weightGoal
      }
    }
    if(calendar.weightRecord !== -1){
      const date = new Date(calendar.date);
      const index = date.getDate() -1
      weightRecords[index] = calendar.weightRecord;
      if(calendar.weightRecord<minY){
        minY = calendar.weightRecord
      }
      if(calendar.weightRecord>maxY){
        maxY = calendar.weightRecord
      }
    }
  })

  const weightData = {
    labels: days,
    datasets: [
      {
        label: '목표 몸무게(kg)',
        data: weightGoals,
        fill: false,
        borderColor: 'rgb(254, 192, 9)',
        tension: 0
      },
      {
        label: '달성 몸무게(kg)',
        data: weightRecords,
        fill: false,
        borderColor: 'rgb(44, 57, 74)',
        tension: 0
      }
    ]
  };
  
  // 기존 차트가 있으면 파괴
  if (myBarChartWeight) {
    myBarChartWeight.destroy();
  }

  const barChartweight = document.getElementById("barChartweight").getContext("2d");
  myBarChartWeight = new Chart(barChartweight, {
    type: 'line',
    data: weightData,
    options: {
      scales: {
        y: {
          beginAtZero: false, // Y축이 0부터 시작하지 않도록 설정
          min: minY -1,
          max: maxY +1,
          ticks: {
            stepSize: 0.5 // Y축 눈금 간격 설정
          }  
        }
      }
    }
  });






  // new Chart(barChartweight, {
  //   type: "line",
  //   data: {
  //     labels: days,
  //     datasets: [
  //       {
  //         label: "섭취량",
  //         data: [7, 8, 9, 10],
  //         backgroundColor: "rgba(254, 192, 9, 1)", // 막대 색상을 불투명하게 설정
  //         borderColor: "rgba(254, 192, 9, 1)",
  //         borderWidth: 1,
  //         pointRadius: 0,
  //         pointHoverRadius: 0,
  //       },
  //     ],
  //   },
  //   options: {
  //     responsive: true,
  //     maintainAspectRatio: false,
  //     scales: {
  //       y: {
  //         beginAtZero: true,
  //         min: 0,
  //         max: 1000,
  //         stepSize: 20,
  //         grid: {
  //           display: false, // y축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: false, // y축의 숫자를 표시하지 않음
  //           callback: function (value) {
  //             return value + "kcal";
  //           },
  //         },
  //       },
  //       x: {
  //         grid: {
  //           display: false, // x축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: true, // x축의 숫자를 표시하지 않음
  //         },
  //       },
  //     },
  //     plugins: {
  //       legend: {
  //         display: false, // 범례를 표시하지 않음
  //       },
  //     },
  //   },
  // });
}

function createChartExercise(exercisePlans, exerciseRecords){
  console.log("캘린더", calendar)
  const lastDate = new Date(currentYear, currentMonth, 0).getDate();
  let days = [];
  for(let i=1; i<=lastDate; i++){
    days.push(`${i}일`)
  }
  console.log(days)


  let minY = 1000;
  let maxY = 0;

  let weightGoals = [];
  let weightRecords = [];


  for (let i = 0; i < lastDate; i++) {
    weightGoals.push(null)
    weightRecords.push(null)
  }

  calendar.forEach((calendar)=>{
    if(calendar.weightGoal !== -1){
      const date = new Date(calendar.date);
      const index = date.getDate() -1
      weightGoals[index] = calendar.weightGoal;
      if(calendar.weightGoal<minY){
        minY = calendar.weightGoal
      }
      if(calendar.weightGoal>maxY){
        maxY = calendar.weightGoal
      }
    }
    if(calendar.weightRecord !== -1){
      const date = new Date(calendar.date);
      const index = date.getDate() -1
      weightRecords[index] = calendar.weightRecord;
      if(calendar.weightRecord<minY){
        minY = calendar.weightRecord
      }
      if(calendar.weightRecord>maxY){
        maxY = calendar.weightRecord
      }
    }
  })

  const weightData = {
    labels: days,
    datasets: [
      {
        label: '목표 몸무게(kg)',
        data: weightGoals,
        fill: false,
        borderColor: 'rgb(254, 192, 9)',
        tension: 0
      },
      {
        label: '달성 몸무게(kg)',
        data: weightRecords,
        fill: false,
        borderColor: 'rgb(44, 57, 74)',
        tension: 0
      }
    ]
  };
  
  // 기존 차트가 있으면 파괴
  if (myBarChartWeight) {
    myBarChartWeight.destroy();
  }

  const barChartweight = document.getElementById("barChartweight").getContext("2d");
  myBarChartWeight = new Chart(barChartweight, {
    type: 'line',
    data: weightData,
    options: {
      scales: {
        y: {
          beginAtZero: false, // Y축이 0부터 시작하지 않도록 설정
          min: minY -1,
          max: maxY +1,
          ticks: {
            stepSize: 0.5 // Y축 눈금 간격 설정
          }  
        }
      }
    }
  });






  // new Chart(barChartweight, {
  //   type: "line",
  //   data: {
  //     labels: days,
  //     datasets: [
  //       {
  //         label: "섭취량",
  //         data: [7, 8, 9, 10],
  //         backgroundColor: "rgba(254, 192, 9, 1)", // 막대 색상을 불투명하게 설정
  //         borderColor: "rgba(254, 192, 9, 1)",
  //         borderWidth: 1,
  //         pointRadius: 0,
  //         pointHoverRadius: 0,
  //       },
  //     ],
  //   },
  //   options: {
  //     responsive: true,
  //     maintainAspectRatio: false,
  //     scales: {
  //       y: {
  //         beginAtZero: true,
  //         min: 0,
  //         max: 1000,
  //         stepSize: 20,
  //         grid: {
  //           display: false, // y축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: false, // y축의 숫자를 표시하지 않음
  //           callback: function (value) {
  //             return value + "kcal";
  //           },
  //         },
  //       },
  //       x: {
  //         grid: {
  //           display: false, // x축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: true, // x축의 숫자를 표시하지 않음
  //         },
  //       },
  //     },
  //     plugins: {
  //       legend: {
  //         display: false, // 범례를 표시하지 않음
  //       },
  //     },
  //   },
  // });
}






// document.addEventListener("DOMContentLoaded", function () {
  


  // 서버에서 데이터를 가져오는 함수
  // async function fetchData(year, month) {
  //   try {
  //     const [dietResponse, exerciseResponse] = await Promise.all([
  //       axios.get(`http://localhost:8080/dietData`, {
  //         params: {
  //           year: year,
  //           month: month,
  //         },
  //       }),
  //       axios.get(`http://localhost:8080/exerciseData`, {
  //         params: {
  //           year: year,
  //           month: month,
  //         },
  //       }),
  //     ]);

  //     const dietData = dietResponse.data;
  //     const exerciseData = exerciseResponse.data;
  //     updateCharts(dietData, exerciseData);
  //   } catch (error) {
  //     console.error("Error fetching data:", error);
  //   }
  // }

  // 차트를 업데이트하는 함수
  function updateCharts(data) {
    intakeChart.data.datasets[0].data = data.intake;
    exerciseChart.data.datasets[0].data = data.exercise;
    intakeChart.update();
    exerciseChart.update();
  }

  // Chart.js 섭취량 막대 그래프 생성
  // const barCtxIntake = document.getElementById("barChartIntake").getContext("2d");
  // new Chart(barCtxIntake, {
  //   type: "bar",
  //   data: {
  //     labels: ["1주", "2주", "3주", "4주", "5주"],
  //     datasets: [
  //       {
  //         label: "섭취량",
  //         data: [],
  //         backgroundColor: "rgba(254, 192, 9, 1)", // 막대 색상을 불투명하게 설정
  //         borderColor: "rgba(254, 192, 9, 1)",
  //         borderWidth: 1,
  //         pointRadius: 0,
  //         pointHoverRadius: 0,
  //       },
  //     ],
  //   },
  //   options: {
  //     responsive: true,
  //     maintainAspectRatio: false,
  //     scales: {
  //       y: {
  //         beginAtZero: true,
  //         min: 0,
  //         max: 1000,
  //         stepSize: 20,
  //         grid: {
  //           display: false, // y축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: false, // y축의 숫자를 표시하지 않음
  //           callback: function (value) {
  //             return value + "kcal";
  //           },
  //         },
  //       },
  //       x: {
  //         grid: {
  //           display: false, // x축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: true, // x축의 숫자를 표시하지 않음
  //         },
  //       },
  //     },
  //     plugins: {
  //       legend: {
  //         display: false, // 범례를 표시하지 않음
  //       },
  //     },
  //   },
  // });

  // // Chart.js 운동량 막대 그래프 생성
  // const barCtxExercise = document
  //   .getElementById("barChartExercise")
  //   .getContext("2d");
  // new Chart(barCtxExercise, {
  //   type: "bar",
  //   data: {
  //     labels: ["1주", "2주", "3주", "4주", "5주"],
  //     datasets: [
  //       {
  //         label: "운동량",
  //         data: [],
  //         backgroundColor: "rgba(75, 192, 192, 1)", // 막대 색상을 불투명하게 설정
  //         borderColor: "rgba(75, 192, 192, 1)",
  //         borderWidth: 1,
  //         pointRadius: 0,
  //         pointHoverRadius: 0,
  //       },
  //     ],
  //   },
  //   options: {
  //     responsive: true,
  //     maintainAspectRatio: false,
  //     scales: {
  //       y: {
  //         beginAtZero: true,
  //         min: 0,
  //         max: 1000,
  //         stepSize: 20,
  //         grid: {
  //           display: false, // y축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: false, // y축의 숫자를 표시하지 않음
  //           callback: function (value) {
  //             return value + "kcal";
  //           },
  //         },
  //       },
  //       x: {
  //         grid: {
  //           display: false, // x축의 그리드를 표시하지 않음
  //         },
  //         ticks: {
  //           display: true, // x축의 숫자를 표시하지 않음
  //         },
  //       },
  //     },
  //     plugins: {
  //       legend: {
  //         display: false, // 범례를 표시하지 않음
  //       },
  //     },
  //   },
  // });
// });






// 달력 안의 내용 생성
updateCalendar();
// renderComments(); 아직 없음
window.prevMonth = prevMonth;
window.nextMonth = nextMonth;