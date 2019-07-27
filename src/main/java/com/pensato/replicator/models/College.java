package com.pensato.replicator.models;

import com.pensato.replicator.support.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "college")
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class College implements BaseEntity<College, Long> {
    @Id
    private Long id;
    private String name;
    private String city;

    @Override
    public College createCopy() {
        return this.toBuilder().build();
    }
}
