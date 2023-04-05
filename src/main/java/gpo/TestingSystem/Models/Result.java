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

    @ManyToOne
    private Student student;

    private String topic;

    private String result;

    @ManyToOne
    private TemplateTest templateTests;

    private Long dataEnd;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public TemplateTest getTemplateTests() {
        return templateTests;
    }

    public void setTemplateTests(TemplateTest templateTests) {
        this.templateTests = templateTests;
    }

    public Long getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(Long dataEnd) {
        this.dataEnd = dataEnd;
    }
}
