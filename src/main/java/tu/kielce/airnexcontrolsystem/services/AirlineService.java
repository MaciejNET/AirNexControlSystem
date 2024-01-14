package tu.kielce.airnexcontrolsystem.services;
import tu.kielce.airnexcontrolsystem.commends.CreateAirlineCommand;
import tu.kielce.airnexcontrolsystem.commends.UpdateAirlineNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;

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
