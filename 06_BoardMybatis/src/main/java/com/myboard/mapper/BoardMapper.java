package com.myboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.FileBoardDTO;

public interface BoardMapper {
	// �߰�
	public void insert(BoardDTO board);
	// ����Ʈ
	public List<BoardDTO> findAll(HashMap<String, Object> hm); // xml �������� id��, parameter ���� 1������ �ؼ� 2���� ���� �����ϱ� ���� HashMap �̿�(�Ǵ� ��ü ���)�ؼ� ����
	// �󼼺���
	public BoardDTO findByNum(int num);
	// ����
	public void update(BoardDTO board);
	// ����
	public void delete(int num);
	// ����
	public int getCount(HashMap<String, Object> hm);

	// replyCnt ���� ����
	public void updateReplyCnt(@Param("bnum") int bnum, @Param("amount") int amount); // xml�� 2�� �̻��� �� ���� �� HashMap / ��ü / �Ǵ� Param���� ����
	
	// hitCount �� ����
	public void updateHitcount(int num);
	
	// ���� ���ε�
	public void fileInsert(FileBoardDTO fboard);
	// ���� ����Ʈ ����
	public List<FileBoardDTO> fileList();
}
