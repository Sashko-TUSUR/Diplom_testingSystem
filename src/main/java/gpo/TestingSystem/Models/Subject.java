package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.*;
import gpo.TestingSystem.Payload.Views;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {


    @JsonView(Views.SubjectGroup.class)
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;


    @JsonView({Views.Teacher.class,Views.SubjectGroup.class})
    @JsonProperty("label")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY )
    private List<Groups> groups = new ArrayList<>();


    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
