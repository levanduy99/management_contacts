package com.app.management_contacts.controller;

import com.app.management_contacts.model.Contact;
import com.app.management_contacts.repository.ContactRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostConstruct
    private void test() {
        System.out.println("test insert db");
        Contact contact = new Contact()
                .setFirstName("duy")
                .setLastName("le")
                .setEmail("duyle@gmail.com")
                .setPhoneNumber("0973566726")
                .setPostalAddress("hcmcity");
        contactRepository.save(contact);
        System.out.println("save test insert db");
    }
}
