package com.pluralsight;

public record Product(String description, String vendor, float price) {

    @Override
    public String toString() {
        return String.format("%.2f paid to %s for %s", this.price(), this.vendor(), this.description());
    }
}

