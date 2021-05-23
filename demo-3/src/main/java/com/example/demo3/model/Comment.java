package com.example.demo3.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbl_comment3")
public class Comment {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnum;
	private Long userid; // 원래 연결 시 외래키로 잡아주어야 함
	private String content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate;
	
	@ManyToOne
	@JoinColumn(name = "bnum") // join된 column의 이름을 bnum이라고 함
	private Board board; // 여러 개의 댓글이 게시글 하나에 달림 -> 이것과 tbl_board 테이블 연결
}
