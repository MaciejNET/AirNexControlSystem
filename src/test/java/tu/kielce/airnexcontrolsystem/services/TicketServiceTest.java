package tu.kielce.airnexcontrolsystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.dto.TicketDto;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.entities.Ticket;
import tu.kielce.airnexcontrolsystem.mappers.EntityMapper;
import tu.kielce.airnexcontrolsystem.repositories.FlightRepository;
import tu.kielce.airnexcontrolsystem.repositories.PassengerRepository;
import tu.kielce.airnexcontrolsystem.repositories.TicketRepository;
import tu.kielce.airnexcontrolsystem.services.implementations.TicketServiceImpl;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private EntityMapper entityMapper;
    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<Ticket> constructor = Ticket.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Long airportId = 1L;
        when(ticketRepository.findById(airportId)).thenReturn(Optional.of(constructor.newInstance()));
        when(entityMapper.toDto(any(Airport.class))).thenReturn(new AirportDto());

        TicketDto result = ticketService.getById(airportId);

        assertNotNull(result);
        verify(ticketRepository, times(1)).findById(airportId);
        verify(entityMapper, times(1)).toDto(any(Airport.class));
    }


    @Test
    void testGetAll() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<Ticket> constructor = Ticket.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        when(ticketRepository.findAll()).thenReturn(Arrays.asList(constructor.newInstance(), constructor.newInstance()));
        when(entityMapper.toDto(any(Airport.class))).thenReturn(new AirportDto());

        List<TicketDto> result = ticketService.getAll();

        assertEquals(2, result.size());
        verify(ticketRepository, times(1)).findAll();
        verify(entityMapper, times(2)).toDto(any(Airport.class));
    }

    @Test
    void getUsersTickets() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Constructor<Ticket> constructor = Ticket.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Long userId = 1L;

        Passenger passenger = new Passenger();
        when(passengerRepository.findById(userId)).thenReturn(Optional.of(passenger));

        List<Ticket> userTickets = Arrays.asList(constructor.newInstance(), constructor.newInstance());
        when(ticketRepository.findAllByPassenger(passenger)).thenReturn(userTickets);

        when(entityMapper.toDto(any(Ticket.class))).thenReturn(new TicketDto());

        // Act
        List<TicketDto> result = ticketService.getUsersTickets(userId, true);

        // Assert
        assertEquals(2, result.size());
        verify(passengerRepository, times(1)).findById(userId);
        verify(ticketRepository, times(1)).findAllByPassenger(passenger);
        verify(entityMapper, times(2)).toDto(any(Ticket.class));
    }

}