package com.example.demo3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo3.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { // 상속
	// 댓글 추가
	@Modifying // 수정
	@Query(value = "insert into tbl_comment3(bnum, content, regdate) "
			+ "values(?1, ?2, now())", nativeQuery = true) // 내가 직접 쿼리 작성 가능
	public void commentInsert(Long bnum, String content);
	
	// 댓글 리스트
	@Query("select sc from tbl_comment3 sc where bnum = ?1")
	public List<Comment> findByBnum(Long bnum);
}
