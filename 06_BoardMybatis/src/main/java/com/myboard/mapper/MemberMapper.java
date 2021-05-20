package com.myboard.mapper;

import com.myboard.dto.MemberDTO;

public interface MemberMapper {
	// �߰�
	public void join(MemberDTO member);
	// id �ߺ�Ȯ�� -> boolean ������ �����ص� ��
	public int idCheck(String id);
	// �α��� üũ -> �α��� �� �� �ִ���, ȸ���� �ƴ��� �� Ȯ��(boolean������ ��ȯ ����)
	public MemberDTO loginCheck(String id);
}
