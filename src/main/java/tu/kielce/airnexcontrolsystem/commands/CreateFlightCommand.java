package tu.kielce.airnexcontrolsystem.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Maciej Deroń
 */
public record CreateFlightCommand(
        String flightNumber,
        String departureAirport,
        String arrivalAirport,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Long planeId,
        String airline,
        @JsonFormat(shape = JsonFormat.Shape.NUMBER_FLOAT) BigDecimal price) {
}
