package gpo.TestingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import gpo.TestingSystem.Payload.Views;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class User {


    @JsonView({Views.Public.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @JsonIgnore
    private String login, password;

    @JsonProperty("name")
    @JsonView({Views.Public.class,Views.Student.class})
    private String nameUser;

    @JsonProperty("middleName")
    @JsonView({Views.Public.class,Views.Student.class})
    private String patronymic;

    @JsonProperty("lastName")
    @JsonView({Views.Public.class,Views.Student.class})
    private String surname;

    @JsonView(Views.SignIn.class)
    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "roleId")
    private Role role ;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Student student;

    @JsonProperty("groups")
    @JsonView(Views.Teacher.class)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_group",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private Set<Groups> teacher_group = new HashSet<>();

    @JsonProperty("disciplines")
    @JsonView(Views.Teacher.class)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="teacher_subject",joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();


    public User(Long userId, String login, String password, String nameUser, String patronymic, String surname) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.nameUser = nameUser;
        this.patronymic = patronymic;
        this.surname = surname;

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
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Groups> getTeacher_group() {
        return teacher_group;
    }

    public void setTeacher_group(Set<Groups> teacher_group) {
        this.teacher_group = teacher_group;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
