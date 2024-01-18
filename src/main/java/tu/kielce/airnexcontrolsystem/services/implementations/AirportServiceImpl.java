package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commands.CreateAirportCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirportNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.exceptions.AirportAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirportNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirportRepository;
import tu.kielce.airnexcontrolsystem.services.AirportService;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.Country;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author Paweł Dostal
 */
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final EntityMapper entityMapper;
    private static final Logger logger = LogManager.getLogger(PlaneServiceImpl.class);

    public AirportServiceImpl(AirportRepository airportRepository, EntityMapper entityMapper) {
        this.airportRepository = airportRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<AirportDto> getAll() {
        logger.info("Getting all airports");
        Iterable<Airport> airports = airportRepository.findAll();
        if (!airports.iterator().hasNext()) {
            return new ArrayList<>();
        }

        List<AirportDto> dtos = StreamSupport.stream(airports.spliterator(), false)
                .map(entityMapper::toDto)
                .toList();

        logger.info("Returning all airports");
        return dtos;
    }

    public AirportDto getById(final Long id) {
        logger.info("Getting airport by id: " + id);
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isEmpty()) {
            return null;
        }

        Airport airport = airportOptional.get();
        logger.info("Returning airport by id: " + id);
        return entityMapper.toDto(airport);
    }

    @Override
    public void add(CreateAirportCommand command) {
        logger.info("Adding airport");
        Name name = new Name(command.name());
        Optional<Airport> airportOptional = airportRepository.findByName(name);
        if (airportOptional.isPresent()) {
            throw new AirportAlreadyExistsException(command.name());
        }

        City city = new City(command.city());
        Country country = new Country(command.country());
        Airport airport = new Airport(name, city, country);
        airportRepository.save(airport);
        logger.info("Added airport");
    }

    @Override
    public void delete(final Long id) {
        logger.info("Deleting airport");
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isEmpty()) {
            throw new AirportNotExistsException(id);
        }

        Airport airport = airportOptional.get();
        airportRepository.delete(airport);
        logger.info("Deleted airport");
    }

    @Override
    public void updateName(Long id, UpdateAirportNameCommand command) {
        logger.info("Updating airport name");
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isEmpty()) {
            throw new AirportNotExistsException(id);
        }
        Name name = new Name(command.name());
        Optional<Airport> airportToCheckOptional = airportRepository.findByName(name);
        if (airportToCheckOptional.isPresent()){
            throw new AirportAlreadyExistsException(command.name());
        }
        Airport airport = airportOptional.get();
        airport.changeName(name);
        airportRepository.save(airport);
        logger.info("Updated airport name");
    }
}
