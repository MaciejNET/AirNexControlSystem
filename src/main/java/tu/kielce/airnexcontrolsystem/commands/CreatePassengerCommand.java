package tu.kielce.airnexcontrolsystem.commands;

import java.util.Date;

/**
 * @author Julia Dziekańska
 */
public record CreatePassengerCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String dateOfBirth) {
}
