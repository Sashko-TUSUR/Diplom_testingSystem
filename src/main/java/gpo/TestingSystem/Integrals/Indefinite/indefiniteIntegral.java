package gpo.TestingSystem.Integrals.Indefinite;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class indefiniteIntegral {

    private static String integral;
    private static String variant1;
    private static String variant2;
    private static String variant3;
    private static String variant4;
    private static String truAnswer;

    public indefiniteIntegral(String integral,String variant1,String variant2,String variant3,String variant4,String truAnswer)
    {
        indefiniteIntegral.integral = integral;
        indefiniteIntegral.variant1 = variant1;
        indefiniteIntegral.variant2 = variant2;
        indefiniteIntegral.variant3 = variant3;
        indefiniteIntegral.variant4 = variant4;
        indefiniteIntegral.truAnswer = truAnswer;
    }


    public static indefiniteIntegral iTask()
    {
        CreateIntegral createIntegral =CreateIntegral.integral();

        integral = createIntegral.getIntegral();
        truAnswer = createIntegral.getTruAnswer();


        variant1 = CreateVariants.variant1();
        variant2 = CreateVariants.generateFormula(Math.random() < 0.5);
        variant3 = CreateVariants.generateFormula(Math.random() < 0.5);
        variant4 = CreateVariants.generateFormula(Math.random() < 0.5);

        ArrayList<String> variants = new ArrayList<>();

        variants.add(variant1);
        variants.add(variant2);
        variants.add(variant3);
        variants.add(variant4);

        Collections.shuffle(variants);

        variant1 = variants.get(0);
        variant2 = variants.get(1);
        variant3 = variants.get(2);
        variant4 = variants.get(3);


        return new indefiniteIntegral(integral,variant1,variant2,variant3,variant4,truAnswer);
    }

    public indefiniteIntegral()
    {}

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        indefiniteIntegral.integral = integral;
    }

    public String getVariant1() {
        return variant1;
    }

    public void setVariant1(String variant1) {
        indefiniteIntegral.variant1 = variant1;
    }

    public String getVariant2() {
        return variant2;
    }

    public void setVariant2(String variant2) {
        indefiniteIntegral.variant2 = variant2;
    }

    public String getVariant3() {
        return variant3;
    }

    public void setVariant3(String variant3) {
        indefiniteIntegral.variant3 = variant3;
    }

    public String getVariant4() {
        return variant4;
    }

    public void setVariant4(String variant4) {
        indefiniteIntegral.variant4 = variant4;
    }





}
