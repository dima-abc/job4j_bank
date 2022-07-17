package ru.job4j.bank.model;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 3.4.8. Rest
 * 5. Обработка исключений и Spring REST [#504797]
 * BankService из уровня "Стажер".
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 17.07.2022
 */
public class User extends Id {
    private String passport;
    private String username;

    private List<Account> accounts = new CopyOnWriteArrayList<>();

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passport);
    }

    @Override
    public String toString() {
        return "User{passport='" + passport + '\''
                + ", username='" + username + '\''
                + ", accounts=" + accounts + '}';
    }
}
