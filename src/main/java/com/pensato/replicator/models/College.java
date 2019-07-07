package com.pensato.replicator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "college")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class College {
    @Id
    private Long id;
    private String name;
    private String city;
}
