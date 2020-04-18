package com.realestate.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.realestate.model.ReComment;

public interface ReCommentJPADao extends JpaRepository<ReComment, Long>{

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM re_comment WHERE comment_id = ?", nativeQuery = true)
	void deleteComment(long commentId);

}
