package br.com.alysongustavoti.backendtodo.model;

public enum Status {

    CREATED("Created"),
    PENDING("Pending"),
    CANCELED("Canceled"),
    COMPLETED("Completed");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
