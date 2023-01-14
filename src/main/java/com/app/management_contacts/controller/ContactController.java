package com.app.management_contacts.controller;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.dto.response.MessageResponse;
import com.app.management_contacts.exception.ApiException;
import com.app.management_contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("")
    public MessageResponse<ContactDto> addContact(@RequestBody @Valid ContactReq contactReq) {
        ContactDto result = contactService.addContact(contactReq);
        return result != null ? MessageResponse.ofSuccess(result) :
                MessageResponse.ofError(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.name(), null);
    }

    @PutMapping("/{id}")
    public MessageResponse<ContactDto> updateContact(
            @PathVariable Long id,
            @RequestBody @Valid ContactReq contactReq
    ) throws ApiException {

        ContactDto result = contactService.updateContact(id, contactReq);

        return MessageResponse.ofSuccess(result);
    }

    @GetMapping("")
    public MessageResponse<ContactDto> getContactList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {

        Page<ContactDto> result = contactService.getContactList(page, size);

        return MessageResponse.ofSuccess(result);
    }

    @GetMapping("/{id}")
    public MessageResponse<ContactDto> getContact(@PathVariable Long id) {
        return MessageResponse.ofSuccess(contactService.getContactById(id));
    }
}
