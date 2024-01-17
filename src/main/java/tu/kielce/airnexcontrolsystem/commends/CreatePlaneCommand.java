package tu.kielce.airnexcontrolsystem.commends;

/**
 * @author Maciej Deroń
 */
public record CreatePlaneCommand(String model, String airline, int numberOfSeats) {
}
