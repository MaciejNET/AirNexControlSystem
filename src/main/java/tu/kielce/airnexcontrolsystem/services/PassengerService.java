package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commends.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commends.CreatePlainCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;

import java.util.List;

/**
 * @author Julia Dziekańska
 */

public interface PassengerService {
    PassengerDto getPassengerById(Long id);
    List<PassengerDto> getAllPassengers();
    PassengerDto createPassenger(CreatePassengerCommand command);
    PassengerDto updatePassenger(Long id, CreatePassengerCommand command);
    void deletePassenger(Long id);
}
