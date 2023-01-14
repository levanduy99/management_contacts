package com.app.management_contacts.repository;

import com.app.management_contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByIdAndRemoveIsFalse(Long id);
}
