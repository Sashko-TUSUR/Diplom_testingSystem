package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;

    private String name;


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name="subject_teacher",joinColumns = @JoinColumn(name = "subject_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<Teacher> teachers = new HashSet<>();

   @ManyToMany(mappedBy = "subjects",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
}
