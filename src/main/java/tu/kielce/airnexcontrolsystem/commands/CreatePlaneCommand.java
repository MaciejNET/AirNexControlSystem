package tu.kielce.airnexcontrolsystem.commands;

/**
 * @author Maciej Deroń
 */
public record CreatePlaneCommand(String model, String airline, int numberOfSeats) {
}
