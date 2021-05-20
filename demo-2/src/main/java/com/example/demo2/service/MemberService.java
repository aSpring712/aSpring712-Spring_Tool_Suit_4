package com.example.demo2.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.example.demo2.model.JpaMember;
import com.example.demo2.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository; // Autowired 써도 되고, spring container -> 객체 생성/관리 (싱글톤으로)
	
	// 추가
	@Transactional
	public void save(JpaMember member) {
		memberRepository.save(member); // 미리 정의되어있는 메서드들.. save, findAll, ..
	}
	
	// 전체보기
	@Transactional
	public List<JpaMember> list() {
		return memberRepository.findAll();
	}
	
	// 상세보기
//	public JpaMember detail(Long id) { // 객체가 없으면 null로.. -> 프로그래머들이 null이 올 거라고 생각X..(Optional : java 1.8ver 부터 나옴 null이 올 것을 예상해서 null값을 처리)
//		return memberRepository.findById(id)
//				.orElseThrow(()-> {return new IllegalArgumentException("상세보기 실패"); // null이 return되면 오류 발생하니까 return 값이 optional ..?
//				}); // 예외처리 - 있으면 찾고, 없으면 메시지
//	}
	
	// 상세보기, 수정 폼으로 이동
	@Transactional
	public JpaMember detail(Long id) { // 객체가 없으면 null로.. -> 프로그래머들이 null이 올 거라고 생각X..(Optional : java 1.8ver 부터 나옴 null이 올 것을 예상해서 null값을 처리)
		Optional<JpaMember> member = memberRepository.findById(id); // 기본값 : 객체 return 시 null을 방지하기 위해 optional 사용해서 방지 -> null이면 객체를 만들어서 return 시킨다.
		return member.get(); // null이 아니고 존재하면 .get()해서 달라
	}
	
	// 삭제
	@Transactional
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	
	// 수정(DB)
	@Transactional // commit이 처리됨 -> db에 값이 바뀜  ==> dirty checking
	public void update(JpaMember member) {
		// JapMamber m = memberRepository.findById(member.getId()).get();
		Optional<JpaMember> mem = memberRepository.findById(member.getId()); // 조회된 내용이 1차 캐시에서 저장되어 있음
		JpaMember m = mem.get(); // 1차 캐시에 있는 객체의 내용을 변경
		m.setAddr(member.getAddr()); // 캐시 값 바뀜
		m.setName(member.getName()); // 캐시 값 바뀜
		m.setEmail(member.getEmail()); // 캐시 값 바뀜
		
		// 1차 캐시의 값으로 db내용 바꿔야 함 -> flush 함수 호출 또는 commit 시키기 -> 트랜젝션 처리를 해주어야 값이 바뀜
		
//		memberRepository.saveAndFlush(member); ??
	}
}
