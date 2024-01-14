package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commends.CreateAirlineCommand;
import tu.kielce.airnexcontrolsystem.commends.UpdateAirlineNameCommand;
import tu.kielce.airnexcontrolsystem.commends.UpdateAirportNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirportAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirportNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.services.AirlineService;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


/**
 * @author Mariusz Ignaciuk
 */
@Service
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final EntityMapper entityMapper;

    private static final Logger logger = LogManager.getLogger(AirlineServiceImpl.class);

    public AirlineServiceImpl(AirlineRepository airlineRepository, EntityMapper entityMapper) {
        this.airlineRepository = airlineRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<AirlineDto> getAll() {
        logger.info("Getting all Airline");
        Iterable<Airline> airlines = airlineRepository.findAll();
        if (!airlines.iterator().hasNext()) {
            return new ArrayList<>();
        }
        List<AirlineDto> dtos = StreamSupport.stream(airlines.spliterator(), false).map(entityMapper::toDto).toList();
        logger.info("To all Airline");
        return dtos;
    }

    @Override
    public AirlineDto getById(final Long id) {
        logger.info("Getting Airline by id: ", + id);
        Optional<Airline> airlineOptional = airlineRepository.findById(id);
        if (airlineOptional.isEmpty()) {
            return null;
        }
        Airline airline = airlineOptional.get();
        logger.info("Returning airline by id: " + id);
        return entityMapper.toDto(airline);
    }

    @Override
    public void add(final CreateAirlineCommand command) {
        logger.info("Adding airline");
        Name name = new Name(command.name());
        Optional<Airline> airlineOptional = airlineRepository.findByName(name);
        if (airlineOptional.isPresent()) {
            throw new AirportAlreadyExistsException(command.name());
        }

        Airline airline = new Airline(name);
        airlineRepository.save(airline);
        logger.info("Added airline");
    }

    @Override
    public void delete(final Long id) {
        logger.info("Delete airline");
        Optional<Airline> optionalAirline = airlineRepository.findById(id);
        if (optionalAirline.isEmpty()){
            throw new AirlineNotExistsException(id);
        }
    }
    @Override
    public void updateName(Long id, UpdateAirlineNameCommand command) {
        logger.info("Updating airline name");
        Optional<Airline> airlineOptional = airlineRepository.findById(id);
        if (airlineOptional.isEmpty()) {
            throw new AirlineNotExistsException(id);
        }
        Name name = new Name(command.name());
        Optional<Airline> airlineToCheckOptional = airlineRepository.findByName(name);
        if (airlineToCheckOptional.isPresent()){
            throw new AirlineAlreadyExistsException(command.name());
        }
        Airline airline = airlineOptional.get();
        airline.changeName(name);
        airlineRepository.save(airline);
        logger.info("Updated airline name");
    }
}
