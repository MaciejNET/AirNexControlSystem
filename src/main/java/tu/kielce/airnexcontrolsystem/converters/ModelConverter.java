package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.Model;

/**
 * @author Maciej Deroń
 */
@Converter
public class ModelConverter implements AttributeConverter<Model, String> {
    @Override
    public String convertToDatabaseColumn(final Model model) {
        return model.value();
    }

    @Override
    public Model convertToEntityAttribute(final String s) {
        return new Model(s);
    }
}
