package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

/*
 * 보통의 웹프로젝트는 db와 인터페이스를 하는 인스턴스와 통신을 하는 것을 이야기 하게되는데 
 * 그때 db 처리를 담당하는 인스턴스에 전달할 객체로 vo 클래스의 인스턴스를 전달하게 되고, 
 * 전달받은 객체는 ibatis를 이용해 vo를 전달하여 query 에 매핑시키게 됩니다.
 * table 을 vo 클래스라고 생각하면 됩니다. vo 인스턴스는 하나의 row 라고 생각할 수 있겠네요. 
 * 세션 클러스터링이나 타 시스템에 전송할 필요가 있거나 연계가 할때는 이 객체를 serialize 하기만 하면 됩니다. 
 * 송신단에서 자동으로 직렬화 해서 보낼 수 있게 되는거죠. 
 * 데이터 통신이 있어야 한다. 라는 전제가 붙는다면 vo를 전송하는구나 라고 생각하시면 됩니다
 */

@Data
public class BoardDTO { // VO : Value Object(값을 담는 객체, 다른 말로 DTO - Data Transfer Object : 데이터 전송용 객체) -> DB에 있는 테이블 컬럼 값을 java에서 객체로 다루기 위해 사용
	private int num;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hitcount;
	private int replyCnt;
}
