package com.ssmdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssmdemo.entity.User;
import com.ssmdemo.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/tosignup")
	public ModelAndView toSignup(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		return mv;
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView signup(User user){
		String error = "";
		ModelAndView mv = new ModelAndView();
		User existent = userService.findByID(user.getId()); 
        if(existent != null){
			error = "�û��Ѵ���";
			mv.addObject("error", error);
			mv.setViewName("errorpage");
			return mv;
		}
		else{
			System.out.println("okokokokokokok");
			userService.add(user);
			//return "redirect:/index.jsp";
			mv.setViewName("successfulpage");
			return mv;
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="id") String id, @RequestParam(value="password") String password){
		String error="";
		User user = userService.findByID(id);
		ModelAndView mv = new ModelAndView();
		if(user == null){
			error = "�û�������";
			mv.addObject("error", error);
			mv.setViewName("errorpage");
			return mv;
		}
		else{
			if(!user.getPassword().equals(password)){
				error = "�˺�/�������";
				mv.addObject("error", error);
				mv.setViewName("errorpage");
				return mv;
			}
			else{
				mv.setViewName("songlists");
				return mv;
			}
		}
	}
}
