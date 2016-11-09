package app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import app.database.PostgreUtil;
import app.models.UserForm;

@Controller
public class UserController extends WebMvcConfigurerAdapter{

	private PostgreUtil pgUtil = PostgreUtil.getInstance();

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showForm(UserForm personForm) {
		return "userForm";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String checkPersonInfo(@Valid UserForm personForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "userForm";
		}
		pgUtil.addUser(personForm);
		return "redirect:/users";
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ModelAndView showUsers(UserForm personForm) {
		ModelAndView mv = new ModelAndView("/users");
		mv.addObject("users", PostgreUtil.getInstance().getUsers());
		return mv;
	}
}
