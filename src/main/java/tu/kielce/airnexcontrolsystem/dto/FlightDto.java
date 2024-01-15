package tu.kielce.airnexcontrolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Maciej Deroń
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long id;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String plane;
    private String airline;
    private int availableSeats;
    private LocalTime duration;
    private BigDecimal price;
}
