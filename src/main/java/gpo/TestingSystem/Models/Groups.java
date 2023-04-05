package gpo.TestingSystem.Models;


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
@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGroup;

    private String numGroup;

//    @ManyToMany(mappedBy = "teacher_group")
//    private Set<Teacher> teachers= new HashSet<>();

   @OneToMany(mappedBy = "numGroup", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
   private Set<Student> student = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(name="group_subject",joinColumns = @JoinColumn(name = "group_id"),
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
