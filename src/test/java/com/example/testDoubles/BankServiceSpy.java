package com.example.testDoubles;

import com.example.BankService;

public class BankServiceSpy implements BankService {
    public int numOfPayments = 0;
    @Override
    public void pay(String id, double amount) {
        numOfPayments++;
    }
}
