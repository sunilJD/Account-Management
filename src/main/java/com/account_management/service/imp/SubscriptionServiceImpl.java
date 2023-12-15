package com.account_management.service.imp;

import com.account_management.entities.Subscription;
import com.account_management.payload.SubscriptionDto;
import com.account_management.repository.SubscriptionRepository;
import com.account_management.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubscriptionDto getSubscriptionById(Long id) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        return optionalSubscription.map(this::mapToDto).orElse(null);
    }

    @Override
    public List<SubscriptionDto> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = mapToEntity(subscriptionDto);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToDto(savedSubscription);
    }

    @Override
    public SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            Subscription existingSubscription = optionalSubscription.get();
            BeanUtils.copyProperties(subscriptionDto, existingSubscription);
            Subscription updatedSubscription = subscriptionRepository.save(existingSubscription);
            return mapToDto(updatedSubscription);
        }
        return null; // Subscription with the given id not found
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    private SubscriptionDto mapToDto(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionDto.class);
    }

    private Subscription mapToEntity(SubscriptionDto subscriptionDto) {
        return modelMapper.map(subscriptionDto, Subscription.class);
    }
}
