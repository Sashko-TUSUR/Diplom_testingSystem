package gpo.TestingSystem.Integrals.Indefinite;

public class CreateIntegral {


    public static String integral()
    {
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


        integral =  "\\int_{}\\frac{"+numerator+"sin(x)^("+numerator_degree+")}{"+ denominator +"cos(x)^("+denominator_degree+")}*dx";

        return integral;
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
