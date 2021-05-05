package com.person.model;

import java.util.List;

public interface PersonDAO {
	// �߰�
	public void personInsert(PersonDTO person);
	// ��ü����
	public List<PersonDTO> personList();
	// �󼼺���
	public PersonDTO personView(String id);
	// �����ϱ�
	public void personUpdate(PersonDTO person);
	// �����ϱ�
	public void personDelete(String id);
}
