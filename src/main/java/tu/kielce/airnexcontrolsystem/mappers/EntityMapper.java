package tu.kielce.airnexcontrolsystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.entities.Plain;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    /**
     * @author Maciej Deroń
     */
    PlainDto toDto(Plain plain);
}
