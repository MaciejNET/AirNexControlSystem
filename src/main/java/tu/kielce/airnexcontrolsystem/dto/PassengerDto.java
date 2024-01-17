package tu.kielce.airnexcontrolsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Julia Dziekańska
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
}
