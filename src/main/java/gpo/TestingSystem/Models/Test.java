package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "test")
public class Test {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long StartTest;
    private Long EndTest;
    private Long Duration;
    private Integer CountTry;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "typeTest_id")
    private TypeTest typeTest ;


    @ManyToMany(mappedBy = "tests", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY )
    private Set<Question> questions = new HashSet<>();



    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTest() {
        return StartTest;
    }

    public void setStartTest(Long startTest) {
        StartTest = startTest;
    }

    public Long getEndTest() {
        return EndTest;
    }

    public void setEndTest(Long endTest) {
        EndTest = endTest;
    }

    public Long getDuration() {
        return Duration;
    }

    public void setDuration(Long duration) {
        Duration = duration;
    }

    public Integer getCountTry() {
        return CountTry;
    }

    public void setCountTry(Integer countTry) {
        CountTry = countTry;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public TypeTest getTypeTest() {
        return typeTest;
    }

    public void setTypeTest(TypeTest typeTest) {
        this.typeTest = typeTest;
    }

}
