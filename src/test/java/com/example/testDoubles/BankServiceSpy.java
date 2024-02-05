package com.example.testDoubles;

import com.example.BankService;

public class BankServiceSpy implements BankService {
    public int numOfPayments = 0;
    public boolean payFailure = false;

    @Override
    public void pay(String id, double amount) {
        numOfPayments++;

        if (payFailure) throw new RuntimeException("Payment failed");
    }
}
