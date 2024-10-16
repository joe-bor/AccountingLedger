package com.pluralsight;

public record Product(String description, String vendor, float price) {

    @Override
    public String toString() {
        return String.format("Description: %s || Vendor: %s || Price: %.2f || ", this.description, this.vendor(), this.price);
    }
}

