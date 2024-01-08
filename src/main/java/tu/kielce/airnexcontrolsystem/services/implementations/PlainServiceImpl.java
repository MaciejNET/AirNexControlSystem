package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commends.CreatePlainCommand;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Plain;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.PlainNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.repositories.PlainRepository;
import tu.kielce.airnexcontrolsystem.services.PlainService;
import tu.kielce.airnexcontrolsystem.value_objects.Model;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author Maciej Deroń
 */
@Service
public class PlainServiceImpl implements PlainService {
    private final PlainRepository plainRepository;
    private final AirlineRepository airlineRepository;
    private final EntityMapper entityMapper;
    private static final Logger logger = LogManager.getLogger(PlainServiceImpl.class);

    public PlainServiceImpl(PlainRepository plainRepository, AirlineRepository airlineRepository, EntityMapper entityMapper) {
        this.plainRepository = plainRepository;
        this.airlineRepository = airlineRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<PlainDto> getAll() {
        logger.info("Getting all plains");
        Iterable<Plain> plains = plainRepository.findAll();
        if (!plains.iterator().hasNext()) {
            return new ArrayList<>();
        }

        List<PlainDto> dtos = StreamSupport.stream(plains.spliterator(), false)
                .map(entityMapper::toDto)
                .toList();

        logger.info("Returning all plains");
        return dtos;
    }

    @Override
    public PlainDto getById(final Long id) {
        logger.info("Getting plain by id: " + id);
        Optional<Plain> plainOptional = plainRepository.findById(id);
        if (plainOptional.isEmpty()) {
            return null;
        }

        Plain plain = plainOptional.get();
        logger.info("Returning plain by id: " + id);
        return entityMapper.toDto(plain);
    }

    @Override
    public void add(final CreatePlainCommand command) {
        logger.info("Adding plain");
        Name name = new Name(command.airline());
        Optional<Airline> airlineOptional = airlineRepository.findByName(name);
        if (airlineOptional.isEmpty()) {
            throw new AirlineNotExistsException(command.airline());
        }
        Model model = new Model(command.model());
        Plain plain = new Plain(model, airlineOptional.get(), command.numberOfSeats());
        plainRepository.save(plain);
        logger.info("Added plain");
    }

    @Override
    public void delete(final Long id) {
        logger.info("Deleting plain");
        Optional<Plain> plainOptional = plainRepository.findById(id);
        if (plainOptional.isEmpty()) {
            throw new PlainNotExistsException(id);
        }

        Plain plain = plainOptional.get();
        plainRepository.delete(plain);
        logger.info("Deleted plain");
    }
}
