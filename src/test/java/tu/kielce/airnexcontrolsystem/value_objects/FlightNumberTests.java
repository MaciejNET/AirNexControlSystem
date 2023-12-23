package tu.kielce.airnexcontrolsystem.value_objects;

import org.junit.jupiter.api.Test;
import tu.kielce.airnexcontrolsystem.exceptions.EmptyFlightNumberException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidFlightNumberException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightNumberTests {
    @Test
    public void testValidFlightNumber() {
        // Arrange
        String value = "AB-1234";

        // Act
        FlightNumber flightNumber = new FlightNumber(value);

        // Assert
        assertEquals(value, flightNumber.value());
    }

    @Test
    public void testEmptyFlightNumberThrowsException() {
        // Arrange
        String value = "";

        // Act and Assert
        assertThrows(EmptyFlightNumberException.class, () -> new FlightNumber(value));
    }

    @Test
    public void testNullFlightNumberThrowsException() {
        // Arrange
        String value = null;

        // Act and Assert
        assertThrows(EmptyFlightNumberException.class, () -> new FlightNumber(value));
    }

    @Test
    public void testInvalidFlightNumberThrowsException() {
        // Arrange
        String value = "SA1234";

        // Act and Assert
        assertThrows(InvalidFlightNumberException.class, () -> new FlightNumber(value));
    }
}
