package com.example.sportsscore.service;

import com.example.sportsscore.model.Subscription;
import com.example.sportsscore.model.SubscriptionDetail;
import com.example.sportsscore.model.SubscriptionType;
import com.example.sportsscore.model.User;
import com.example.sportsscore.repository.SubscriberRepository;
import com.example.sportsscore.repository.SubscriptionDetailRepository;
import com.example.sportsscore.repository.SubscriptionRepository;
import com.example.sportsscore.repository.SubscriptionTypeRepository;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class SubscriptionService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionDetailRepository subscriptionDetailRepository;

    public SubscriptionDetail createSubscription(int userId, int typeId, String detailValue) {
        User subscriber = subscriberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Subscriber not found"));
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(typeId).orElseThrow(() -> new IllegalArgumentException("Subscription type not found"));

        Subscription subscription = new Subscription();
        subscription.setSubscriber(subscriber);
        subscription.setSubscriptionType(subscriptionType);
        subscription = subscriptionRepository.save(subscription);

        SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
        subscriptionDetail.setSubscription(subscription);
        subscriptionDetail.setDetailValue(detailValue);
        subscriptionDetailRepository.save(subscriptionDetail);

        return subscriptionDetail;
    }
}
