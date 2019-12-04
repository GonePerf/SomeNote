package SQL;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Column(name = "user_id")
    @GeneratedValue
    @Id
    private long id;
    @Column(name = "user_email", unique = true)
    private String email;
    @Column(name = "user_password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "pin")
    private String pin;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Notebook> notebooks;

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", pin='" + pin + '\'' +
                ", notebooks=" + notebooks +
                '}';
    }

    public List<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }
    public void addNotebook(Notebook notebook){
        notebooks.add(notebook);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
