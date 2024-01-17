package tu.kielce.airnexcontrolsystem.commends;

import java.util.Date;

/**
 * @author Julia Dziekańska
 */
public record CreatePassengerCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Date dateOfBirth) {
}
