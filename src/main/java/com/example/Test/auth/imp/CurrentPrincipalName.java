package com.example.Test.auth.imp;

import com.example.Test.auth.IAuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CurrentPrincipalName {

    private final IAuthenticationFacade authenticationFacade;

    public CurrentPrincipalName(IAuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public String currentPrincipalName() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
}