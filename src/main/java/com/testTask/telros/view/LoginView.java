package com.testTask.telros.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.core.userdetails.UserDetailsService;

@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    public LoginView(UserDetailsService userDetailsService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        var login = new LoginForm();
        login.setAction("login");

        add(
                new H1("Enter"),
                login
        );
    }
}
