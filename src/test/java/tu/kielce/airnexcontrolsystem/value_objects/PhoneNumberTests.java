package tu.kielce.airnexcontrolsystem.value_objects;

import org.junit.jupiter.api.Test;
import tu.kielce.airnexcontrolsystem.exceptions.EmptyPhoneNumberException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidPhoneNumberException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTests {
    @Test
    public void testValidPhoneNumber() {
        // Arrange
        String value = "123456789";

        // Act
        PhoneNumber phoneNumber = new PhoneNumber(value);

        // Assert
        assertEquals(value, phoneNumber.value());
    }

    @Test
    public void testEmptyPhoneNumberThrowsException() {
        // Arrange
        String value = "";

        // Act and Assert
        assertThrows(EmptyPhoneNumberException.class, () -> new PhoneNumber(value));
    }

    @Test
    public void testNullPhoneNumberThrowsException() {
        // Arrange
        String value = null;

        // Act and Assert
        assertThrows(EmptyPhoneNumberException.class, () -> new PhoneNumber(value));
    }

    @Test
    public void testInvalidPhoneNumberThrowsException() {
        // Arrange
        String value = "1234567890";

        // Act and Assert
        assertThrows(InvalidPhoneNumberException.class, () -> new PhoneNumber(value));
    }
}
