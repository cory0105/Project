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
  const chart_intake = document.querySelector(".chart_intake")
  chart_intake.textContent = currentYear + "년 " + currentMonth + "월 칼로리 섭취량(kcal)";
  const chart_exercise = document.querySelector(".chart_exercise")
  chart_exercise.textContent = currentYear + "년 " + currentMonth + "월 운동량(분)";
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
    const responses = response.data;
    console.log("이번달 캘린더 정보: ", responses)
    createChartWeight(response.data)

    
    
    const calendars = responses.map((responses) => {
      return { id: responses.id };
    });
    
    console.log(calendars);

    // 칼로리 차트
    axios
    .post("http://localhost:8080/findCalendarDietPlanByCalendars", calendars)
    .then((calendarDietPlan)=>{
      console.log("calendarDietPlan: ", calendarDietPlan.data)
      axios
      .post("http://localhost:8080/findCalendarDietRecordByCalendars", calendars)
      .then((calendarDietRecord)=>{
        console.log("calendarDietRecord", calendarDietRecord.data)
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
    .post("http://localhost:8080/findCalendarExercisePlanByCalendars", calendars)
    .then((calendarExercisePlan)=>{
      console.log("calendarExercisePlan ", calendarExercisePlan.data)
      axios
      .post("http://localhost:8080/findCalendarExerciseRecordByCalendars", calendars)
      .then((calendarExerciseRecord)=>{
        console.log("calendarExerciseRecord", calendarExerciseRecord.data)
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
let myBarChartIntake = null;
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
  console.log("dietPlans", dietPlans)
  console.log("dietRecords", dietRecords)
  const lastDate = new Date(currentYear, currentMonth, 0).getDate();
  let days = [];
  for(let i=1; i<=lastDate; i++){
    days.push(`${i}일`)
  }

  let carbohydratePlan = [];
  let proteinPlan = [];
  let fatPlan = [];
  let carbohydrateRecord = [];
  let proteinRecord = [];
  let fatRecord = [];


  for (let i = 0; i < lastDate; i++) {
    carbohydratePlan.push(null)
    proteinPlan.push(null)
    fatPlan.push(null)
    carbohydrateRecord.push(null)
    proteinRecord.push(null)
    fatRecord.push(null)
  }

  dietPlans.forEach((data)=>{
    const date = new Date(data.calendar.date);
    const index = date.getDate() -1

    if(data.carbohydrate !== -1){
      carbohydratePlan[index] = data.carbohydrate;
    }
    
    if(data.protein !== -1){
      proteinPlan[index] = data.protein;
    }

    if(data.fat !== -1){
      fatPlan[index] = data.fat;
    }
  })

  dietRecords.forEach((data)=>{
    const date = new Date(data.calendar.date);
    const index = date.getDate() -1

    if(data.carbohydrate !== -1){
      carbohydrateRecord[index] = data.carbohydrate;
    }
    
    if(data.protein !== -1){
      proteinRecord[index] = data.protein;
    }

    if(data.fat !== -1){
      fatRecord[index] = data.fat;
    }
  })

  const chartData = {
    labels: days,
    datasets: [
      {
        label: '달성 탄수화물',
        data: carbohydrateRecord,
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        stack: 'Stack 0',
        type: 'bar'
      },
      {
          label: '달성 단백질',
          data: proteinRecord,
          backgroundColor: 'rgba(54, 162, 235, 0.5)',
          stack: 'Stack 0',
          type: 'bar'
      },
      {
          label: '달성 지방',
          data: fatRecord,
          backgroundColor: 'rgba(255, 206, 86, 0.5)',
          stack: 'Stack 0',
          type: 'bar'
      },
      {
          label: '목표 탄수화물',
          data: carbohydratePlan,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      },
      {
          label: '목표 단백질',
          data: proteinPlan,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      },
      {
          label: '목표 지방',
          data: fatPlan,
          backgroundColor: 'rgba(255, 206, 86, 0.2)',
          borderColor: 'rgba(255, 206, 86, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      }
    ]
  };
  
  // 기존 차트가 있으면 파괴
  if (myBarChartIntake) {
    myBarChartIntake.destroy();
  }

  const barChartIntake = document.getElementById("barChartIntake").getContext("2d");
  myBarChartIntake = new Chart(barChartIntake, {
    type: 'bar',
    data: chartData,
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'top',
        }
      },
      scales: {
        x: {
          stacked: true,
        },
        y: {
          stacked: true,
          beginAtZero: true
        }
      }
    }
  });
}

function createChartExercise(exercisePlans, exerciseRecords){
  console.log("exercisePlans", exercisePlans)
  console.log("exerciseRecords", exerciseRecords)
  const lastDate = new Date(currentYear, currentMonth, 0).getDate();
  let days = [];
  for(let i=1; i<=lastDate; i++){
    days.push(`${i}일`)
  }
  
  let aerobicPlan = [];
  let musclePlan = [];
  let stretchPlan = [];
  let yogaPlan = [];
  let aerobicRecord = [];
  let muscleRecord = [];
  let stretchRecord = [];
  let yogaRecord = [];


  for (let i = 0; i < lastDate; i++) {
    aerobicPlan.push(null)
    musclePlan.push(null)
    stretchPlan.push(null)
    yogaPlan.push(null)
    aerobicRecord.push(null)
    muscleRecord.push(null)
    stretchRecord.push(null)
    yogaRecord.push(null)
  }

  exercisePlans.forEach((data)=>{
    const date = new Date(data.calendar.date);
    const index = date.getDate() -1

    if(data.aerobic !== -1){
      aerobicPlan[index] = data.aerobic;
    }
    
    if(data.muscle !== -1){
      musclePlan[index] = data.muscle;
    }

    if(data.stretch !== -1){
      stretchPlan[index] = data.stretch;
    }

    if(data.yoga !== -1){
      yogaPlan[index] = data.yoga;
    }
  })

  exerciseRecords.forEach((data)=>{
    const date = new Date(data.calendar.date);
    const index = date.getDate() -1

    if(data.aerobic !== -1){
      aerobicRecord[index] = data.aerobic;
    }
    
    if(data.muscle !== -1){
      muscleRecord[index] = data.muscle;
    }

    if(data.stretch !== -1){
      stretchRecord[index] = data.stretch;
    }

    if(data.yoga !== -1){
      yogaRecord[index] = data.yoga;
    }
  })

  const chartData = {
    labels: days,
    datasets: [
      {
        label: '달성 유산소',
        data: aerobicRecord,
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        stack: 'Stack 0',
        type: 'bar'
      },
      {
          label: '달성 근력',
          data: muscleRecord,
          backgroundColor: 'rgba(54, 162, 235, 0.5)',
          stack: 'Stack 0',
          type: 'bar'
      },
      {
          label: '달성 스트레칭',
          data: stretchRecord,
          backgroundColor: 'rgba(255, 206, 86, 0.5)',
          stack: 'Stack 0',
          type: 'bar'
      },
      {
          label: '달성 요가',
          data: yogaRecord,
          backgroundColor: 'rgba(75, 192, 192, 0.5)',
          stack: 'Stack 0',
          type: 'bar'
      },
      {
          label: '목표 유산소',
          data: aerobicPlan,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      },
      {
          label: '목표 근력',
          data: musclePlan,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      },
      {
          label: '목표 스트레칭',
          data: stretchRecord,
          backgroundColor: 'rgba(255, 206, 86, 0.2)',
          borderColor: 'rgba(255, 206, 86, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      },
      {
          label: '목표 요가',
          data: yogaRecord,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1,
          type: 'line', // 영역형 그래프로 설정
          fill: 'origin' // 원점부터 채우기
      }
    ]
  };
  
  // 기존 차트가 있으면 파괴
  if (myBarChartExercise) {
    myBarChartExercise.destroy();
  }

  const barChartExercise = document.getElementById("barChartExercise").getContext("2d");
  myBarChartExercise = new Chart(barChartExercise, {
    type: 'bar',
    data: chartData,
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'top',
        }
      },
      scales: {
        x: {
          stacked: true,
        },
        y: {
          stacked: true,
          beginAtZero: true
        }
      }
    }
  });
}

// 달력 안의 내용 생성
updateCalendar();
window.prevMonth = prevMonth;
window.nextMonth = nextMonth;