package com.myboard.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myboard.dto.CommentDTO;
import com.myboard.mapper.BoardMapper;
import com.myboard.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper cmapper; // Service에서 바로 mapper 부르기(dao만들지 않음)
	
	@Autowired
	private BoardMapper bmapper; // replyCnt 처리 위해
	
	@Transactional // 두개가 하나의 단위로 처리가 되어야 commit, 하나라도 안되면 rollback
	@Override
	public void insert(CommentDTO comment) {
		cmapper.insert(comment);
		bmapper.updateReplyCnt(comment.getBnum(), 1);
	}

	@Override
	public List<CommentDTO> getList(int bnum) {
		return cmapper.getList(bnum);
	}

	@Override
	@Transactional
	public void delete(int cnum) {
		// cnum을 지우기 전에 bnum 구해오기
		CommentDTO cdto = cmapper.read(cnum);
		cmapper.del(cnum);
		bmapper.updateReplyCnt(cdto.getBnum(), -1);
	}

}
