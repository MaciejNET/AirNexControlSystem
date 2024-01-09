package tu.kielce.airnexcontrolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Paweł Dostal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto implements Serializable {
    private Long id;
    private String name;
    private String city;
    private String country;

}
