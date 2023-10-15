package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.Agent;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        Agent userDetails = (Agent) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
        Cookie cookie = new Cookie("auth",userDetails.getGrade());
         
        if (userDetails.getGrade().equals("admin")) {
            redirectURL = "/";
        } else if (userDetails.getGrade().equals("collecter")) {
            redirectURL = "agent_transac";
        } 
        else if (userDetails.getGrade().equals("client")) {
        	System.out.println("client connected");
            redirectURL = "client";
        }
        
        response.addCookie(cookie);
        response.sendRedirect(redirectURL);
         
    }

}
