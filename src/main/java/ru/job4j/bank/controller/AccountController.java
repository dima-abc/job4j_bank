package ru.job4j.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.service.BankService;

import java.util.Map;

/**
 * 3.4.8. Rest
 * 5. Обработка исключений и Spring REST [#504797]
 * BankService из уровня "Стажер".
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 18.07.2022
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private final BankService bankService;

    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public Account addAccount(@RequestBody Map<String, String> body) {
        String passport = body.get("passport");
        Account account = new Account(body.get("requisite"), 0);
        bankService.addAccount(passport, account);
        return account;
    }

    @GetMapping
    public Account findByRequisite(@RequestParam String passport,
                                   @RequestParam String requisite) {
        return bankService.findByRequisite(passport, requisite)
                .orElseThrow(
                        () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account is not found. Please, check requisites"));
    }
}
