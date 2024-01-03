package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commends.CreatePlainCommand;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;

import java.util.List;

/**
 * @author Maciej Deroń
 */
public interface PlainService {
    List<PlainDto> getAll();
    PlainDto getById(Long id);
    void add(CreatePlainCommand command);
    void delete(Long id);
}
