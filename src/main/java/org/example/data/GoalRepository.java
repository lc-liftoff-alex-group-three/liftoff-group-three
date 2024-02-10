package org.example.data;

import org.example.models.Goals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GoalRepository extends CrudRepository<Goals, Integer> {
}
