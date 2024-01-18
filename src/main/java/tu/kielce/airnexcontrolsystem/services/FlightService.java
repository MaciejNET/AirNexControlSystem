package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commands.ChangeDepartureTimeCommand;
import tu.kielce.airnexcontrolsystem.commands.ChangePriceCommand;
import tu.kielce.airnexcontrolsystem.commands.CreateFlightCommand;
import tu.kielce.airnexcontrolsystem.dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Maciej Deroń
 */
public interface FlightService {
    FlightDto getById(Long id);
    List<FlightDto> findFlights(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime);
    void createFlight(CreateFlightCommand command);
    void updateDepartureTime(Long id, ChangeDepartureTimeCommand command);
    void updatePrice(Long id, ChangePriceCommand command);
    void deleteFlight(Long id);
}
