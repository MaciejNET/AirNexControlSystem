package tu.kielce.airnexcontrolsystem.commends;

import java.time.LocalDateTime;

/**
 * @author Maciej Deroń
 */
public record ChangeDepartureTimeCommand(LocalDateTime departureTime) {
}
