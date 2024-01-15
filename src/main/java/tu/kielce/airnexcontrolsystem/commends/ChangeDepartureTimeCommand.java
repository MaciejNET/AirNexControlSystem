package tu.kielce.airnexcontrolsystem.commends;

import java.time.LocalDateTime;

public record ChangeDepartureTimeCommand(
        LocalDateTime departureTime) {
}
