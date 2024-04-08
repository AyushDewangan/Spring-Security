package com.example.demo.ag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ag.model.AGLoans;
import com.example.demo.ag.repository.AGLoanRepository;

@RestController
@RequestMapping("/ag")
public class AGLoansController {

    @Autowired
    private AGLoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<AGLoans> getLoanDetails(@RequestParam int id) {
        List<AGLoans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null ) {
            return loans;
        }else {
            return null;
        }
    }
    
    @PostMapping("/save/loan")
    public AGLoans saveLoanDetails(@RequestBody AGLoans agLoans) {
    	return loanRepository.save(agLoans);
    }

}
