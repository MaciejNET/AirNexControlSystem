package tu.kielce.airnexcontrolsystem.mappers;

import org.springframework.stereotype.Component;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.entities.Plain;

@Component
public class EntityMapperImpl implements EntityMapper{
    /**
     * @author Maciej Deroń
     */
    @Override
    public PlainDto toDto(final Plain plain) {
        return new PlainDto(plain.getId(),
                plain.getModel().value(),
                plain.getAirline().getName().value(),
                plain.getSeats().size());
    }
}
