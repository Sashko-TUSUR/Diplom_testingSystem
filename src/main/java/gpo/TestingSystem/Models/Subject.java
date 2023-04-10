package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
