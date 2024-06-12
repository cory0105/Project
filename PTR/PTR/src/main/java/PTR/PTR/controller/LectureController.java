package PTR.PTR.controller;

import PTR.PTR.model.*;
import PTR.PTR.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureController {
    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    @PostMapping("createLecture")
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureService.createLecture(lecture), HttpStatus.OK);
    }
    @PostMapping("findTeacherLecture")
    public ResponseEntity<List<Lecture>> findTeacherLecture(@RequestBody Teacher teacher){
        return new ResponseEntity<>(lectureService.findTeacherLecture(teacher), HttpStatus.OK);
    }


    @PostMapping("searchLecture")
    public ResponseEntity<List<Lecture>> searchLecture(@RequestBody String search){
        return new ResponseEntity<>(lectureService.searchLecture(search), HttpStatus.OK);
    }
    @PostMapping("findPriceLecture")
    public ResponseEntity<List<Lecture>> findPriceLecture(@RequestBody List<Integer> price){
        int minPrice = price.get(0);
        int maxPrice = price.get(1);
        return new ResponseEntity<>(lectureService.findPriceLecture(minPrice, maxPrice), HttpStatus.OK);
    }

    @PostMapping("saveLectureCategory")
    public ResponseEntity<String> saveLectureCategory(@RequestBody List<LectureCategory> lectureCategories){
        return new ResponseEntity<>(lectureService.saveLectureCategory(lectureCategories), HttpStatus.OK);
    }
    @PostMapping("deleteLecturerCategory")
    public ResponseEntity<String> deleteLecturerCategory(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureService.deleteLecturerCategory(lecture), HttpStatus.OK);
    }
    @PostMapping("findLectureCategory")
    public ResponseEntity<List<LectureCategory>> findLectureCategory(@RequestBody Lecture lecture){
        return new ResponseEntity<>(lectureService.findLectureCategory(lecture), HttpStatus.OK);
    }
    @PostMapping("findLectureByCategory")
    public ResponseEntity<List<Lecture>> findLectureByCategory(@RequestBody Category category){
        return new ResponseEntity<>(lectureService.findLectureByCategory(category), HttpStatus.OK);
    }
}
