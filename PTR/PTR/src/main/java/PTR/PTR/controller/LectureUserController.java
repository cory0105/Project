package PTR.PTR.controller;

import PTR.PTR.service.LectureUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureUserController {
    LectureUserService lectureUserService;

    public LectureUserController(LectureUserService lectureUserService) {
        this.lectureUserService = lectureUserService;
    }
}
