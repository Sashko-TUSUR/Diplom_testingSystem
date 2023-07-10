package gpo.TestingSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "test_id")
    private Test test ;

    private Long dataOfCompletion;
    private Integer switchedToTab;
    private Integer removedFocus;
    private Long spendTime;
    private String condition;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Long getDataOfCompletion() {
        return dataOfCompletion;
    }

    public void setDataOfCompletion(Long dataOfCompletion) {
        this.dataOfCompletion = dataOfCompletion;
    }

    public Integer getSwitchedToTab() {
        return switchedToTab;
    }

    public void setSwitchedToTab(Integer switchedToTab) {
        this.switchedToTab = switchedToTab;
    }

    public Integer getRemovedFocus() {
        return removedFocus;
    }

    public void setRemovedFocus(Integer removedFocus) {
        this.removedFocus = removedFocus;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
