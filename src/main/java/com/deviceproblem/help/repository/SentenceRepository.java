package com.deviceproblem.help.repository;

import com.deviceproblem.help.model.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<Sentence,Integer> {
}
