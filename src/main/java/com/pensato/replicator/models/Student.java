package com.pensato.replicator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="student")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    private College college;
}
