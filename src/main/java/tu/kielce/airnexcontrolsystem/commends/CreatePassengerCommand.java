package tu.kielce.airnexcontrolsystem.commends;

/**
 * @author Julia Dziekańska
 */
public record CreatePassengerCommand(Long id, String firstName, String lastName, String phoneNumber, String email) {
}
