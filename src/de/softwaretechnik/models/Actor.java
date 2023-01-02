package de.softwaretechnik.models;

/**
 * Model of Actor according to the needs within the program.
 * @param actorID ID according to the database
 * @param firstName first name
 * @param lastName last name
 */
public record Actor(
        int actorID,
        String firstName,
        String lastName
) {
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
