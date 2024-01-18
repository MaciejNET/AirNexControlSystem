import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tu.kielce.airnexcontrolsystem.commends.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commends.UpdateEmailCommand;
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
import tu.kielce.airnexcontrolsystem.services.PassengerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        // Arrange
        long passengerId = 1L;
        Passenger existingPassenger = new Passenger(
                new FirstName("John"),
                new LastName("Doe"),
                new Date(),
                new PhoneNumber("123456789"),
                new Email("john@example.com")
        );

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(existingPassenger));
        when(entityMapper.toDto(existingPassenger)).thenReturn(new PassengerDto());

        // Act
        PassengerDto result = passengerService.getPassenger(passengerId);

        // Assert
        assertNotNull(result);

        // Verify that repository and mapper methods are called
        verify(passengerRepository, times(1)).findById(passengerId);
        verify(entityMapper, times(1)).toDto(existingPassenger);
    }

    @Test
    void getPassenger_WhenPassengerNotExists_ShouldThrowException() {
        // Arrange
        long nonExistingPassengerId = 999L;

        // Act and Assert
        assertThrows(PassengerNotExistsException.class, () -> passengerService.getPassenger(nonExistingPassengerId));

        // Verify that repository method is called
        verify(passengerRepository, times(1)).findById(nonExistingPassengerId);
    }

    @Test
    void getAllPassengers_ShouldReturnListOfPassengerDtos() {
        // Arrange
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(new FirstName("John"), new LastName("Doe"), new Date(), new PhoneNumber("123456789"), new Email("john@example.com")));
        passengers.add(new Passenger(new FirstName("Jane"), new LastName("Doe"), new Date(), new PhoneNumber("987654321"), new Email("jane@example.com")));

        when(passengerRepository.findAll()).thenReturn(passengers);
        when(entityMapper.toDto(any(Passenger.class))).thenReturn(new PassengerDto());

        // Act
        List<PassengerDto> result = passengerService.getAllPassengers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify that repository and mapper methods are called
        verify(passengerRepository, times(1)).findAll();
        verify(entityMapper, times(2)).toDto(any(Passenger.class));
    }

    @Test
    void createPassenger_ShouldAddPassenger() {
        // Arrange
        CreatePassengerCommand createCommand = new CreatePassengerCommand("John", "Doe", "john@example.com", "123456789", new Date());

        // Act
        passengerService.createPassenger(createCommand);

        // Verify that repository method is called
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    void updatePassenger_WhenPassengerExists_ShouldUpdatePassenger() {
        // Arrange
        long passengerId = 1L;
        UpdateEmailCommand updateCommand = new UpdateEmailCommand("new.email@example.com");
        Passenger existingPassenger = new Passenger(
                new FirstName("John"),
                new LastName("Doe"),
                new Date(),
                new PhoneNumber("123456789"),
                new Email("john@example.com")
        );

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(existingPassenger));

        // Act
        passengerService.updatePassenger(passengerId, updateCommand);

        // Verify that repository method is called
        verify(passengerRepository, times(1)).findById(passengerId);
        verify(passengerRepository, times(1)).save(existingPassenger);
    }

    @Test
    void deletePassenger_WhenPassengerExists_ShouldDeletePassenger() {
        // Arrange
        long passengerId = 1L;
        Passenger existingPassenger = new Passenger(
                new FirstName("John"),
                new LastName("Doe"),
                new Date(),
                new PhoneNumber("123456789"),
                new Email("john@example.com")
        );

        when(passengerRepository.findById(passengerId)).thenReturn(Optional.of(existingPassenger));

        // Act
        passengerService.deletePassenger(passengerId);

        // Verify that repository method is called
        verify(passengerRepository, times(1)).findById(passengerId);
        verify(passengerRepository, times(1)).delete(existingPassenger);
    }

    @Test
    void deletePassenger_WhenPassengerNotExists_ShouldThrowException() {
        // Arrange
        long nonExistingPassengerId = 999L;
        when(passengerRepository.findById(nonExistingPassengerId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(PassengerNotExistsException.class, () -> passengerService.deletePassenger(nonExistingPassengerId));

        // Verify that repository method is called
        verify(passengerRepository, times(1)).findById(nonExistingPassengerId);
        verify(passengerRepository, never()).delete(any(Passenger.class));
    }
}
