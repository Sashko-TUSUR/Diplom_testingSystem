package gpo.TestingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import gpo.TestingSystem.Payload.Views;
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
@Table(name = "didacticUnit")
public class DidacticUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long didacticUnitId;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "SubjectId")
    private Subject subjectId;

    @JsonIgnore
    @OneToMany(mappedBy = "didacticUnit", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<Topic> topic = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDidacticUnitId() {
        return didacticUnitId;
    }

    public void setDidacticUnitId(Long didacticUnitId) {
        this.didacticUnitId = didacticUnitId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Set<Topic> getTopic() {
        return topic;
    }

    public void setTopic(Set<Topic> topic) {
        this.topic = topic;
    }
}
