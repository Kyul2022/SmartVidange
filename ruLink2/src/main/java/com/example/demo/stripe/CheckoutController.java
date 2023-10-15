package com.example.demo.stripe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Value;

@Controller
public class CheckoutController {

    private String stripePublicKey = "pk_test_51NkpcyGvOxBUKm6chZidWLdx9OCExW3fgBGjRxdZrHstx5uIzXZe0ADbh1b1iXPKodf2Tl6cRiFoTyAEgQeKrKQz003q10THGU";

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 7197 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
