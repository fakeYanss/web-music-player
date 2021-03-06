package com.ssmdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssmdemo.entity.Song;
import com.ssmdemo.service.SongService;

@Controller
@RequestMapping(value="/song")
public class SongController {
	
	@Autowired
	private SongService songService;
	
	@RequestMapping(value="/search")
	public ModelAndView search(@RequestParam(value="song") String song){
		
		List<Song> consequence = songService.findBySong(song);
		ModelAndView mv = new ModelAndView();
		/*
		if(consequence == null || consequence.isEmpty()){
			Song ss = new Song();
			ss.setSong("未搜索到结果");
			consequence.add(ss);
		}
		*/
		mv.addObject("consequence", consequence);
		mv.setViewName("searchresult");
		return mv;
	}
	
	@RequestMapping(value="/research")
	public ModelAndView research(@RequestParam(value="song") String song){
		
		List<Song> consequence = songService.findBySong(song);
		ModelAndView mv = new ModelAndView();
		/*
		if(consequence == null || consequence.isEmpty()){
			Song ss = new Song();
			ss.setSong("未搜索到结果");
			consequence.add(ss);
		}
		*/
		mv.addObject("consequence", consequence);
		mv.setViewName("researchresult");
		return mv;
	}
	
	@RequestMapping(value="/adminaddsong")
	public ModelAndView adminAddSong(@RequestParam(value="song") String song, @RequestParam(value="singer") String singer){
		Song tem = new Song();
		tem.setSinger(singer);
		tem.setSong(song);
		songService.add(tem);
		List<Song> consequence = songService.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("consequence", consequence);
		mv.setViewName("songmage");
		return mv;
	}
	
	@RequestMapping(value="/adminallsong")
	public ModelAndView adminAllSong(){
		List<Song> consequence = songService.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("consequence", consequence);
		mv.setViewName("songmage");
		return mv;
	}
	
	@RequestMapping(value="/admindeletesong")
	public ModelAndView adminDeleteSong(@RequestParam(value="delete") Integer delete){
		songService.delete(delete);
		List<Song> deco =  songService.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("consequence", deco);
		mv.setViewName("songmage");
		return mv;
	}
}
