package tu.kielce.airnexcontrolsystem.services;
import tu.kielce.airnexcontrolsystem.commands.CreateAirlineCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirlineNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;

import java.util.List;

/**
 * @author Mariusz Ignaciuk
 */
public interface AirlineService {
    List<AirlineDto> getAll();
    AirlineDto getById(Long id);
    void add(CreateAirlineCommand command);
    void delete(Long id);
    void updateName(Long id, UpdateAirlineNameCommand command);
}
