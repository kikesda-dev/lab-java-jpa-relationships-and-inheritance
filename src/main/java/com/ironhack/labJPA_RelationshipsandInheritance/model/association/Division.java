package com.ironhack.labJPA_RelationshipsandInheritance.model.association;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String district;

    @OneToOne
    @JoinColumn(name = "president_id")
    private Member president;

    @OneToMany(mappedBy = "division", fetch = FetchType.LAZY)
    private List<Member> members;

    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;
}