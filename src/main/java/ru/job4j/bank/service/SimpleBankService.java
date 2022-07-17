package ru.job4j.bank.service;

import org.springframework.stereotype.Service;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.model.User;
import ru.job4j.bank.repository.AccountRepository;
import ru.job4j.bank.repository.UserRepository;

import java.util.Optional;

/**
 * 3.4.8. Rest
 * 5. Обработка исключений и Spring REST [#504797]
 * BankService из уровня "Стажер".
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 17.07.2022
 */
@Service
public class SimpleBankService implements BankService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public SimpleBankService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addUser(User user) {
        this.userRepository.saveOrUpdate(user);
    }

    @Override
    public void addAccount(String passport, Account account) {
        this.userRepository.findByPassport(passport)
                .ifPresent(u -> {
                    account.setUser(u);
                    accountRepository.saveOrUpdate(account);
                    u.getAccounts().add(account);
                });
    }

    @Override
    public Optional<User> findByPassport(String passport) {
        return this.userRepository.findByPassport(passport);
    }

    @Override
    public Optional<Account> findByRequisite(String passport, String requisite) {
        return this.accountRepository.findByRequisite(passport, requisite);
    }

    @Override
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        if (srcAccount.isEmpty()) {
            return false;
        }
        Optional<Account> descAccount = findByRequisite(destPassport, destRequisite);
        if (descAccount.isEmpty()) {
            return false;
        }
        srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
        descAccount.get().setBalance(descAccount.get().getBalance() + amount);
        return true;
    }
}
