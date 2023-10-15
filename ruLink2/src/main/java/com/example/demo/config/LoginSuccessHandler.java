package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        Users userDetails = (Users) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
        Cookie cookie = new Cookie("auth",userDetails.getGrade()+userDetails.getRegion());
         
        if (userDetails.getGrade().equals("admin")) {
            redirectURL = "/ruLink2-0.0.1-SNAPSHOT/";
        } else if (userDetails.getRegion().equals("Centre")) {
            redirectURL = "mapCentreChef";
        } else if (userDetails.getRegion().equals("Littoral")) {
            redirectURL = "mapLittoralChef";
        }
        response.addCookie(cookie);
        response.sendRedirect(redirectURL);
         
    }

}
