package com.pensato.replicator.models;

import com.pensato.replicator.support.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "student")
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Student implements BaseEntity<Student, Long> {
    @Id
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    private College college;

    @Override
    public Student createCopy() {
        return this.toBuilder().build();
    }

}
