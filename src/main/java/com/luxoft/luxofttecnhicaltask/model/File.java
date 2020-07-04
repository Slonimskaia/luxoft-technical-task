package com.luxoft.luxofttecnhicaltask.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name="FILES")
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FILE_NAME")
    private String fileName;
}
