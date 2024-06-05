package PTR.PTR.service;

import PTR.PTR.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
}
