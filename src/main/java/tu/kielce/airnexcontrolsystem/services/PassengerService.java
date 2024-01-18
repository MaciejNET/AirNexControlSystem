package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commands.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateEmailCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;

import java.util.List;

/**
 * @author Julia Dziekańska
 */
public interface PassengerService {
    PassengerDto getPassenger(Long id);
    List<PassengerDto> getAllPassengers();
    void createPassenger(CreatePassengerCommand command);
    void updatePassenger(Long id, UpdateEmailCommand command);
    void deletePassenger(Long id);
}
