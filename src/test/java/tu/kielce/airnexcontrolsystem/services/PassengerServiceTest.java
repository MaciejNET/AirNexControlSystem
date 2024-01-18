package tu.kielce.airnexcontrolsystem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tu.kielce.airnexcontrolsystem.commands.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateEmailCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.exceptions.PassengerNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.PassengerRepository;
import tu.kielce.airnexcontrolsystem.services.implementations.PassengerServiceImpl;
import tu.kielce.airnexcontrolsystem.value_objects.Email;
import tu.kielce.airnexcontrolsystem.value_objects.FirstName;
import tu.kielce.airnexcontrolsystem.value_objects.LastName;
import tu.kielce.airnexcontrolsystem.value_objects.PhoneNumber;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Test
    void getPassenger_WhenPassengerExists_ShouldReturnPassengerDto() {

        long passengerId = 1L;
        Passenger existingPassenger = new Passenger(
                new FirstName("John"),
                new LastName("Doe"),
                LocalDate.parse("1990-01-01"),
                new PhoneNumber("123456789"),
                new Email("john@example.com")
        );

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(existingPassenger));
        when(entityMapper.toDto(existingPassenger)).thenReturn(new PassengerDto());

        PassengerDto result = passengerService.getPassenger(passengerId);

        assertNotNull(result);

        verify(passengerRepository, times(1)).findById(passengerId);
        verify(entityMapper, times(1)).toDto(existingPassenger);
    }

    @Test
    void getAllPassengers_ShouldReturnListOfPassengerDtos() {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(new FirstName("John"), new LastName("Doe"), LocalDate.parse("1990-01-01"), new PhoneNumber("123456789"), new Email("john@example.com")));
        passengers.add(new Passenger(new FirstName("Jane"), new LastName("Doe"), LocalDate.parse("1990-01-01"), new PhoneNumber("987654321"), new Email("jane@example.com")));

        when(passengerRepository.findAll()).thenReturn(passengers);
        when(entityMapper.toDto(any(Passenger.class))).thenReturn(new PassengerDto());

        List<PassengerDto> result = passengerService.getAllPassengers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(passengerRepository, times(1)).findAll();
        verify(entityMapper, times(2)).toDto(any(Passenger.class));
    }

    @Test
    void createPassenger_ShouldAddPassenger() {
        CreatePassengerCommand createCommand = new CreatePassengerCommand("John", "Doe", "john@example.com", "123456789", "1990-01-01");

        passengerService.createPassenger(createCommand);

        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    void updatePassenger_WhenPassengerExists_ShouldUpdatePassenger() {
        long passengerId = 1L;
        UpdateEmailCommand updateCommand = new UpdateEmailCommand("new.email@example.com");
        Passenger existingPassenger = new Passenger(
                new FirstName("John"),
                new LastName("Doe"),
                LocalDate.parse("1990-01-01"),
                new PhoneNumber("123456789"),
                new Email("john@example.com")
        );

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(existingPassenger));

        passengerService.updatePassenger(passengerId, updateCommand);

        verify(passengerRepository, times(1)).findById(passengerId);
        verify(passengerRepository, times(1)).save(existingPassenger);
    }

    @Test
    void deletePassenger_WhenPassengerExists_ShouldDeletePassenger() {
        long passengerId = 1L;
        Passenger existingPassenger = new Passenger(
                new FirstName("John"),
                new LastName("Doe"),
                LocalDate.parse("1990-01-01"),
                new PhoneNumber("123456789"),
                new Email("john@example.com")
        );

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(existingPassenger));

        passengerService.deletePassenger(passengerId);

        verify(passengerRepository, times(1)).findById(passengerId);
        verify(passengerRepository, times(1)).delete(existingPassenger);
    }

    @Test
    void deletePassenger_WhenPassengerNotExists_ShouldThrowException() {
        long nonExistingPassengerId = 999L;
        when(passengerRepository.findById(nonExistingPassengerId)).thenReturn(Optional.empty());

        assertThrows(PassengerNotExistsException.class, () -> passengerService.deletePassenger(nonExistingPassengerId));

        verify(passengerRepository, times(1)).findById(nonExistingPassengerId);
        verify(passengerRepository, never()).delete(any(Passenger.class));
    }
}
