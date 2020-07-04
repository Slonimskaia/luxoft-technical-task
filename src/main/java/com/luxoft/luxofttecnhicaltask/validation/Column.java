package com.luxoft.luxofttecnhicaltask.validation;

public enum Column {
    PRIMARY_KEY(0),
    NAME(1),
    DESCRIPTION(2),
    UPDATED_TIMESTAMP(3);

    private final int column;

    Column(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
