package com.deviceproblem.help.repository;

import com.deviceproblem.help.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
