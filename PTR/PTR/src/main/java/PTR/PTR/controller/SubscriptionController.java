package PTR.PTR.controller;

import PTR.PTR.service.SubscriptionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {
    SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
}
