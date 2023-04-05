package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "template")
public class TemplateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long templateId;

    private String typeTest;
    private Long startTest;
    private Long endTest;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Complexity complexities ;


    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTypeTest() {
        return typeTest;
    }

    public void setTypeTest(String typeTest) {
        this.typeTest = typeTest;
    }

    public Long getStartTest() {
        return startTest;
    }

    public void setStartTest(Long startTest) {
        this.startTest = startTest;
    }

    public Long getEndTest() {
        return endTest;
    }

    public void setEndTest(Long endTest) {
        this.endTest = endTest;
    }

    public Complexity getComplexities() {
        return complexities;
    }

    public void setComplexities(Complexity complexities) {
        this.complexities = complexities;
    }
}
