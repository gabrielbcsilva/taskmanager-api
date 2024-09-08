package io.gabriel.taskmanager.model.enums;

public enum Status {
    AWAITING("awaiting"),
    IN_PROGRESS("in progress"),
    DONE("done"),
    CANCELED("canceled");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue(Object... args) {
        return String.format(value, args);
    }
}
