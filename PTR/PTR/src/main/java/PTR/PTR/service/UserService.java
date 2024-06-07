package PTR.PTR.service;

import PTR.PTR.dto.SignupDto;
import PTR.PTR.model.Authority;
import PTR.PTR.model.Category;
import PTR.PTR.model.User;
import PTR.PTR.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String saveUser(SignupDto signupDto) {
        Optional<User> userOptional = userRepository.findByUserId(signupDto.getUserId());
        if (userOptional.isPresent()){
            return "이미 등록된 아이디입니다.";
        }
        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");
        Category category = new Category();
        category.setCategoryName("all");
        User user = new User(
                signupDto.getUserId(),
                bCryptPasswordEncoder.encode(signupDto.getPassword()),
                signupDto.getUserName(),
                signupDto.getUserEmail(),
                signupDto.getBirthday(),
                LocalDateTime.now(),
                "",
                "",
                category,
                0,
                authority);
        return userRepository.save(user).getUserId();
    }
}