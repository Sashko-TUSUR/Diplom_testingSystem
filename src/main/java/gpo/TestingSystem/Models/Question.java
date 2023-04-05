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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Topic topic ;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Complexity complexities ;


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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Complexity getComplexities() {
        return complexities;
    }

    public void setComplexities(Complexity complexities) {
        this.complexities = complexities;
    }
}
