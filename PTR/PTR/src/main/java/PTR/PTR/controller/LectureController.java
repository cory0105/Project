package PTR.PTR.controller;

import PTR.PTR.model.Category;
import PTR.PTR.model.Lecture;
import PTR.PTR.model.Teacher;
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

    @PostMapping("findCategoryLecture")
    public ResponseEntity<List<Lecture>> findCategoryLecture(@RequestBody Category category){
        return new ResponseEntity<>(lectureService.findCategoryLecture(category), HttpStatus.OK);
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
}
