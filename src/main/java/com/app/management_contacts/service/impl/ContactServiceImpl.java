package com.app.management_contacts.service.impl;

import com.app.management_contacts.dto.mapper.ContactMapper;
import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.model.Contact;
import com.app.management_contacts.repository.ContactRepository;
import com.app.management_contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactDto addContact(ContactReq contactReq) {

        Contact contact = null;
        try {
            contact = contactRepository.save(toModel(contactReq));
        } catch (Exception e) {
            logger.error("[addContact] Exception: {}", e.getMessage());
        }

        return contact != null ? toDto(contact) : null;
    }

    private Contact toModel(ContactReq from) {
        return ContactMapper.INSTANCE.toModel(from);
    }

    private ContactDto toDto(Contact from) {
        return ContactMapper.INSTANCE.toDto(from);
    }
}
