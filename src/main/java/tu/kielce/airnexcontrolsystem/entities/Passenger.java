package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import tu.kielce.airnexcontrolsystem.converters.*;
import tu.kielce.airnexcontrolsystem.value_objects.*;

import java.util.Date;
import java.util.List;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "passengers")
@Getter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Convert(converter = FirstNameConverter.class)
    private FirstName firstName;

    @Column(name = "last_name")
    @Convert(converter = LastNameConverter.class)
    private LastName lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phoneNumber;

    @Column(name = "email", unique = true)
    @Convert(converter = EmailConverter.class)
    private Email email;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    protected Passenger() {
    }

    public Passenger(final FirstName firstName, final LastName lastName, final Date dateOfBirth, final PhoneNumber phoneNumber, final Email email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void changePhoneNumber(final PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changeEmail(final Email email) {
        this.email = email;
    }

}
