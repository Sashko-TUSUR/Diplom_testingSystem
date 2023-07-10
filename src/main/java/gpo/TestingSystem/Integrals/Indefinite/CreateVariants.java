package gpo.TestingSystem.Integrals.Indefinite;

import java.util.*;

public class CreateVariants {


    public static String variant1()
    {
        return "\\frac{' + f + '\\cdot (x^{' + b + '})}{' + a + '}";
    }

    public static String generateFormula(boolean includeFrac) {

        // Список переменных {
        // Список переменных
        ArrayList<String> variables = new ArrayList<>();
        variables.add("' + a + '");
        variables.add("x");
        variables.add("' + f ^ {' + b + '} + '");
        variables.add("' + k + '");

        // Перемешиваем список переменных
        Collections.shuffle(variables);

        // Выбираем случайное количество переменных для использования в формуле
        int numVariables = (int) (Math.random() * 4) + 1;

        // Создаем список переменных, которые будут использоваться в формуле
        ArrayList<String> formulaVariables = new ArrayList<>();
        for (int i = 0; i < numVariables; i++) {
            String variable = variables.get(i);
            while (formulaVariables.contains(variable)) {
                variable = variables.get((variables.indexOf(variable) + 1) % variables.size());
            }
            formulaVariables.add(variable);
        }

        // Перемешиваем список переменных в формуле
        Collections.shuffle(formulaVariables);

        // Составляем формулу
        StringBuilder formula = new StringBuilder();
        for (int i = 0; i < formulaVariables.size(); i++) {
            // Добавляем переменную
            formula.append(formulaVariables.get(i));

            // Добавляем знак операции
            if (i < formulaVariables.size() - 1) {
                if (Math.random() < 0.33) {
                    formula.append("+");
                } else if (Math.random() < 0.67) {
                    formula.append("-");
                } else {
                    formula.append("\\cdot ");
                }
            }
        }

        // Добавляем оператор frac с вероятностью 50%
        if (includeFrac && Math.random() < 0.5) {
            String numerator = formula.toString();
            String denominator = "";
            // Создаем новую формулу для знаменателя
            while (denominator.isEmpty() || denominator.equals(numerator)) {
                denominator = generateFormula(false);
            }
            formula = new StringBuilder("\\frac{" + numerator + "}{" + denominator + "}");
        }

        return formula.toString();
    }
}
