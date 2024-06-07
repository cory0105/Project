package PTR.PTR.controller;

import PTR.PTR.model.Subscription;
import PTR.PTR.model.Teacher;
import PTR.PTR.model.User;
import PTR.PTR.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriptionController {
    SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    @PostMapping("subscription")
    public ResponseEntity<String> subscription(@RequestBody Subscription subscription){
        return new ResponseEntity<>(subscriptionService.subscription(subscription), HttpStatus.OK);
    }
    @PostMapping("subscriptionCansel")
    public ResponseEntity<String> subscriptionCansel(@RequestBody Subscription subscription){
        return new ResponseEntity<>(subscriptionService.subscriptionCansel(subscription), HttpStatus.OK);
    }
    @PostMapping("mySubscription")
    public ResponseEntity<List<Subscription>> mySubscription(@RequestBody User user){
        return new ResponseEntity<>(subscriptionService.mySubscription(user),HttpStatus.OK);
    }
    @PostMapping("teacherSubscription")
    public ResponseEntity<Integer> teacherSubscription(@RequestBody Teacher teacher){
        return new ResponseEntity<>(subscriptionService.teacherSubscription(teacher), HttpStatus.OK);
    }
}
