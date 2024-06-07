package PTR.PTR.service;

import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.repository.AdminRepository;
import PTR.PTR.repository.TeacherRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    AdminRepository adminRepository;
    TeacherRepository teacherRepository;
    UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, TeacherRepository teacherRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }

    public String grantTeacher(String userId){
        Teacher teacher = new Teacher();
        teacher.setUser(userRepository.findById(userId).get());
        teacher.setPrice(0);
        teacherRepository.save(teacher);
        return teacher.getUser().getUserId();
    }

}
