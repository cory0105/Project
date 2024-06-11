package PTR.PTR.controller;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureUser;
import PTR.PTR.model.User;
import PTR.PTR.service.LectureUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureUserController {
    LectureUserService lectureUserService;

    public LectureUserController(LectureUserService lectureUserService) {
        this.lectureUserService = lectureUserService;
    }
    @PostMapping("buyLecture")
    public ResponseEntity<String> buyLecture(@RequestBody LectureUser lectureUser){
        return new ResponseEntity<>(lectureUserService.buyLecture(lectureUser), HttpStatus.OK);
    }
    @PostMapping("myBuyLecture")
    public ResponseEntity<List<LectureUser>> myBuyLecture(@RequestBody User user){
        return new ResponseEntity<>(lectureUserService.myBuyLecture(user), HttpStatus.OK);
    }
    @PostMapping("buyNumber")
    public ResponseEntity<Integer> buyNumber(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureUserService.buyNumber(lecture), HttpStatus.OK);
    }
    @PostMapping("changeLectureUser")
    public ResponseEntity<LectureUser> changeLectureUser(@RequestBody LectureUser lectureUser){
        return new ResponseEntity<>(lectureUserService.changeLectureUser(lectureUser), HttpStatus.OK);
    }
}
