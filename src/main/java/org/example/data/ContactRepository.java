package org.example.data;

import org.example.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
//    Optional<Contact> findByEmail(String email);
}
