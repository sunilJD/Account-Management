package com.account_management.service;

import com.account_management.payload.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto getSubscriptionById(Long id);

    List<SubscriptionDto> getAllSubscriptions();

    SubscriptionDto createSubscription(SubscriptionDto subscriptionDto);

    SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto);

    void deleteSubscription(Long id);
}
