package com.app.management_contacts.service;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.model.Contact;
import com.app.management_contacts.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    private AutoCloseable autoCloseable;

    @Mock
    private ContactService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        contactRepository.deleteAll();
    }

//    @Test
    void canAddContact() {
        //given
        String firstName = "Duy";
        String email = "leduy@gmail.com";
        ContactReq contactReq = new ContactReq()
                .setFirstName(firstName)
                .setLastName("Le")
                .setEmail(email)
                .setPhoneNumber("+8487366728")
                .setPostalAddress("hcm city");

        //when
        ContactDto contactDto = underTest.addContact(contactReq);

        //then
        assertEquals(firstName, contactDto.getFirstName());
        assertEquals(email, contactDto.getEmail());
    }
//
//    @Test
//    void updateContact() {
//    }

//    @Test
//    void getContactList() {
//        Page<ContactDto> contactDtoPage = underTest.getContactList(null, null, 0, 20);
//        verify(contactRepository).findAllByName(null, null, PageRequest.of(0, 20));
//    }

//    @Test
//    void getContactById() {
//    }

//    @Test
//    void removeContact() {
//    }
}