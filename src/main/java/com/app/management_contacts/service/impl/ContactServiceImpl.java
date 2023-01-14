package com.app.management_contacts.service.impl;

import com.app.management_contacts.dto.mapper.ContactMapper;
import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.exception.NotFoundException;
import com.app.management_contacts.model.Contact;
import com.app.management_contacts.repository.ContactRepository;
import com.app.management_contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public ContactDto updateContact(Long id, ContactReq contactReq) throws NotFoundException {

        boolean existContact = contactRepository.existsByIdAndRemoveIsFalse(id);
        if (!existContact) {
            throw new NotFoundException("Contact not found");
        }

        // update detail contact
        Contact contact = toModel(contactReq).setId(id);
        contact = contactRepository.save(contact);

        return toDto(contact);
    }

    @Override
    public Page<ContactDto> getContactList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Contact> contactPage = contactRepository.findAllByRemoveIsFalse(pageable);
        return contactPage.map(this::toDto);
    }

    @Override
    public ContactDto getContactById(Long id) {
        Contact contact = contactRepository.findByIdAndRemoveIsFalse(id);
        if (contact == null) {
            logger.info("[getContactById] not found contact_id: {}", id);
        }
        return toDto(contact);
    }

    private Contact toModel(ContactReq from) {
        return ContactMapper.INSTANCE.toModel(from);
    }

    private ContactDto toDto(Contact from) {
        return ContactMapper.INSTANCE.toDto(from);
    }
}
