package com.app.management_contacts.repository;

import com.app.management_contacts.model.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfExistContactId() {
        //given
        Long contactId = 0L;

        //when
        Boolean expected = underTest.existsByIdAndRemovedIsFalse(contactId);

        //then
        assertThat(expected).isFalse();
    }

    @Test
    void findAllByName() {
        //given
        List<Contact> contactList = Arrays.asList(
                new Contact().setFirstName("Duy").setLastName("Le").setEmail("leduy@gmail.com").setPhoneNumber("+8487366728").setPostalAddress("hcm city"),
                new Contact().setFirstName("Teo").setLastName("Nguyen").setEmail("nguyentai@gmail.com").setPhoneNumber("+8487123728").setPostalAddress("hn city"),
                new Contact().setFirstName("Duy").setLastName("Nguye").setEmail("nguyenduy@gmail.com").setPhoneNumber("+8487366670").setPostalAddress("dn city"),
                new Contact().setFirstName("Ti").setLastName("Vo").setEmail("voti@gmail.com").setPhoneNumber("+8487366231").setPostalAddress("hcm city")
        );
        underTest.saveAll(contactList);

        //when
        List<Contact> result = underTest.findAllByName("Duy", null, PageRequest.of(0, 100)).toList();

        //then
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), "Duy");
        assertEquals(result.get(1).getFirstName(), "Duy");
    }
}