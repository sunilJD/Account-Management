package com.account_management.controller;

import com.account_management.payload.SubscriptionDto;
import com.account_management.utils.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paypal")
public class PaypalRestController {

    @Autowired
    private PaypalService paypalService;

    @PostMapping("/create-subscription")
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        try {
            Payment payment = paypalService.createPayment(subscriptionDto.getId(),
                    subscriptionDto.getPaymentStatus(),
                    subscriptionDto.getStartDate(),
                    subscriptionDto.getEndDate();

            for (com.paypal.api.payments.Links link : payment.getLinks()) {
                if ("approval_url".equals(link.getRel())) {
                    return ResponseEntity.ok(link.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating subscription payment");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating subscription payment");
    }

    @GetMapping("/execute-subscription")
    public ResponseEntity<String> executeSubscription(@RequestParam("paymentId") String paymentId,
                                                      @RequestParam("payerId") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {
                return ResponseEntity.ok("Subscription payment approved");
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing subscription payment");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error executing subscription payment");
    }
}
