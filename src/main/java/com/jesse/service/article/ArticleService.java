package com.jesse.service.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jesse.model.ArticleEntity;

public interface ArticleService {
	public List<ArticleEntity> getAll();
	public void save(ArticleEntity articleEntity);
	public ArticleEntity findById(int id);
	public Page<ArticleEntity> findArticleEntityByPageable(Pageable pageable);
	public void deleteById(int id);
}
