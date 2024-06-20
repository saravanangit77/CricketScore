package com.example.sportsscore.controller;

import com.example.sportsscore.model.Subscription;
import com.example.sportsscore.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<Subscription> subscribe(
            @RequestParam int userId,
            @RequestParam int typeId,
            @RequestParam String detailValue) {
        Subscription subscription = subscriptionService.createSubscription(userId, typeId, detailValue);
        return ResponseEntity.ok(subscription);
    }
}

