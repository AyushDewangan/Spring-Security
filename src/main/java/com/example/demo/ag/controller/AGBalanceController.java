package com.example.demo.ag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ag.model.AGAccountTransactions;
import com.example.demo.ag.repository.AGAccountTransactionsRepository;

@RestController
@RequestMapping("/ag")
public class AGBalanceController {

    @Autowired
    private AGAccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AGAccountTransactions> getBalanceDetails(@RequestParam int id) {
        List<AGAccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);
        if (accountTransactions != null ) {
            return accountTransactions;
        }else {
            return null;
        }
    }
    
    @PostMapping("/save/balance")
    public AGAccountTransactions saveBalanceDetails(@RequestBody AGAccountTransactions accountTransactions) {
    	return accountTransactionsRepository.save(accountTransactions);
    }
}
