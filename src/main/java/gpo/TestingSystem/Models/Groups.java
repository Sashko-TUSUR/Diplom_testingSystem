package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
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

    @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(name="group_subject",joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();




}
