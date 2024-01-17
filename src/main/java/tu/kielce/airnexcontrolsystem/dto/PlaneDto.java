package tu.kielce.airnexcontrolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Maciej Deroń
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaneDto implements Serializable {
    private Long id;
    private String model;
    private String airline;
    private int seatsCount;
}
