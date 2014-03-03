package pl.wszib.kolo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.wszib.kolo.service.ContactService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Spring 4 MVC Hello World");
		model.addAttribute("contacts", contactService.listContacts());
		
		return "home";
	}
}
