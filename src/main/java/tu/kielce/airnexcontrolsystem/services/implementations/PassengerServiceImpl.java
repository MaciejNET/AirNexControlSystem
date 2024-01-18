package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commands.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateEmailCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.exceptions.PassengerNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.PassengerWithGivenEmailAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.PassengerRepository;
import tu.kielce.airnexcontrolsystem.services.PassengerService;
import tu.kielce.airnexcontrolsystem.value_objects.Email;
import tu.kielce.airnexcontrolsystem.value_objects.FirstName;
import tu.kielce.airnexcontrolsystem.value_objects.LastName;
import tu.kielce.airnexcontrolsystem.value_objects.PhoneNumber;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * @author Julia Dziekańska
 */
@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final EntityMapper entityMapper;
    private final static Logger logger = LogManager.getLogger(PassengerServiceImpl.class);

    public PassengerServiceImpl(PassengerRepository passengerRepository, EntityMapper entityMapper) {
        this.passengerRepository = passengerRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public PassengerDto getPassenger(final Long id) {
        logger.info("Getting passenger with id: " + id);
        return passengerRepository.findById(id).map(entityMapper::toDto).orElse(null);
    }

    @Override
    public List<PassengerDto> getAllPassengers() {
        logger.info("Getting all passengers");
        Iterable<Passenger> passengers = passengerRepository.findAll();
        if (!passengers.iterator().hasNext()) {
            return null;
        }
        List<PassengerDto> dtos = StreamSupport.stream(passengers.spliterator(), false)
                .map(entityMapper::toDto)
                .toList();

        return dtos;
    }

    @Override
    public void createPassenger(final CreatePassengerCommand command) {
        logger.info("Creating passenger");
        FirstName firstName = new FirstName(command.firstName());
        LastName lastName = new LastName(command.lastName());
        Email email = new Email(command.email());
        PhoneNumber phoneNumber = new PhoneNumber(command.phoneNumber());
        LocalDate dateOfBirth = LocalDate.parse(command.dateOfBirth());
        if (passengerRepository.existsByEmail(email)) {
            throw new PassengerWithGivenEmailAlreadyExistsException(command.email());
        }

        Passenger passenger = new Passenger(firstName, lastName, dateOfBirth, phoneNumber, email);
        passengerRepository.save(passenger);
        logger.info("Passenger created");
    }

    @Override
    public void updatePassenger(final Long id, final UpdateEmailCommand command) {
        logger.info("Updating passenger with id: " + id);
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotExistsException(id));
        Email email = new Email(command.email());
        if (passengerRepository.existsByEmail(email)) {
            throw new PassengerWithGivenEmailAlreadyExistsException(command.email());
        }
        passenger.changeEmail(email);
        passengerRepository.save(passenger);
        logger.info("Passenger updated");
    }

    @Override
    public void deletePassenger(final Long id) {
        logger.info("Deleting passenger with id: " + id);
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotExistsException(id));
        passengerRepository.delete(passenger);
        logger.info("Passenger deleted");
    }
}
