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
@Table(name = "teacher")
@Entity
public class Teacher {

    @Id
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="teacher_groups",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "numGroup"))
    private Set<Groups> groups = new HashSet<>();

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects = new ArrayList<>();

}
