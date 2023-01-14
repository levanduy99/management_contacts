package com.app.management_contacts.service;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;

public interface ContactService {
    ContactDto addContact(ContactReq contactReq);
}
