package com.example.demo.ag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ag.model.AGAccounts;
import com.example.demo.ag.repository.AGAccountsRepository;

@RestController
@RequestMapping("/ag")
public class AGAccountController {

    @Autowired
    private AGAccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public AGAccounts getAccountDetails(@RequestParam int id) {
        AGAccounts accounts = accountsRepository.findByCustomerId(id);
        if (accounts != null ) {
            return accounts;
        }else {
            return null;
        }
    }
    
    @PostMapping("/save/account")
    public AGAccounts saveAccountDetails(@RequestBody AGAccounts accounts) {
    	return accountsRepository.save(accounts);
    }

}
