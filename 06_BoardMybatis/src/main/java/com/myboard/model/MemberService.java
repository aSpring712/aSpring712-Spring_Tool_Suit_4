package com.myboard.model;

import com.myboard.dto.MemberDTO;

public interface MemberService { // ������ Mapper�� ����
	// �߰�
	public void join(MemberDTO member);
	
	// id �ߺ�Ȯ�� -> boolean ������ �����ص� ��
	public int idCheck(String id);
	
	// �α��� üũ -> �α��� �� �� �ִ���, ȸ���� �ƴ��� �� Ȯ��(boolean������ ��ȯ ����)
	public MemberDTO loginCheck(String id);
	
}
