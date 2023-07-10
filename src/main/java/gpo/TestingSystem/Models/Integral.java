package gpo.TestingSystem.Models;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Integral")
@Entity
public class Integral {


    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    @MapsId
    private Question question;

    private String truAnswer;
    private String integral;
    private String variant1;
    private String variant2;
    private String variant3;
    private String variant4;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "typeIntegral_id")
    private TypeIntegral typeIntegral;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getTruAnswer() {
        return truAnswer;
    }

    public void setTruAnswer(String truAnswer) {
        this.truAnswer = truAnswer;
    }

    public TypeIntegral getTypeIntegral() {
        return typeIntegral;
    }

    public void setTypeIntegral(TypeIntegral typeIntegral) {
        this.typeIntegral = typeIntegral;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getVariant1() {
        return variant1;
    }

    public void setVariant1(String variant1) {
        this.variant1 = variant1;
    }

    public String getVariant2() {
        return variant2;
    }

    public void setVariant2(String variant2) {
        this.variant2 = variant2;
    }

    public String getVariant3() {
        return variant3;
    }

    public void setVariant3(String variant3) {
        this.variant3 = variant3;
    }

    public String getVariant4() {
        return variant4;
    }

    public void setVariant4(String variant4) {
        this.variant4 = variant4;
    }
}
