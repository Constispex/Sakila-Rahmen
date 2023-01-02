package de.softwaretechnik.models;

/**
 * Category Model according to the needs of the program.
 * @param categoryID ID according to the database
 * @param name name of the category e.g. "Horror"
 */
public record Category(
        int categoryID,
        String name
) {
    @Override
    public String toString() {
        return name;
    }
}
