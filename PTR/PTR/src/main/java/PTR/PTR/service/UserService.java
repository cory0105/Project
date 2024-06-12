package PTR.PTR.service;

import PTR.PTR.dto.SignupDto;
import PTR.PTR.model.Authority;
import PTR.PTR.model.Category;
import PTR.PTR.model.User;
import PTR.PTR.model.UserCategory;
import PTR.PTR.repository.UserCategoryRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    UserCategoryRepository userCategoryRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, UserCategoryRepository userCategoryRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String saveUser(SignupDto signupDto) {
        Optional<User> userOptional = userRepository.findByUserId(signupDto.getUserId());
        if (userOptional.isPresent()){
            return "이미 등록된 아이디입니다.";
        }
        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");
        User user = new User(
                signupDto.getUserId(),
                bCryptPasswordEncoder.encode(signupDto.getPassword()),
                signupDto.getUserName(),
                signupDto.getUserEmail(),
                signupDto.getBirthday(),
                LocalDateTime.now(),
                "",
                "",
                0,
                authority);
        return userRepository.save(user).getUserId();
    }

    public String saveUserCategory(List<UserCategory> userCategories){
        if (!userCategoryRepository.findByUser(userCategories.getFirst().getUser()).isEmpty()){
            userCategoryRepository.deleteAll(userCategoryRepository.findByUser(userCategories.getFirst().getUser()));
        }
        userCategoryRepository.saveAll(userCategories);
        return "카테고리가 저장됨";
    }
    public String deleteUserCategory(User user){
        if (!userCategoryRepository.findByUser(user).isEmpty()){
            userCategoryRepository.deleteAll(userCategoryRepository.findByUser(user));
        }
        return "카테고리가 삭제됨";
    }
    public List<UserCategory> findUserCategory(User user){
        return userCategoryRepository.findByUser(user);
    }
}