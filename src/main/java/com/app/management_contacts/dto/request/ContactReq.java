package com.app.management_contacts.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContactReq {

    @NotBlank(message = "First name may not be blank")
    @Length(max = 30)
    private String firstName;

    @NotBlank(message = "Last name may not be blank")
    @Length(max = 30)
    private String lastName;

    @Email
    @NotBlank(message = "Email may not be blank")
    private String email;

    @NotBlank(message = "Phone number may not be blank")
    @Pattern(regexp = "^\\+(?:[0-9]●?){6,14}[0-9]$") // International Phone Numbers
    private String phoneNumber;

    private String postalAddress;
}
