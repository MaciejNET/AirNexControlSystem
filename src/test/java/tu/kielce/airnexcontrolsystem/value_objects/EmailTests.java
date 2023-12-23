package tu.kielce.airnexcontrolsystem.value_objects;

import org.junit.jupiter.api.Test;
import tu.kielce.airnexcontrolsystem.exceptions.EmptyEmailException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidEmailException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTests {
    @Test
    public void testValidEmail() {
        // Arrange
        String value = "test@example.com";

        // Act
        Email email = new Email(value);

        // Assert
        assertEquals(value, email.value());
    }

    @Test
    public void testEmptyEmailThrowsException() {
        // Arrange
        String value = "";

        // Act and Assert
        assertThrows(EmptyEmailException.class, () -> new Email(value));
    }

    @Test
    public void testNullEmailThrowsException() {
        // Arrange
        String value = null;

        // Act and Assert
        assertThrows(EmptyEmailException.class, () -> new Email(value));
    }

    @Test
    public void testInvalidEmailThrowsException() {
        // Arrange
        String value = "invalidEmail";

        // Act and Assert
        assertThrows(InvalidEmailException.class, () -> new Email(value));
    }
}
