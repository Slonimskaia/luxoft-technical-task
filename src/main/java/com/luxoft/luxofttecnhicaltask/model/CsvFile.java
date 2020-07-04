package com.luxoft.luxofttecnhicaltask.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name="CSV_FILES")
@Entity
public class CsvFile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FILE_NAME")
    private String fileName;


    public CsvFile(String fileName) {
        this.fileName = fileName;
    }
}
