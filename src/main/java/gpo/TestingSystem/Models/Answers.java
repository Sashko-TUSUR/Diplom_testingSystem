package gpo.TestingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answerId;

   private String Title;

   private Boolean trueAnswer;

   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JoinColumn(name = "questionId")
   private Question question;


    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Boolean getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(Boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
