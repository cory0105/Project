package PTR.PTR.controller;

import PTR.PTR.service.TeacherService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
