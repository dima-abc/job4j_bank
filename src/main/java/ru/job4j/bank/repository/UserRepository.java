package ru.job4j.bank.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.stereotype.Repository;
import ru.job4j.bank.model.User;

import java.util.Optional;

/**
 * 3.4.8. Rest
 * 5. Обработка исключений и Spring REST [#504797]
 * BankService из уровня "Стажер".
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 17.07.2022
 */
@Repository
public class UserRepository extends Store<User> {
    public Optional<User> findByPassport(String passport) {
        return store.values().stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst();
    }
}
