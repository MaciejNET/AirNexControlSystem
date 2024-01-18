package tu.kielce.airnexcontrolsystem.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

/**
 * @author Maciej Deroń
 */
public record ChangePriceCommand(@JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT) BigDecimal price) {
}
