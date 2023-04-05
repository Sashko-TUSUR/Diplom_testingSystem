package gpo.TestingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



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


    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="role_user",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true,fetch = FetchType.LAZY)
    private Teacher teacher;


    public User(Long userId, String login, String password, String nameUser, String patronymic, String surname) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.nameUser = nameUser;
        this.patronymic = patronymic;
        this.Surname = surname;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
