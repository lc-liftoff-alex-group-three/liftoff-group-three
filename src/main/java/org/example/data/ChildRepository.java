package org.example.data;

import org.example.models.Child;
import org.example.models.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends CrudRepository<Child, Integer> {
    List<Child> findAllByParent(Parent parent);

}
