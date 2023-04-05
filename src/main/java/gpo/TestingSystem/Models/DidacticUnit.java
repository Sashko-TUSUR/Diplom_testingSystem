package gpo.TestingSystem.Models;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;



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


    public Long getDidacticUnitId() {
        return didacticUnitId;
    }

    public void setDidacticUnitId(Long didacticUnitId) {
        this.didacticUnitId = didacticUnitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
