package PTR.PTR.controller;

import PTR.PTR.service.LectureService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {
    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
}
