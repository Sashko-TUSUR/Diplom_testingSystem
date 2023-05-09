package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.*;
import gpo.TestingSystem.Payload.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Groups {


    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGroup;

    @JsonView({Views.Student.class,Views.Subject.class, Views.Public.class})
    @JsonProperty("label")
    private String numGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "idGroup", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<Student> student = new HashSet<>();

    @JsonUnwrapped
    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "group_subject", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getNumGroup() {
        return numGroup;
    }

    public void setNumGroup(String numGroup) {
        this.numGroup = numGroup;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
