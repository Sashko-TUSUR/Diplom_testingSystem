package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "didacticUnit")
public class DidacticUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long didacticUnitId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

}
