package com.app.management_contacts.dto.mapper;

import com.app.management_contacts.dto.model.ContactDto;
import com.app.management_contacts.dto.request.ContactReq;
import com.app.management_contacts.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper extends GenericMapper<ContactDto, ContactReq, Contact> {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
}
