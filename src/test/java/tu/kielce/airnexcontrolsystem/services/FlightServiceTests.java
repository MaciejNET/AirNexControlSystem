package tu.kielce.airnexcontrolsystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tu.kielce.airnexcontrolsystem.commands.ChangeDepartureTimeCommand;
import tu.kielce.airnexcontrolsystem.commands.CreateFlightCommand;
import tu.kielce.airnexcontrolsystem.dto.FlightDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.entities.Flight;
import tu.kielce.airnexcontrolsystem.entities.Plane;
import tu.kielce.airnexcontrolsystem.exceptions.AirportNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.FlightNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.repositories.AirportRepository;
import tu.kielce.airnexcontrolsystem.repositories.FlightRepository;
import tu.kielce.airnexcontrolsystem.repositories.PlaneRepository;
import tu.kielce.airnexcontrolsystem.services.implementations.FlightServiceImpl;
import tu.kielce.airnexcontrolsystem.value_objects.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Mariusz Ignaciuk
 */
class FlightServiceTests {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private EntityMapper entityMapper;

    @Mock
    private PlaneRepository planeRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_NonExistingFlight_ReturnsNull() {
        // Arrange
        Long flightId = 1L;

        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        // Act
        FlightDto result = flightService.getById(flightId);

        // Assert
        assertNull(result);
    }

    @Test
    void createFlight_InvalidAirport_ThrowsException() {
        // Arrange
        CreateFlightCommand createFlightCommand = new CreateFlightCommand(
                "FL-1123",
                "InvalidAirport",
                "AirportB",
                LocalDateTime.of(2024, 1, 18, 12, 0),
                LocalDateTime.of(2024, 1, 18, 15, 0),
                1L,
                "AirlineX",
                new BigDecimal("200.50")
        );

        when(airportRepository.findByName(any(Name.class))).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AirportNotExistsException.class, () -> flightService.createFlight(createFlightCommand));
    }

    @Test
    void updateDepartureTime_ValidCommand_DepartureTimeUpdated() {
        // Arrange
        Long flightId = 1L;
        ChangeDepartureTimeCommand changeDepartureTimeCommand = new ChangeDepartureTimeCommand(LocalDateTime.now().plusYears(2L));


        FlightNumber flightNumber = new FlightNumber("AB-1234");
        LocalDateTime departureTime = LocalDateTime.now().plusHours(2);
        LocalDateTime arrivalTime = departureTime.plusHours(3);

        Airport departureAirport = new Airport(new Name("Departure Airport"), new City("CityA"), new Country("CountryA"));
        Airport arrivalAirport = new Airport(new Name("Arrival Airport"), new City("CityB"), new Country("CountryB"));
        Airline airline = new Airline(new Name("Example Airline"));
        Plane plane = new Plane(new Model("Boeing 737"), airline, 150);

        BigDecimal price = new BigDecimal("250.00");

        Flight newFlight = new Flight(flightNumber, departureTime, arrivalTime, departureAirport, arrivalAirport, plane, airline, price);
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(newFlight));

        // Act
        assertDoesNotThrow(() -> flightService.updateDepartureTime(flightId, changeDepartureTimeCommand));

        // Assert
        verify(flightRepository).save(newFlight);
    }

    @Test
    void updateDepartureTime_InvalidFlightId_ThrowsException() {
        // Arrange
        Long invalidFlightId = 999L;
        ChangeDepartureTimeCommand changeDepartureTimeCommand = new ChangeDepartureTimeCommand(LocalDateTime.now());

        when(flightRepository.findById(invalidFlightId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FlightNotExistsException.class, () -> flightService.updateDepartureTime(invalidFlightId, changeDepartureTimeCommand));
    }

}


