package PTR.PTR.controller;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureScrap;
import PTR.PTR.model.User;
import PTR.PTR.service.LectureScrapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LectureScrapController {
    LectureScrapService lectureScrapService;

    public LectureScrapController(LectureScrapService lectureScrapService) {
        this.lectureScrapService = lectureScrapService;
    }
    @PostMapping("scrapLecture")
    public ResponseEntity<LectureScrap> scrapLecture(@RequestBody LectureScrap lectureScrap){
        return new ResponseEntity<>(lectureScrapService.scrapLecture(lectureScrap), HttpStatus.OK);
    }
    @PostMapping("deleteScrapLecture")
    public ResponseEntity<String> deleteScrapLecture(@RequestBody LectureScrap lectureScrap){
        return new ResponseEntity<>(lectureScrapService.deleteScrapLecture(lectureScrap), HttpStatus.OK);
    }
    @PostMapping("myScrapLecture")
    public ResponseEntity<List<LectureScrap>> myScrapLecture(@RequestBody User user){
        return new ResponseEntity<>(lectureScrapService.myScrapLecture(user), HttpStatus.OK);
    }

}
