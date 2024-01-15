package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commends.ChangeDepartureTimeCommand;
import tu.kielce.airnexcontrolsystem.commends.ChangePriceCommand;
import tu.kielce.airnexcontrolsystem.commends.CreateFlightCommand;
import tu.kielce.airnexcontrolsystem.dto.FlightDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.entities.Flight;
import tu.kielce.airnexcontrolsystem.entities.Plain;
import tu.kielce.airnexcontrolsystem.exceptions.AirportNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.repositories.AirportRepository;
import tu.kielce.airnexcontrolsystem.repositories.FlightRepository;
import tu.kielce.airnexcontrolsystem.repositories.PlainRepository;
import tu.kielce.airnexcontrolsystem.services.FlightService;
import tu.kielce.airnexcontrolsystem.specifications.FlightSpecification;
import tu.kielce.airnexcontrolsystem.value_objects.FlightNumber;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final EntityMapper entityMapper;
    private final PlainRepository plainRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final static Logger logger = LogManager.getLogger(FlightServiceImpl.class);

    public FlightServiceImpl(FlightRepository flightRepository, EntityMapper entityMapper, PlainRepository plainRepository, AirportRepository airportRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.entityMapper = entityMapper;
        this.plainRepository = plainRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
    }

    @Override
    public FlightDto getById(final Long id) {
        logger.info("Getting flight by id: " + id);
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isEmpty()) {
            logger.error("Flight with id: " + id + " not found");
            return null;
        }
        logger.info("Flight with id: " + id + " found");
        return flightOptional.map(entityMapper::toDto).orElse(null);
    }

    @Override
    public List<FlightDto> findFlights(final String departureAirport, final String arrivalAirport, final String departureTime, final String arrivalTime) {
        LocalDateTime departureTimeLocalDateTime = departureTime != null ? LocalDateTime.parse(departureTime) : null;
        LocalDateTime arrivalTimeLocalDateTime = arrivalTime != null ? LocalDateTime.parse(arrivalTime) : null;
        logger.info("Finding flights");
        FlightSpecification flightSpecification = new FlightSpecification(departureAirport, arrivalAirport, departureTimeLocalDateTime, arrivalTimeLocalDateTime);
        List<Flight> flights = flightRepository.findAll(flightSpecification);

        if (flights.isEmpty()) {
            logger.info("Flights not found");
            return Collections.emptyList();
        }

        logger.info("Flights found");
        return flights.stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createFlight(final CreateFlightCommand command) {
        logger.info("Creating flight");
        Airport departureAirport = airportRepository.findByName(new Name(command.departureAirport()))
                .orElseThrow(() -> new AirportNotExistsException(command.departureAirport()));

        Airport arrivalAirport = airportRepository.findByName(new Name(command.arrivalAirport()))
                .orElseThrow(() -> new AirportNotExistsException(command.arrivalAirport()));

        Airline airline = airlineRepository.findByName(new Name(command.airline()))
                .orElseThrow(() -> new AirportNotExistsException(command.airline()));

        Plain plain = plainRepository.findById(command.plainId())
                .orElseThrow(() -> new AirportNotExistsException(command.plainId()));

        FlightNumber flightNumber = new FlightNumber(command.flightNumber());
        Flight flight = new Flight(flightNumber,
                command.departureTime(),
                command.arrivalTime(),
                departureAirport,
                arrivalAirport,
                plain,
                airline,
                command.price());

        flightRepository.save(flight);
        logger.info("Flight created");
    }

    @Override
    public void updateDepartureTime(final Long id, final ChangeDepartureTimeCommand command) {
        logger.info("Updating departure time");
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new AirportNotExistsException(id));

        flight.changeDepartureTime(command.departureTime());
        flightRepository.save(flight);
        logger.info("Departure time updated");
    }

    @Override
    public void updatePrice(final Long id, final ChangePriceCommand command) {
        logger.info("Updating price");
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new AirportNotExistsException(id));

        flight.changePrice(command.price());
        flightRepository.save(flight);
        logger.info("Price updated");
    }

    @Override
    public void deleteFlight(final Long id) {
        logger.info("Deleting flight");
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new AirportNotExistsException(id));
        flightRepository.delete(flight);
        logger.info("Flight deleted");
    }
}
