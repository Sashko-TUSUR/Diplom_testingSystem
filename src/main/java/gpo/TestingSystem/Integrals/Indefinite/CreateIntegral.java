package gpo.TestingSystem.Integrals.Indefinite;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class CreateIntegral {


    private String integral;
    private String truAnswer;

    public CreateIntegral() {
    }

    public CreateIntegral(String integral,String truAnswer)
    {
        this.integral = integral;
        this.truAnswer = truAnswer;
    }


    public static CreateIntegral integral()
    {
        String truAnswer;
        String integral;

        int denominator_degree;
        int numerator_degree;
        int denominator;
        int numerator;

        //для значений
        int max = 30;
        int min = 2;

        //для степеней
        int min2 = 2;
        int max2 = 20;

        numerator = number(min,max);
        denominator = number(min,max);

        numerator_degree = numeratorDegree(min2,max2);
        denominator_degree = numerator_degree + 2;

        BigInteger num = BigInteger.valueOf(numerator);
        System.out.println(num + " числитель");

        int avg = checkNumeratorDegree(numerator_degree,denominator_degree);
        System.out.println(avg+ " среднее");


        int denominator1 = checkKDenominator(denominator,avg );

        System.out.println(denominator1+ " знаменатель");

        BigInteger denom = BigInteger.valueOf(denominator1);

        BigInteger gcd = num.gcd(denom); // Находим наибольший общий делитель

        num = num.divide(gcd);
        denom = denom.divide(gcd);
        truAnswer = num + "/" + denom ;

        integral =  "\\int_{}\\frac{"+numerator+"sin(x)^("+numerator_degree+")}{"+ denominator +"cos(x)^("+denominator_degree+")}*dx";
        return new CreateIntegral(integral,truAnswer);
    }

    //рандом  степень числитель
    private static int numeratorDegree(int min, int max) {
        max -= min;

        return (int) (Math.random() * ++max) + min;

    }

    //рандом числитель и знаменатель
    private static int number(int min, int max) {
        max -= min;

        return (int) (Math.random() * ++max) + min;
    }

    public static int checkNumeratorDegree(int numeratorDegree, int denominatorDegree) {

        return (numeratorDegree+denominatorDegree)/2;
    }

    public static int checkKDenominator(int denominator , int avg) {

        return denominator*avg;

    }


    public String getIntegral() {
        return integral;
    }

    public String getTruAnswer() {
        return truAnswer;
    }
}
