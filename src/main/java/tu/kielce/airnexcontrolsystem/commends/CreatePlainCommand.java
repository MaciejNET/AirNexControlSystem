package tu.kielce.airnexcontrolsystem.commends;

/**
 * @author Maciej Deroń
 */
public record CreatePlainCommand(String model, String airline, int numberOfSeats) {
}
