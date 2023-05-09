package gpo.TestingSystem.Integrals.Indefinite;

import org.springframework.stereotype.Service;

@Service
public class RandomNumber {

    private static String integral;
    private static String variant1;
    private static String variant2;
    private static String variant3;
    private static String variant4;

    public RandomNumber(String integral) {
        this.integral=integral;

    }


    public RandomNumber() {
    }

    public String getIntegral() {
        return integral;
    }


    public static RandomNumber number() {

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


        integral= "\"" + "\\\\int_{}\\\\frac{"+numerator+"sin(x)^("+numerator_degree+")}{"+ denominator +"cos(x)^("+denominator_degree+")}*dx"+"\""; ;

        System.out.println(integral);


        return new RandomNumber(integral);


        //рандом  степень числитель
    }

    //рандом  степень числитель
    private static int numeratorDegree(int min, int max) {//рандо
        max -= min;

        return (int) (Math.random() * ++max) + min;


    }

    //рандом числитель и знаменатель
    private static int number(int min, int max) {
        max -= min;

        return (int) (Math.random() * ++max) + min;
    }

}
