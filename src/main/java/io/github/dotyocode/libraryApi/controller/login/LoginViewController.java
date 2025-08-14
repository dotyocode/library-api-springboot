package io.github.dotyocode.libraryApi.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.dotyocode.libraryApi.security.CustomAuthentication;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String paginaHome(Authentication authentication) {
        if (authentication instanceof CustomAuthentication customAuth) {
            return "Olá, " + customAuth.getUsuario();
        }
        return "Olá, " + authentication.getName();
    }
}
