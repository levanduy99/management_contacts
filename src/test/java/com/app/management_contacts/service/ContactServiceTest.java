package com.app.management_contacts.service;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.exception.NotFoundException;
import com.app.management_contacts.model.Contact;
import com.app.management_contacts.repository.ContactRepository;
import com.app.management_contacts.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    private AutoCloseable autoCloseable;

    @InjectMocks
    private ContactServiceImpl underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        contactRepository.deleteAll();
    }

    @Test
    void canAddContactSuccess() {
        //given
        ContactReq contactReq = getContactReq();
        Mockito.when(contactRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        //when
        ContactDto contactDto = underTest.addContact(contactReq);

        //then
        assertEquals(getContactReq().getFirstName(), contactDto.getFirstName());
        assertEquals(getContactReq().getEmail(), contactDto.getEmail());
    }

    @Test
    void updateContactSuccess() throws NotFoundException {
        //given
        Mockito.when(contactRepository.existsByIdAndRemovedIsFalse(getContact().getId())).thenReturn(true);
        Mockito.when(contactRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        //when
        ContactDto contactDto = underTest.updateContact(getContact().getId(), getContactReq());

        //then
        assertEquals(getContactReq().getFirstName(), contactDto.getFirstName());
        assertEquals(getContactReq().getEmail(), contactDto.getEmail());
    }

    @Test
    void updateContactFailureWhenContactNotFound() {
        //given
        Mockito.when(contactRepository.existsByIdAndRemovedIsFalse(getContact().getId())).thenReturn(false);

        //when
        //then
        assertThatThrownBy(() -> underTest.updateContact(getContact().getId(), getContactReq()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Contact not found");
    }

    @Test
    void getContactList() {
        //given
        Mockito.when(contactRepository.findAllByName(any(), any(), any())).thenReturn(new PageImpl<>(List.of(getContact())));

        //when
        Page<ContactDto> contactDtoPage = underTest.getContactList(null, null, 0, 20);

        //then
        assertFalse(contactDtoPage.isEmpty());
    }

    @Test
    void getContactById() {
        //given
        Mockito.when(contactRepository.findByIdAndRemovedIsFalse(any())).thenReturn(getContact());

        //when
        ContactDto contactDto = underTest.getContactById(getContact().getId());

        //then
        assertEquals(getContact().getId(), contactDto.getId());
    }

//    @Test
//    void removeContact() {
//    }

    private ContactReq getContactReq() {
        return new ContactReq()
                .setFirstName("Duy")
                .setLastName("Le")
                .setEmail("leduy@gmail.com")
                .setPhoneNumber("+8487366728")
                .setPostalAddress("hcm city");
    }

    private Contact getContact() {
        return new Contact()
                .setId(1L)
                .setFirstName("Duy")
                .setLastName("Le")
                .setEmail("leduy@gmail.com")
                .setPhoneNumber("+8487366728")
                .setPostalAddress("hcm city");
    }
}
