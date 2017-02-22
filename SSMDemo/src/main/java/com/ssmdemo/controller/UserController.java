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
	private String inner = "�ο�";
	
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
				inner = user.getId();
				mv.setViewName("reindex");
				return mv;
			}
		}
	}
	
	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("musiclist");
		return mv;
	}
	
	
	
	@RequestMapping(value="/detailedlist")
	public ModelAndView detailedList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("playerlist");
		return mv;
	}
	
	@RequestMapping(value="/personalfile")
	public ModelAndView personalFile(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", inner);
		mv.setViewName("personalfile");
		return mv;
	}
	
	@RequestMapping(value="/returntoreindex")
	public ModelAndView toReindex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("reindex");
		return mv;
	}
	
	@RequestMapping(value="/exit")
	public String exit(){
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/relist")
	public ModelAndView reList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("remusiclist");
		return mv;
	}
	
	@RequestMapping(value="/redetailedlist")
	public ModelAndView reDetailedList(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("replayerlist");
		return mv;
	}
	
}
