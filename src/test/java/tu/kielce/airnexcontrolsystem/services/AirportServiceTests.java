package tu.kielce.airnexcontrolsystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tu.kielce.airnexcontrolsystem.commands.CreateAirportCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirportNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.exceptions.AirportAlreadyExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.AirportNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirportRepository;
import tu.kielce.airnexcontrolsystem.services.implementations.AirportServiceImpl;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.Country;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirportServiceTests {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private AirportServiceImpl airportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Airport> constructor = Airport.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        when(airportRepository.findAll()).thenReturn(Arrays.asList(constructor.newInstance(),constructor.newInstance()));
        when(entityMapper.toDto(any(Airport.class))).thenReturn(new AirportDto());

        // Act
        List<AirportDto> result = airportService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(airportRepository, times(1)).findAll();
        verify(entityMapper, times(2)).toDto(any(Airport.class));
    }

    @Test
    void testGetById() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Airport> constructor = Airport.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Long airportId = 1L;
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(constructor.newInstance()));
        when(entityMapper.toDto(any(Airport.class))).thenReturn(new AirportDto());

        // Act
        AirportDto result = airportService.getById(airportId);

        // Assert
        assertNotNull(result);
        verify(airportRepository, times(1)).findById(airportId);
        verify(entityMapper, times(1)).toDto(any(Airport.class));
    }


    @Test
    void testAdd() {
        // Arrange
        CreateAirportCommand createAirportCommand = new CreateAirportCommand("Airport1", "City1", "Country1");
        when(airportRepository.findByName(any(Name.class))).thenReturn(Optional.empty());

        // Act
        airportService.add(createAirportCommand);

        // Assert
        verify(airportRepository, times(1)).findByName(any(Name.class));
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void testAddAirportAlreadyExists() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Airport> constructor = Airport.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CreateAirportCommand createAirportCommand = new CreateAirportCommand("Airport1", "City1", "Country1");
        when(airportRepository.findByName(any(Name.class))).thenReturn(Optional.of(constructor.newInstance()));

        // Act and Assert
        assertThrows(AirportAlreadyExistsException.class, () -> airportService.add(createAirportCommand));
        verify(airportRepository, times(1)).findByName(any(Name.class));
        verify(airportRepository, never()).save(any(Airport.class));
    }

    @Test
    void testDelete() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Airport> constructor = Airport.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Long airportId = 1L;
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(constructor.newInstance()));

        // Act
        airportService.delete(airportId);

        // Assert
        verify(airportRepository, times(1)).findById(airportId);
        verify(airportRepository, times(1)).delete(any(Airport.class));
    }

    @Test
    void testDeleteAirportNotFound() {
        // Arrange
        Long airportId = 1L;
        when(airportRepository.findById(airportId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AirportNotExistsException.class, () -> airportService.delete(airportId));
        verify(airportRepository, times(1)).findById(airportId);
        verify(airportRepository, never()).delete(any(Airport.class));
    }

    @Test
    void testUpdateName() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Airport> constructor = Airport.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Long airportId = 1L;
        UpdateAirportNameCommand updateAirportNameCommand = new UpdateAirportNameCommand("NewAirportName");
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(constructor.newInstance()));
        when(airportRepository.findByName(any(Name.class))).thenReturn(Optional.empty());

        // Act
        airportService.updateName(airportId, updateAirportNameCommand);

        // Assert
        verify(airportRepository, times(1)).findById(airportId);
        verify(airportRepository, times(1)).findByName(any(Name.class));
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void testUpdateNameAirportNotFound() {
        // Arrange
        Long airportId = 1L;
        UpdateAirportNameCommand updateAirportNameCommand = new UpdateAirportNameCommand("NewAirportName");
        when(airportRepository.findById(airportId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AirportNotExistsException.class, () -> airportService.updateName(airportId, updateAirportNameCommand));
        verify(airportRepository, times(1)).findById(airportId);
        verify(airportRepository, never()).findByName(any(Name.class));
        verify(airportRepository, never()).save(any(Airport.class));
    }

    @Test
    void testUpdateNameAirportAlreadyExists() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Airport> constructor = Airport.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Long airportId = 1L;
        UpdateAirportNameCommand updateAirportNameCommand = new UpdateAirportNameCommand("NewAirportName");
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(constructor.newInstance()));
        when(airportRepository.findByName(any(Name.class))).thenReturn(Optional.of(constructor.newInstance()));

        // Act and Assert
        assertThrows(AirportAlreadyExistsException.class, () -> airportService.updateName(airportId, updateAirportNameCommand));
        verify(airportRepository, times(1)).findById(airportId);
        verify(airportRepository, times(1)).findByName(any(Name.class));
        verify(airportRepository, never()).save(any(Airport.class));
    }
}
