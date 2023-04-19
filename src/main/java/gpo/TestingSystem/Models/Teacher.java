//package gpo.TestingSystem.Models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonView;
//import gpo.TestingSystem.Payload.Views;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "teacher")
//@Entity
//public class Teacher {
//
//    @JsonIgnore
//    @Id
//    private Long userId;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    @MapsId
//    private User user;
//
//    @JsonView(Views.Teacher.class)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
//    @JoinTable(name = "teacher_group",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "groups_id"))
//    private Set<Groups> teacher_group = new HashSet<>();
//
//    @JsonView(Views.Teacher.class)
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name="teacher_subject",joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id"))
//    private Set<Subject> subject = new HashSet<>();
//
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Set<Groups> getTeacher_group() {
//        return teacher_group;
//    }
//
//    public void setTeacher_group(Set<Groups> teacher_group) {
//        this.teacher_group = teacher_group;
//    }
//
//    public Set<Subject> getSubject() {
//        return subject;
//    }
//
//    public void setSubject(Set<Subject> subject) {
//        this.subject = subject;
//    }
//}
