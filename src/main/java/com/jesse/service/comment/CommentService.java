package com.jesse.service.comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jesse.model.CommentEntity;

public interface CommentService {
	public List<CommentEntity> getAll();
	public void save(CommentEntity CommentEntity);
	public CommentEntity findById(int id);
	public Page<CommentEntity> findCommentEntityByPageable(Pageable pageable);
	public Page<CommentEntity> findByArticleId(String articleid,Pageable pageable);
}
