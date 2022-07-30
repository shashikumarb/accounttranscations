package com.banking.transactions.controller;

import com.banking.transactions.dao.AccountDAO;
import com.banking.transactions.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class AccountTransactionController {
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/read")
    public List<Account> read(){
        return accountDAO.findAll();
    }

    @GetMapping("/read/{id}")
    public Account read(@PathVariable Long id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(id));
        Account account = mongoTemplate.findOne(query,Account.class);
        return account;
    }

    @PostMapping(value = "/updatetransaction", consumes = "application/json", produces = "application/json")
    public Account updateTransaction(@RequestBody Account account, HttpServletResponse response) {
        return accountDAO.save(account);
    }


}

