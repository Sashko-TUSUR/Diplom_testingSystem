package gpo.TestingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long userId;


    private String login;
    private String password;
    private String nameUser;
    private String patronymic;
    private String Surname;


    @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(name="role_user",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(Long userId, String login, String password, String nameUser, String patronymic, String surname) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.nameUser = nameUser;
        this.patronymic = patronymic;
        this.Surname = surname;

    }

}
