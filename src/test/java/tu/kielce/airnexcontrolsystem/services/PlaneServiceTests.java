package tu.kielce.airnexcontrolsystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tu.kielce.airnexcontrolsystem.commands.CreatePlaneCommand;
import tu.kielce.airnexcontrolsystem.dto.PlaneDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Plane;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.PlaneNotExistsException;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.AirlineRepository;
import tu.kielce.airnexcontrolsystem.repositories.PlaneRepository;
import tu.kielce.airnexcontrolsystem.services.implementations.PlaneServiceImpl;
import tu.kielce.airnexcontrolsystem.value_objects.Model;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PlaneServiceTests {

    @Mock
    private PlaneRepository planeRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private PlaneServiceImpl planeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsEmptyList_WhenNoPlanes() {
        when(planeRepository.findAll()).thenReturn(new ArrayList<>());

        List<PlaneDto> result = planeService.getAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_ReturnsListOfPlaneDtos_WhenPlanesExist() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<Plane> constructor = Plane.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        List<Plane> planes = List.of(constructor.newInstance(), constructor.newInstance());
        when(planeRepository.findAll()).thenReturn(planes);
        when(entityMapper.toDto(constructor.newInstance())).thenReturn(new PlaneDto());

        List<PlaneDto> result = planeService.getAll();

        assertEquals(planes.size(), result.size());
    }

    @Test
    void getById_ReturnsNull_WhenPlaneNotExists() {
        when(planeRepository.findById(any())).thenReturn(Optional.empty());

        PlaneDto result = planeService.getById(1L);

        assertNull(result);
    }


    @Test
    void add_ThrowsAirlineNotExistsException_WhenAirlineNotFound() {
        CreatePlaneCommand command = new CreatePlaneCommand("model", "airline", 100);
        when(airlineRepository.findByName(any())).thenReturn(Optional.empty());

        assertThrows(AirlineNotExistsException.class, () -> planeService.add(command));
    }

    @Test
    void add_SavesPlane_WhenAirlineExists() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<Plane> constructorPlane = Plane.class.getDeclaredConstructor();
        constructorPlane.setAccessible(true);
        Constructor<Airline> constructorAirline = Airline.class.getDeclaredConstructor();
        constructorAirline.setAccessible(true);
        CreatePlaneCommand command = new CreatePlaneCommand("model", "airline", 100);
        Airline airline = constructorAirline.newInstance();
        when(airlineRepository.findByName(any())).thenReturn(Optional.of(airline));
        when(planeRepository.save(any())).thenReturn(constructorPlane.newInstance());

        assertDoesNotThrow(() -> planeService.add(command));
    }

    @Test
    void delete_ThrowsPlaneNotExistsException_WhenPlaneNotFound() {
        when(planeRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PlaneNotExistsException.class, () -> planeService.delete(1L));
    }

    @Test
    void delete_DeletesPlane_WhenPlaneExists() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<Plane> constructor = Plane.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Plane plane = constructor.newInstance();
        when(planeRepository.findById(any())).thenReturn(Optional.of(plane));

        assertDoesNotThrow(() -> planeService.delete(1L));
        verify(planeRepository, times(1)).delete(plane);
    }
}
