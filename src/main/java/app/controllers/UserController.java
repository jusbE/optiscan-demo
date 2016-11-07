package app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import app.models.UserForm;

@Controller
public class UserController extends WebMvcConfigurerAdapter{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/users").setViewName("users");
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showForm(UserForm personForm) {
		return "userForm";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String checkPersonInfo(@Valid UserForm personForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "userForm";
		}

		return "redirect:/users";
	}
}
