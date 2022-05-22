import org.jetbrains.annotations.Contract;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        String r;
        String[] calc = input.split(" ");
        boolean arab = false;
        boolean roman = false;
        if (calc.length != 3) {
            if (calc.length < 3) {
                return ("throws Exception //т.к. строка не является математической операцией");
            }
            return ("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String oper = calc[1];
        if (isNumeric(calc[0]) && isNumeric(calc[2])) {
            arab = true;
            if (Integer.parseInt(calc[0]) > 10 || Integer.parseInt(calc[2]) > 10) {
                return "Выход за пределы ";
            }
            return String.valueOf(operation(calc[0], calc[2], oper));
        } else {
            r = convertInRoman(calc[0], calc[2]);
            if (!r.equals("0")) {
                roman = true;
                calc = r.split(" ");
                if (oper.equals("-")) {
                    if (Integer.parseInt(calc[0]) < Integer.parseInt(calc[1])) {
                        return "throws Exception //т.к. в римской системе нет отрицательных чисел";
                    }
                }
                return convertInArab(String.valueOf(operation(calc[0], calc[1], oper)));
            }
        }
        return "throws Exception //т.к. используются одновременно разные системы счисления";
    }

    private static boolean isNumeric(String s) {
        int intValue;
        try {
            intValue = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String convertInRoman(String a, String b) {
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        boolean checkA = false;
        boolean checkB = false;
        for (int i = 0; i < roman.length; i++) {
            if (a.equals(roman[i])) {
                a = Integer.toString(i + 1);
                checkA = true;
            }
            if (b.equals(roman[i])) {
                b = Integer.toString(i + 1);
                checkB = true;
            }
        }
        if (checkA && checkB) {
            return a + " " + b;
        } else return "0";
    }

    private static String convertInArab(String a) {
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int c = Integer.valueOf(a);
        for (int i = 0; i < roman.length; i++) {
            if (i == c) {
                return roman[i - 1];
            }
        }
        return "Выход за пределы ";
    }

    private static String operation(String a, String b, String oper) {
        int a1 = Integer.parseInt(a);
        int b1 = Integer.parseInt(b);
        switch (oper) {
            case "+":
                return String.valueOf(a1 + b1);
            case "-":
                return String.valueOf(a1 - b1);
            case "*":
                return String.valueOf(a1 * b1);
            case "/": {
                if (b1 != 0) {
                    return String.valueOf(a1 / b1);
                } else return ("ArithmeticException/ т.к. деление на ноль");
            }
        }
        return "Неизвестная операция";
    }
}
