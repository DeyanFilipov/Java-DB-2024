package org.springdemo;

import org.springdemo.data.entities.Account;
import org.springdemo.data.entities.User;
import org.springdemo.service.AccountService;
import org.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private final UserService userService;

    @Autowired
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = this.userService.findUserById(1);
        User user2 = this.userService.findUserById(2);

        this.accountService.withdrawMoney(BigDecimal.valueOf(50), 1);
        this.accountService.transferMoney(BigDecimal.valueOf(1000), 2);
    }
}
