package mail.tolstov.one_to_one.v1.model;

import jakarta.persistence.*;

import java.io.Serializable;
//т.к id не число то нужно использовать implements Serializable, в v2 он не нужен
@Entity
@Table(schema = "one_to_one", name = "passport")
public class Passport implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person person;

    @Column(name = "passport_number")
    private int passportNumber;

    public Passport() {
    }

    public Passport(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "person=" + person +
                ", passportNumber=" + passportNumber +
                '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}
