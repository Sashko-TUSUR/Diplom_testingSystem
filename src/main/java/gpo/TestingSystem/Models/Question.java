package gpo.TestingSystem.Models;


import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    private String content;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idDidactic")
    private DidacticUnit didacticUnit ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "complexityId")
    private Complexity complexities ;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "typeQuestionId")
    private TypeQuestion typeQuestion;


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Complexity getComplexities() {
        return complexities;
    }

    public void setComplexities(Complexity complexities) {
        this.complexities = complexities;
    }

    public TypeQuestion getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(TypeQuestion typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DidacticUnit getDidacticUnit() {
        return didacticUnit;
    }

    public void setDidacticUnit(DidacticUnit didacticUnit) {
        this.didacticUnit = didacticUnit;
    }
}
