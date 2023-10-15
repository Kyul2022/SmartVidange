package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Poubelle;
import com.example.demo.model.Vidange;
import com.example.demo.model.trans_client;
import com.example.demo.service.AgentService;
import com.example.demo.service.EmailService;
import com.example.demo.service.PoubelleService;
import com.example.demo.service.VidangeService;
import com.example.demo.service.trans_clientService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(maxAge = 3600)
@RestController
public class CenterVidangeAuto {
	
	@Autowired
	private PoubelleService poubelleService;
	
	//
	@Autowired
	private trans_clientService transService;
	//
	@Autowired
	private EmailService emailService;
	//
	@Autowired
	private VidangeService vidangeService;
	//
	@Autowired
	private AgentService agentService;

	@GetMapping("/poubelle/{poubelle_id}/{percentage}") //packet plutot
    @ResponseBody
    public Poubelle setLevel(@PathVariable(value = "poubelle_id") String id,@PathVariable(value = "percentage") String percentage, HttpSession session){
		
		Poubelle poubelle = poubelleService.findByNumSerie(Integer.parseInt(id));
		poubelle.setPlein(Double.parseDouble(percentage));
		poubelleService.savePoubelle(poubelle);
		return poubelle;
	}
	
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;    }
	
    @CrossOrigin(origins = "http://192.168.1.3:8080")
	@GetMapping("/poubelles/{matricule}") //packet plutot
    @ResponseBody
    public ResponseEntity<Iterable<Vidange>> getVidanges(@PathVariable(value = "matricule") String matricule){

		return new ResponseEntity<Iterable<Vidange>>(vidangeService.findByVideur(agentService.findByMatricule(matricule)),HttpStatus.OK);
	}
    
	@GetMapping("/vider/{poubelle_id}") //packet plutot
    @ResponseBody
    public RedirectView finish(@PathVariable(value = "poubelle_id") int id, RedirectAttributes attributes)

	{
    	Poubelle p = poubelleService.findByNumSerie(id);
    	Vidange v = vidangeService.findByPoubelle(p);
    	v.setVide("okay");
    	vidangeService.save(v);
        return new RedirectView("http://192.168.1.3:8080/agent_transac");
	}
	
	@GetMapping("/end_user_side") //packet plutot
    @ResponseBody
    public RedirectView ended(RedirectAttributes attributes)

	{
    	Poubelle p = poubelleService.findByNumSerie(1);
    	Vidange v = vidangeService.findByPoubelle(p);
    	v.setVide("ok");
    	vidangeService.save(v);
		trans_client tr = transService.findById(1);
		tr.setStatus("finished");
		transService.save(tr);
        return new RedirectView("http://192.168.1.3:8080/client");
	}
	
	@GetMapping("/choisir") //packet plutot
    @ResponseBody
    public RedirectView choose(RedirectAttributes attributes)

	{
    	/*Poubelle p = poubelleService.findByNumSerie(id);
    	Vidange v = vidangeService.findByPoubelle(p);
    	v.setVide("ok");
    	vidangeService.save(v);
        return new RedirectView("http://192.168.1.3:8080/agent_transac");*/
		trans_client tr = transService.findById(1);
		tr.setStatus("selected");
		transService.save(tr);
        return new RedirectView("http://192.168.1.3:8080/agent_transac");
	}
	
	@GetMapping("/finish") //packet plutot
    @ResponseBody
    public RedirectView fin(RedirectAttributes attributes)

	{
    	/*Poubelle p = poubelleService.findByNumSerie(id);
    	Vidange v = vidangeService.findByPoubelle(p);
    	v.setVide("ok");
    	vidangeService.save(v);
        return new RedirectView("http://192.168.1.3:8080/agent_transac");*/
		trans_client tr = transService.findById(1);
		tr.setStatus("end");
		transService.save(tr);
        return new RedirectView("http://192.168.1.3:8080/client");
	}
    
	
    @CrossOrigin(origins = "http://192.168.1.3:8080")
    @GetMapping("/trans_client")
    public ResponseEntity<Iterable<trans_client>> getTrans_client(HttpSession session) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	return new ResponseEntity<Iterable<trans_client>>(transService.findByStatus("selected"), HttpStatus.OK);
    } 
	
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/ongoingtrans")
    public ResponseEntity<Iterable<Vidange>> getVidangeOnGoing(HttpSession session) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	return new ResponseEntity<Iterable<Vidange>>(vidangeService.findByVide("no"), HttpStatus.OK);
    } 
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/finishedtrans")
    public ResponseEntity<Iterable<Vidange>> getVidangeFinished(HttpSession session) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	return new ResponseEntity<Iterable<Vidange>>(vidangeService.findByVide("ok"), HttpStatus.OK);
    } 
    
    @CrossOrigin(origins = "http://localhosts:8080")
    @GetMapping("/done")
    public ResponseEntity<Iterable<Vidange>> getVidangeDone(HttpSession session) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	return new ResponseEntity<Iterable<Vidange>>(vidangeService.findByVide("ok"), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/poubelles")
    public ResponseEntity<Iterable<Poubelle>> getPoubellesAll(HttpSession session) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	return new ResponseEntity<Iterable<Poubelle>>(poubelleService.getPoubellesAll(), HttpStatus.OK);
    }
    
    @GetMapping("/mail")
    public void sendEmail() {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	emailService.sendEmail("ulrichkamdem048@gmail.com", "RAS", "<a href='tel:#150*1#'>Orange Money</a>");
    	
    }  
    
	@GetMapping("/agent_transac")
	public ModelAndView mytransactions() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("agent_transac");
		return mav;
	}
	
	@GetMapping("/client")
	public ModelAndView clientTrans() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client");
		return mav;
	}
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/")
    public ModelAndView index() {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("essai");
    	return mav;
    }
    
    @GetMapping("/ongoing")
    public ModelAndView ongoing() {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("ongoing");
    	return mav;    	
    }
    
    @GetMapping("/finished")
    public ModelAndView finished() {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("completed_transac");
    	return mav;    	
    }
    
    @GetMapping("/contact")
    public ModelAndView contact() {
    	//int number = session.getAttribute("agency_num")!=null?(int) session.getAttribute("agency_num"):0;
       //System.out.println("number agency : "+number);
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("contact");
    	return mav;    	
    }
    
}
