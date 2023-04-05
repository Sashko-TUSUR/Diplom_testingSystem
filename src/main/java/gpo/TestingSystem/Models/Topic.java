package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DidacticUnit didacticUnit ;


    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DidacticUnit getDidacticUnit() {
        return didacticUnit;
    }

    public void setDidacticUnit(DidacticUnit didacticUnit) {
        this.didacticUnit = didacticUnit;
    }
}
