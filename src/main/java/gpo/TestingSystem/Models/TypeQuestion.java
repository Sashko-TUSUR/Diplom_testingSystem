package gpo.TestingSystem.Models;



import gpo.TestingSystem.Enumeration.EnumTypeQuestion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TypeQuestion")
public class TypeQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private EnumTypeQuestion name;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public EnumTypeQuestion getName() {
        return name;
    }

    public void setName(EnumTypeQuestion name) {
        this.name = name;
    }
}
