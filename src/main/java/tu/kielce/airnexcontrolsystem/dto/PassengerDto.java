package tu.kielce.airnexcontrolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Julia Dziekańska
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PassengerDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String date_of_birth;
    private String phoneNumber;
    private String email;
}
