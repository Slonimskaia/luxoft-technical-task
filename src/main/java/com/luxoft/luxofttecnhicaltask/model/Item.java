package com.luxoft.luxofttecnhicaltask.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name="ITEMS")
@Entity
public class Item {

    @Column(name="FILE_NAME")
    private String fileName;

    @Column(name="PRIMARY_KEY")
    private String primaryKey;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="UPDATED_TIMESTAMP")
    private String updatedTimestamp;
}
