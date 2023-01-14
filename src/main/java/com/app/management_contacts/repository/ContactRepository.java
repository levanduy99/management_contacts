package com.app.management_contacts.repository;

import com.app.management_contacts.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByIdAndRemovedIsFalse(Long id);

    Contact findByIdAndRemovedIsFalse(Long id);

    Page<Contact> findAllByRemovedIsFalse(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Contact c SET c.removed = true WHERE c.id = :id")
    void updateRemovedTrue(@Param("id") Long id);
}
