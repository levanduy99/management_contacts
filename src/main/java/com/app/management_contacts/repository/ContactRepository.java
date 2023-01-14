package com.app.management_contacts.repository;

import com.app.management_contacts.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByIdAndRemoveIsFalse(Long id);

    Contact findByIdAndRemoveIsFalse(Long id);

    Page<Contact> findAllByRemoveIsFalse(Pageable pageable);
}
