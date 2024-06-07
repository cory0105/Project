package PTR.PTR.controller;

import PTR.PTR.model.Status;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.Training;
import PTR.PTR.model.User;
import PTR.PTR.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingController {
    TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("callTraining")
    public ResponseEntity<String> callTraining(@RequestBody Training training){
        return new ResponseEntity<>(trainingService.callTraining(training), HttpStatus.OK);
    }
    @PostMapping("lookCallTraining")
    public ResponseEntity<String> lookCallTraining(@RequestBody Training training){
        return new ResponseEntity<>(trainingService.lookCallTraining(training),HttpStatus.OK);
    }
    @PostMapping("yesTraining")
    public ResponseEntity<String> yesTraining(@RequestBody Training training){
        return new ResponseEntity<>(trainingService.yesTraining(training),HttpStatus.OK);
    }
    @PostMapping("noTraining")
    public ResponseEntity<String> noTraining(@RequestBody Training training){
        return new ResponseEntity<>(trainingService.noTraining(training),HttpStatus.OK);
    }
    @PostMapping("callStudents")
    public ResponseEntity<List<Training>> callStudents(@RequestBody Teacher teacher){
        return new ResponseEntity<>(trainingService.callStudents(teacher),HttpStatus.OK);
    }
    @PostMapping("lookCallStudents")
    public ResponseEntity<List<Training>> lookCallStudents(@RequestBody Teacher teacher){
        return new ResponseEntity<>(trainingService.lookCallStudents(teacher),HttpStatus.OK);
    }
    @PostMapping("myStudents")
    public ResponseEntity<List<Training>> myStudents(@RequestBody Teacher teacher){
        return new ResponseEntity<>(trainingService.myStudents(teacher),HttpStatus.OK);
    }
    @PostMapping("noStudents")
    public ResponseEntity<List<Training>> noStudents(@RequestBody Teacher teacher){
        return new ResponseEntity<>(trainingService.noStudents(teacher),HttpStatus.OK);
    }
}
