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
    private int id;

    @Column(name = "NAME")
    private String name;

    public CsvFile() {
    }

    public CsvFile(String fileName) {
        this.name = fileName;
    }
}
