package com.deviceproblem.help.repository;

import com.deviceproblem.help.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findAllByQuestionId(int id);
}
