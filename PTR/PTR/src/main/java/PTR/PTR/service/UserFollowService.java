package PTR.PTR.service;

import PTR.PTR.repository.UserFollowRepository;
import org.springframework.stereotype.Service;

@Service
public class UserFollowService {
    UserFollowRepository userFollowRepository;

    public UserFollowService(UserFollowRepository userFollowRepository) {
        this.userFollowRepository = userFollowRepository;
    }
}
