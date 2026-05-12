package com.ironhack.labJPA_RelationshipsandInheritance.model.contact;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    private String salutation;
    private String firstName;
    private String middleName;
    private String lastName;
}