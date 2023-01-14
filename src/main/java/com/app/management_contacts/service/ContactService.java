package com.app.management_contacts.service;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.exception.NotFoundException;

public interface ContactService {

    ContactDto addContact(ContactReq contactReq);

    ContactDto updateContact(Long id, ContactReq contactReq) throws NotFoundException;
}
