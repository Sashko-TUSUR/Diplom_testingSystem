package gpo.TestingSystem.Service;


import gpo.TestingSystem.Integrals.Indefinite.indefiniteIntegral;
import gpo.TestingSystem.Models.Integral;
import gpo.TestingSystem.Models.Question;
import gpo.TestingSystem.Models.Test;
import gpo.TestingSystem.Repositories.IntegralRepository;
import gpo.TestingSystem.Repositories.QuestionRepository;
import gpo.TestingSystem.Repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceStudent {

    @Autowired
    TestRepository testRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    IntegralRepository integralRepository;
    @Autowired
    indefiniteIntegral indefiniteIntegral;


    //получение теста с вопросами студенту

    public void getTestForStudent(Long idTest) {

        Test test = testRepository.findById(idTest).orElse(null);

        List<Long> idQuestion = questionRepository.testsQuestion(idTest);


        for (Long questionId : idQuestion) {

            Question question = questionRepository.findById(questionId).orElse(null);

            if(question!=null) {



                if (question.getTypeQuestion().getId() == 3) {

                    Question newQuestion = new Question();

                    if(question.getTypeQuestion()!=null)
                    newQuestion.setTypeQuestion(question.getTypeQuestion());

                    if(question.getComplexities()!=null)
                    newQuestion.setComplexities(question.getComplexities());

                    if(question.getContent()!=null)
                    newQuestion.setContent(question.getContent());

                    if(question.getTitle()!=null)
                    newQuestion.setTitle(question.getTitle());

                    if(question.getTopic()!=null)
                    newQuestion.setTopic(question.getTopic());

                    newQuestion.setTests(question.getTests());


                    Integral oldIntegral = integralRepository.findById(questionId).orElse(null);

                    Integral integral = new Integral();

                    if(oldIntegral!=null) {

                        integral.setQuestion(newQuestion);
                        integral.setTypeIntegral(oldIntegral.getTypeIntegral());

                        gpo.TestingSystem.Integrals.Indefinite.indefiniteIntegral.iTask();


                        integral.setIntegral(indefiniteIntegral.getIntegral());
                        integral.setVariant1(indefiniteIntegral.getVariant2());
                        integral.setVariant2(indefiniteIntegral.getVariant2());
                        integral.setVariant3(indefiniteIntegral.getVariant3());
                        integral.setVariant4(indefiniteIntegral.getVariant4());

                        System.out.println(indefiniteIntegral.getIntegral());
                        System.out.println(indefiniteIntegral.getVariant1());
                        System.out.println(indefiniteIntegral.getVariant2());
                        System.out.println(indefiniteIntegral.getVariant3());
                        System.out.println(indefiniteIntegral.getVariant4());

                       // integral.setTruAnswer();

                        integralRepository.save(integral);
                        questionRepository.save(newQuestion);




                    }




                }


             }




            }
        }

    }
