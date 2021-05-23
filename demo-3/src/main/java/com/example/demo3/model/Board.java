package com.example.demo3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data // view에 뿌리거나 db에 값을 가져오거나 할 때 사용
@Entity(name="tbl_board3") // Data, Entity(단순히 data mapping 용도) 같이 적는 것은 위험 -> 분리
public class Board {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num; // num과 Comment의 bnum과 관계성
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp // 시, 분, 초
	@Temporal(TemporalType.TIMESTAMP) // 날짜형이 자동적으로 들어감 -> util의 date와 형태가 맞지 않아서 써줌(db와 유형을 맞추기 위해)
	@Column(name = "regdate")
	private Date regdate;
	private Long hitcount;
	private Long replyCnt;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 얘가 여러개의 comment를 가짐, 부모 글에 변동이 생기면 다 적용되도록 설정
	@JsonIgnoreProperties("board") // json 형태로 받아올 때 얘는 무시?
	private List<Comment> comments; // 게시글 하나는 여러 개의 댓글을 가짐
	
	@PrePersist
	public void prePersist() {
		this.hitcount = this.hitcount == null ? 0 : this.hitcount; // hitcount가 null이면 기본값으로 0을 입력
		this.replyCnt = this.replyCnt == null ? 0 : this.replyCnt;
	}
}
