package tu.kielce.airnexcontrolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Julia Dziekańska
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private String flightNumber;
    private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmail;
    private int seatNumber;
    private String seatPosition;
}
