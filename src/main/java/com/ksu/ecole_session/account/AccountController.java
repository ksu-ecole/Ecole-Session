package com.ksu.ecole_session.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @GetMapping("/sign-up")
    public String loadSignUp(Model model)  {
        model.addAttribute("account", new Account());

        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String createAccount(Account account)    {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);

        return "sign-in";
    }

    @GetMapping("/sign-in")
    public String loadSignIn()  {
        return "sign-in";
    }

    @GetMapping("/test")
    public String loadIndex(Model model, @AuthenticationPrincipal Account currentAccount)   {
        model.addAttribute("email", currentAccount.getEmail());

        return "test";
    }









}
