package com.app.management_contacts.service;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.exception.NotFoundException;
import org.springframework.data.domain.Page;

public interface ContactService {

    ContactDto addContact(ContactReq contactReq);

    ContactDto updateContact(Long id, ContactReq contactReq) throws NotFoundException;

    Page<ContactDto> getContactList(String firstName, String lastName, int page, int size);

    ContactDto getContactById(Long id);

    void removeContact(Long id) throws NotFoundException;
}
