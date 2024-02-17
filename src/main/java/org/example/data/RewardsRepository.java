package org.example.data;

import org.example.models.Rewards;
import org.example.models.Rewards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends CrudRepository<Rewards, Long> {
    List<Rewards> findByName(String name);
}