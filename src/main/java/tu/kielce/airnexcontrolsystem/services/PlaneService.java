package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commands.CreatePlaneCommand;
import tu.kielce.airnexcontrolsystem.dto.PlaneDto;

import java.util.List;

/**
 * @author Maciej Deroń
 */
public interface PlaneService {
    List<PlaneDto> getAll();
    PlaneDto getById(Long id);
    void add(CreatePlaneCommand command);
    void delete(Long id);
}
