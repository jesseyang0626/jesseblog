package com.jesse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jesse.model.ArticleEntity;

@Controller
public class ToPageController {

	@RequestMapping("/")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/articleList")
	public String toArtcileListPage(){
		return "articleList";
	}
	

}
