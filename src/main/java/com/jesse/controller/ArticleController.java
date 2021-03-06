package com.jesse.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jesse.model.ArticleEntity;
import com.jesse.model.CommentEntity;
import com.jesse.service.article.ArticleService;
import com.jesse.service.comment.CommentService;
import com.jesse.utils.JsonUtils;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	

	
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAll(){

		System.out.println(JsonUtils.toString(articleService.getAll()));
		
		return JsonUtils.toString(articleService.getAll());
	}
	
	@RequestMapping("/getArticlesByPage/{page}")
	@ResponseBody
	public Page<ArticleEntity> getArticlesByPage(@PathVariable int page){
		
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(page-1, 5,sort);
		System.out.println("22222222");
		System.out.println(articleService.findArticleEntityByPageable(pageable));
		return articleService.findArticleEntityByPageable(pageable);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addArticle(@RequestBody Map map){
		try{
			String title = (String) map.get("title");
			String content = (String) map.get("content");
			String type = (String) map.get("type");
			Date date = new Date();
			ArticleEntity articleEntity = new ArticleEntity();
			articleEntity.setTitle(title);
			articleEntity.setContent(content);
			articleEntity.setType(type);
			articleEntity.setCreateDate(date);
			articleService.save(articleEntity);
		}catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
		
	}
	
	@RequestMapping("/single")
	public String toSingle(){
		return "single";
	}
	
	//根据id获取文章、评论详情
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  
	public String findOwner(@PathVariable String id,HttpServletRequest hsr) {
	  HttpSession session = hsr.getSession();
	  ArticleEntity articleEntity = articleService.findById(Integer.valueOf(id));
	  session.setAttribute("article", articleEntity);
	  Sort sort = new Sort(Direction.DESC,"id");
	  Pageable pageable = new PageRequest(0, 10,sort);
	  Page<CommentEntity> comments = commentService.findByArticleId(id, pageable);
	  session.setAttribute("comments", comments);
	  System.out.println(articleEntity.getContent());
	  return "articleDetail";   
	}  
	
	@DeleteMapping(value="/{id}")
	@ResponseBody
	public String deleteById(@PathVariable int id){
		articleService.deleteById(id);
		return "1";
	}
	
	
	@RequestMapping(value="/page/{page}",method=RequestMethod.GET)
	@ResponseBody
	public Page<ArticleEntity> findByPage(@PathVariable String page){
		int pageNum = Integer.valueOf(page);
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(pageNum-1, 10, sort);  //每页10
		return articleService.findArticleEntityByPageable(pageable);
	}
	
	@RequestMapping("/getTop6")
	@ResponseBody
	public Page<ArticleEntity> getTop6(){
		
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(0, 6,sort);
		return articleService.findArticleEntityByPageable(pageable);
	}
}
