package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commands.CreatePlaneCommand;
import tu.kielce.airnexcontrolsystem.dto.PlaneDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Plane;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.PlaneNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.repositories.PlaneRepository;
import tu.kielce.airnexcontrolsystem.services.PlaneService;
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
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final AirlineRepository airlineRepository;
    private final EntityMapper entityMapper;
    private static final Logger logger = LogManager.getLogger(PlaneServiceImpl.class);

    public PlaneServiceImpl(PlaneRepository planeRepository, AirlineRepository airlineRepository, EntityMapper entityMapper) {
        this.planeRepository = planeRepository;
        this.airlineRepository = airlineRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<PlaneDto> getAll() {
        logger.info("Getting all planes");
        Iterable<Plane> planes = planeRepository.findAll();
        if (!planes.iterator().hasNext()) {
            return new ArrayList<>();
        }

        List<PlaneDto> dtos = StreamSupport.stream(planes.spliterator(), false)
                .map(entityMapper::toDto)
                .toList();

        logger.info("Returning all planes");
        return dtos;
    }

    @Override
    public PlaneDto getById(final Long id) {
        logger.info("Getting plane by id: " + id);
        Optional<Plane> planeOptional = planeRepository.findById(id);
        if (planeOptional.isEmpty()) {
            return null;
        }

        Plane plane = planeOptional.get();
        logger.info("Returning plane by id: " + id);
        return entityMapper.toDto(plane);
    }

    @Override
    public void add(final CreatePlaneCommand command) {
        logger.info("Adding plane");
        Name name = new Name(command.airline());
        Optional<Airline> airlineOptional = airlineRepository.findByName(name);
        if (airlineOptional.isEmpty()) {
            throw new AirlineNotExistsException(command.airline());
        }
        Model model = new Model(command.model());
        Plane plane = new Plane(model, airlineOptional.get(), command.numberOfSeats());
        planeRepository.save(plane);
        logger.info("Added plane");
    }

    @Override
    public void delete(final Long id) {
        logger.info("Deleting plane");
        Optional<Plane> planeOptional = planeRepository.findById(id);
        if (planeOptional.isEmpty()) {
            throw new PlaneNotExistsException(id);
        }

        Plane plane = planeOptional.get();
        planeRepository.delete(plane);
        logger.info("Deleted plane");
    }
}
