package com.jesse.service.article;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jesse.model.ArticleEntity;
import com.jesse.repository.ArticleRepository;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Override
	public List<ArticleEntity> getAll() {
		
		return articleRepository.findAll();
	}
	@Override
	@Transactional
	public void save(ArticleEntity articleEntity) {
		articleRepository.save(articleEntity);
	}
	
	@Override
	public ArticleEntity findById(int id) {
		return articleRepository.findById(id);
	}
	@Override
	public Page<ArticleEntity> findArticleEntityByPageable(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}
	@Override
	@Transactional
	public void deleteById(int id) {
		articleRepository.deleteArticleEntityById(id);
	}

}
