package gpo.TestingSystem.Models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@Entity
public class Groups {

    @Id
    private String numGroup;

    @ManyToMany(mappedBy = "groups")
    private List<Teacher> teacherList = new ArrayList<>();

    @ManyToMany(mappedBy = "groups")
    private List<Subject> subjects = new ArrayList<>();

}
