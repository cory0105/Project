package PTR.PTR.service;

import PTR.PTR.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
}
