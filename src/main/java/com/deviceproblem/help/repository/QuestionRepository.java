package com.deviceproblem.help.repository;

import com.deviceproblem.help.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategoryName(String name);
    Question findOneById(int id);
}
