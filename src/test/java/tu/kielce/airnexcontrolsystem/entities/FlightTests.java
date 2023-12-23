package tu.kielce.airnexcontrolsystem.entities;

import org.junit.jupiter.api.Test;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidArrivalException;
import tu.kielce.airnexcontrolsystem.exceptions.PastDepartureException;
import tu.kielce.airnexcontrolsystem.value_objects.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightTests {
    @Test
    public void testChangePrice() {
        // Arrange
        FlightNumber flightNumber = new FlightNumber("FN-1234");
        LocalDateTime departureTime = LocalDateTime.now().plusHours(2);
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(4);
        Airport departureAirport = new Airport(new Name("Departure Airport"), new City("City"), new Country("Country"));
        Airport arrivalAirport = new Airport(new Name("Arrival Airport"), new City("City"), new Country("Country"));
        Airline airline = new Airline(new Name("Airline"));
        Plain plain = new Plain(new Model("Model"), airline, 100);
        BigDecimal originalPrice = new BigDecimal("100.00");
        BigDecimal newPrice = new BigDecimal("200.00");
        Flight flight = new Flight(flightNumber, departureTime, arrivalTime, departureAirport, arrivalAirport, plain, airline, originalPrice);

        // Act
        flight.changePrice(newPrice);

        // Assert
        assertEquals(newPrice, flight.getPrice());
    }

    @Test
    public void testGetFlightDuration() {
        // Arrange
        FlightNumber flightNumber = new FlightNumber("FN-1234");
        LocalDateTime departureTime = LocalDateTime.now().plusHours(2);
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(4);
        Airport departureAirport = new Airport(new Name("Departure Airport"), new City("City"), new Country("Country"));
        Airport arrivalAirport = new Airport(new Name("Arrival Airport"), new City("City"), new Country("Country"));
        Airline airline = new Airline(new Name("Airline"));
        Plain plain = new Plain(new Model("Model"), airline, 100);
        BigDecimal price = new BigDecimal("100.00");
        Flight flight = new Flight(flightNumber, departureTime, arrivalTime, departureAirport, arrivalAirport, plain, airline, price);

        // Act
        LocalDateTime flightDuration = flight.getFlightDuration();

        // Assert
        assertEquals(2, flightDuration.getHour());
        assertEquals(0, flightDuration.getMinute());
    }

    @Test
    public void testConstructorThrowsPastDepartureException() {
        // Arrange
        FlightNumber flightNumber = new FlightNumber("FN-1234");
        LocalDateTime departureTime = LocalDateTime.now().minusHours(2);
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(4);
        Airport departureAirport = new Airport(new Name("Departure Airport"), new City("City"), new Country("Country"));
        Airport arrivalAirport = new Airport(new Name("Arrival Airport"), new City("City"), new Country("Country"));
        Airline airline = new Airline(new Name("Airline"));
        Plain plain = new Plain(new Model("Model"), airline, 100);
        BigDecimal price = new BigDecimal("100.00");

        // Act and Assert
        assertThrows(PastDepartureException.class, () -> new Flight(flightNumber, departureTime, arrivalTime, departureAirport, arrivalAirport, plain, airline, price));
    }

    @Test
    public void testConstructorThrowsInvalidArrivalException() {
        // Arrange
        FlightNumber flightNumber = new FlightNumber("FN-1234");
        LocalDateTime departureTime = LocalDateTime.now().plusHours(2);
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(1);
        Airport departureAirport = new Airport(new Name("Departure Airport"), new City("City"), new Country("Country"));
        Airport arrivalAirport = new Airport(new Name("Arrival Airport"), new City("City"), new Country("Country"));
        Airline airline = new Airline(new Name("Airline"));
        Plain plain = new Plain(new Model("Model"), airline, 100);
        BigDecimal price = new BigDecimal("100.00");

        // Act and Assert
        assertThrows(InvalidArrivalException.class, () -> new Flight(flightNumber, departureTime, arrivalTime, departureAirport, arrivalAirport, plain, airline, price));
    }

    @Test
    public void testChangeDepartureTimeThrowsPastDepartureException() {
        // Arrange
        FlightNumber flightNumber = new FlightNumber("FN-1234");
        LocalDateTime departureTime = LocalDateTime.now().plusHours(2);
        LocalDateTime arrivalTime = LocalDateTime.now().plusHours(4);
        Airport departureAirport = new Airport(new Name("Departure Airport"), new City("City"), new Country("Country"));
        Airport arrivalAirport = new Airport(new Name("Arrival Airport"), new City("City"), new Country("Country"));
        Airline airline = new Airline(new Name("Airline"));
        Plain plain = new Plain(new Model("Model"), airline, 100);
        BigDecimal price = new BigDecimal("100.00");
        Flight flight = new Flight(flightNumber, departureTime, arrivalTime, departureAirport, arrivalAirport, plain, airline, price);
        LocalDateTime newDepartureTime = LocalDateTime.now().minusHours(1);

        // Act and Assert
        assertThrows(PastDepartureException.class, () -> flight.changeDepartureTime(newDepartureTime));
    }
}
