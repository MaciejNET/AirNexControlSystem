package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commands.CreateAirportCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirportNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;

import java.util.List;

/**
 * @author Paweł Dostal
 */
public interface AirportService {

    List<AirportDto> getAll();
    AirportDto getById(Long id);
    void add(CreateAirportCommand command);
    void delete(Long id);
    void updateName(Long id, UpdateAirportNameCommand command);

}
