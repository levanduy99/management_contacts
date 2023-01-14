package com.app.management_contacts.dto.mapper;

public interface GenericMapper<Dto, DtoReq, Entity> {

    Dto toDto(Entity from);

    Entity toModel(DtoReq from);
}
