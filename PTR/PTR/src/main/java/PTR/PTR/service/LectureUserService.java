package PTR.PTR.service;

import PTR.PTR.model.Lecture;
import PTR.PTR.model.LectureUser;
import PTR.PTR.model.User;
import PTR.PTR.repository.LectureUserRepository;
import PTR.PTR.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureUserService {
    LectureUserRepository lectureUserRepository;
    UserRepository userRepository;

    public LectureUserService(LectureUserRepository lectureUserRepository, UserRepository userRepository) {
        this.lectureUserRepository = lectureUserRepository;
        this.userRepository = userRepository;
    }

    public String buyLecture(LectureUser lectureUser){
        lectureUser.setCreatedAt(LocalDateTime.now());
        int price = lectureUser.getLecture().getPrice();
        int coin = lectureUser.getUser().getCoin();
        lectureUser.setUser(userRepository.findById(lectureUser.getUser().getUserId()).get());
        if (coin >= price){
            if (null==lectureUserRepository.findByUserAndLecture(lectureUser.getUser(),lectureUser.getLecture())){
                coin = coin - price;
                lectureUser.getUser().setCoin(coin);
                userRepository.save(lectureUser.getUser());
                lectureUserRepository.save(lectureUser);
                return "결제 완료";
            }else {
                return "이미 결제함";
            }
        }else {
            return "잔액 부족";
        }
    }

    public List<LectureUser> myBuyLecture(User user){
        return lectureUserRepository.findByUser(user);
    }
}
