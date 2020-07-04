package com.luxoft.luxofttecnhicaltask.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(name="ITEMS")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "FILE_ID")
    private File fileId;

    @Column(name = "PRIMARY_KEY")
    private String primaryKey;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UPDATED_TIMESTAMP")
    private String updatedTimestamp;
}
