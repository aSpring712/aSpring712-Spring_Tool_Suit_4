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
	private CommentMapper cmapper; // Service���� �ٷ� mapper �θ���(dao������ ����)
	
	@Autowired
	private BoardMapper bmapper; // replyCnt ó�� ����
	
	@Transactional // �ΰ��� �ϳ��� ������ ó���� �Ǿ�� commit, �ϳ��� �ȵǸ� rollback
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
		// cnum�� ����� ���� bnum ���ؿ���
		CommentDTO cdto = cmapper.read(cnum);
		cmapper.del(cnum);
		bmapper.updateReplyCnt(cdto.getBnum(), -1);
	}

}
