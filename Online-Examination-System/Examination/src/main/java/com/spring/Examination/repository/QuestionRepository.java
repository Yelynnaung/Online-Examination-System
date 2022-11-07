package com.spring.Examination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Examination.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
