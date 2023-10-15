package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import com.example.demo.model.Users;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.stripe.ChargeRequest;

import jakarta.servlet.http.HttpSession;

@RestController
public class ruLinkCenter {
    @Autowired
    private MailSender mailSender;
	
	@Autowired
	private CustomUserDetails UserDetailsService;
	
    private String stripePublicKey = "pk_test_51NkpcyGvOxBUKm6chZidWLdx9OCExW3fgBGjRxdZrHstx5uIzXZe0ADbh1b1iXPKodf2Tl6cRiFoTyAEgQeKrKQz003q10THGU";

	
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(){
		ModelAndView mav = new ModelAndView();
        Users user = new Users();
        mav.addObject("user", user);
		mav.setViewName("login");
		return mav;    }
    

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gate");
		return mav;
	}
	/////////Pour la région du centre en tant que Admin
	
	@GetMapping("/map")
	public ModelAndView map() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("map");
		return mav;
	}
	/////////////Pour le littoral en tant que Admin
	
	@GetMapping("/mapLittoral")
	public ModelAndView map_littoral() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("map_littoral");
		return mav;
	}
	
	@GetMapping("/mapAdmin")
	public ModelAndView map_admin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mapAdmin");
		return mav;
	}
	
    @PostMapping("/Areas")
    public RedirectView mapAreas(@CookieValue(value="auth",defaultValue="none") String auth, @RequestBody String area,HttpSession session, BindingResult bindingResult, RedirectAttributes attributes) {

    	if(((area).trim()).contains("centre")) {
        	System.out.println("area "+area);
        	if(auth.contains("admin")) {
                return new RedirectView("http://localhost:8080/ruLink2-0.0.1-SNAPSHOT/map");
        	}
    	}
    	
    	else if(((area).trim()).contains("littoral")) {
        	System.out.println("area "+area);
        	if(auth.contains("admin")) {
                return new RedirectView("http://localhost:8080/ruLink2-0.0.1-SNAPSHOT/mapLittoral");
        	}
    	}
    	
    	else if(((area).trim()).contains("douala")) {
        	System.out.println("area "+area);
        	if(auth.contains("chef-centre") && auth.contains("Littoral") ) {
                return new RedirectView("http://localhost:8080/ruLink2-0.0.1-SNAPSHOT/Douala");
        	}
    	}
    	
    	else if(((area).trim()).contains("yaounde")) {
        	System.out.println("area "+area);
        	if(auth.contains("chef-centre") && auth.contains("Centre") ) {
                return new RedirectView("http://localhost:8080/ruLink2-0.0.1-SNAPSHOT/Yaounde");
        	}
    	}
    	
    	return new RedirectView("http://localhost:8080/ruLink2-0.0.1-SNAPSHOT/error");
}
	
	//////////////Littoral mais chef centre
	
	@GetMapping("/mapLittoralChef")
	public ModelAndView map_littoral_chef() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mapLittoralChef");
		return mav;
	}
	
	@GetMapping("/mail")
	public ModelAndView mail() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mail");
		return mav;
	}
	
	@GetMapping("/buy")
	public ModelAndView buy() {
	        
			ModelAndView mav = new ModelAndView();
			mav.setViewName("MarketPlace");
			mav.addObject("stripePublicKey", stripePublicKey);
			mav.addObject("amount", 7197 * 100); // in cents
			mav.addObject("currency", ChargeRequest.Currency.EUR);
		return mav;
	}
	
	@GetMapping("/mapCentreChef")
	public ModelAndView map_centre_chef() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mapCentreChef");
		return mav;
	}
	
	@GetMapping("/Douala")
	public ModelAndView map_douala() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("douala");
		return mav;
	}
	
	@GetMapping("/error")
	public ModelAndView err() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		return mav;
	}
	
	@GetMapping("/Yaounde")
	public ModelAndView map_yaounde() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("yaounde");
		return mav;
	}
	
    @PostMapping("/sendMail")
    public RedirectView senMail(@RequestBody String email,@RequestBody String msg,HttpSession session, BindingResult bindingResult, RedirectAttributes attributes) {

        /*SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("aaa@localhost");
        mail.setSubject("Pour RuLink");
        mail.setText(msg);
        mailSender.send(mail);
    	*/
    	return new RedirectView("http://localhost:8080");
}
}
