package tu.kielce.airnexcontrolsystem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tu.kielce.airnexcontrolsystem.commands.CreateAirlineCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirlineNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirportAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.services.implementations.AirlineServiceImpl;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Mariusz Ignaciuk
 */
@ExtendWith(MockitoExtension.class)
class AirlineServiceTests {

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Test
    void getAll_ShouldReturnListOfAirlineDtos() {
        // Arrange
        List<Airline> airlines = new ArrayList<>();
        airlines.add(new Airline(new Name("Airline 1")));
        airlines.add(new Airline(new Name("Airline 2")));

        when(airlineRepository.findAll()).thenReturn(airlines);
        when(entityMapper.toDto(any(Airline.class))).thenReturn(new AirlineDto());

        // Act
        List<AirlineDto> result = airlineService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify that repository and mapper methods are called
        verify(airlineRepository, times(1)).findAll();
        verify(entityMapper, times(2)).toDto(any(Airline.class));
    }

    @Test
    void getById_WhenAirlineExists_ShouldReturnAirlineDto() {
        // Arrange
        long airlineId = 1L;
        Airline existingAirline = new Airline(new Name("Existing Airline"));

        when(airlineRepository.findById(airlineId)).thenReturn(Optional.of(existingAirline));
        when(entityMapper.toDto(existingAirline)).thenReturn(new AirlineDto());

        // Act
        AirlineDto result = airlineService.getById(airlineId);

        // Assert
        assertNotNull(result);

        verify(airlineRepository, times(1)).findById(airlineId);
        verify(entityMapper, times(1)).toDto(existingAirline);
    }

    @Test
    void getById_WhenAirlineNotExists_ShouldReturnNull() {
        // Arrange
        long nonExistingAirlineId = 999L;
        when(airlineRepository.findById(nonExistingAirlineId)).thenReturn(Optional.empty());

        // Act
        AirlineDto result = airlineService.getById(nonExistingAirlineId);

        // Assert
        assertNull(result);

        verify(airlineRepository, times(1)).findById(nonExistingAirlineId);
    }

    @Test
    void add_WhenAirlineDoesNotExist_ShouldAddAirline() {
        // Arrange
        CreateAirlineCommand createCommand = new CreateAirlineCommand("New Airline");
        when(airlineRepository.findByName(any(Name.class))).thenReturn(Optional.empty());

        // Act
        airlineService.add(createCommand);

        verify(airlineRepository, times(1)).findByName(any(Name.class));
        verify(airlineRepository, times(1)).save(any(Airline.class));
    }

    @Test
    void add_WhenAirlineAlreadyExists_ShouldThrowException() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        CreateAirlineCommand createCommand = new CreateAirlineCommand("Existing Airline");
        Constructor<Airline> constructor = Airline.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        when(airlineRepository.findByName(any(Name.class))).thenReturn(Optional.of(constructor.newInstance()));

        // Act and Assert
        assertThrows(AirportAlreadyExistsException.class, () -> airlineService.add(createCommand));

        verify(airlineRepository, times(1)).findByName(any(Name.class));
        verify(airlineRepository, never()).save(any(Airline.class));
    }

    @Test
    void delete_WhenAirlineExists_ShouldDeleteAirline() {
        // Arrange
        long airlineId = 1L;
        Airline existingAirline = new Airline(new Name("Existing Airline"));
        when(airlineRepository.findById(airlineId)).thenReturn(Optional.of(existingAirline));

        // Act
        airlineService.delete(airlineId);

        verify(airlineRepository, times(1)).findById(airlineId);
        verify(airlineRepository, times(1)).delete(existingAirline);
    }

    @Test
    void delete_WhenAirlineNotExists_ShouldThrowException() {
        // Arrange
        long nonExistingAirlineId = 999L;
        when(airlineRepository.findById(nonExistingAirlineId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AirlineNotExistsException.class, () -> airlineService.delete(nonExistingAirlineId));

        verify(airlineRepository, times(1)).findById(nonExistingAirlineId);
        verify(airlineRepository, never()).delete(any(Airline.class));
    }

    @Test
    void updateName_WhenAirlineNotExists_ShouldThrowException() {
        // Arrange
        long nonExistingAirlineId = 999L;
        UpdateAirlineNameCommand updateCommand = new UpdateAirlineNameCommand("New Airline Name");

        when(airlineRepository.findById(nonExistingAirlineId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AirlineNotExistsException.class,
                () -> airlineService.updateName(nonExistingAirlineId, updateCommand));

        verify(airlineRepository, never()).findByName(any(Name.class));
        verify(airlineRepository, never()).save(any(Airline.class));
    }

    @Test
    void updateName_WhenNewNameAlreadyExists_ShouldThrowException() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        long airlineId = 1L;
        String newName = "New Airline Name";
        UpdateAirlineNameCommand updateCommand = new UpdateAirlineNameCommand(newName);

        Airline existingAirline = new Airline(new Name("Old Airline Name"));
        when(airlineRepository.findById(airlineId)).thenReturn(Optional.of(existingAirline));
        Constructor<Airline> constructor = Airline.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        when(airlineRepository.findByName(any(Name.class))).thenReturn(Optional.of(constructor.newInstance()));

        // Act and Assert
        assertThrows(AirlineAlreadyExistsException.class,
                () -> airlineService.updateName(airlineId, updateCommand));

        verify(airlineRepository, times(1)).findById(airlineId);
        verify(airlineRepository, times(1)).findByName(any(Name.class));
        verify(airlineRepository, never()).save(any(Airline.class));
    }
}

