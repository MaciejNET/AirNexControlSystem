package tu.kielce.airnexcontrolsystem.commands;

import java.time.LocalDateTime;

/**
 * @author Maciej Deroń
 */
public record ChangeDepartureTimeCommand(LocalDateTime departureTime) {
}
