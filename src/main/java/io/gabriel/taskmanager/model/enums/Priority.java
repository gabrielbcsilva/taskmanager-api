package io.gabriel.taskmanager.model.enums;

public enum Priority {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue(Object... args) {
        return String.format(value, args);
    }

}
