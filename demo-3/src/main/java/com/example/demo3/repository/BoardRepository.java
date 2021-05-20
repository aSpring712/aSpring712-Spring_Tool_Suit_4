package com.example.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
