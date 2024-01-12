package tu.kielce.airnexcontrolsystem.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * @author Mariusz Ignaciuk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDto implements Serializable {
    private Long id;
    private String name;
}
