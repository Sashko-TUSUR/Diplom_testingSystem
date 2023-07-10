package gpo.TestingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "didacticUnit")
public class DidacticUnit {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long didacticUnitId;

    @JsonProperty("label")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "subject_id", referencedColumnName = "subjectId")
    private Subject subject;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Topic> getTopic() {
        return topic;
    }

    public void setTopic(Set<Topic> topic) {
        this.topic = topic;
    }
}
