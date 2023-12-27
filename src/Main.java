import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            System.out.println("Input:");
            Scanner scanIn = new Scanner(System.in);
            String input = scanIn.nextLine();
            input = input.replace(" ", "");

            System.out.println();
            System.out.println("Output:");
            System.out.println(calc(input));
            System.out.println();
        }
    }

    public static String calc(String input) {
        boolean isArabic, isRoman;
        isArabic = false;
        isRoman = false;

        char operation = ' ';

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                isArabic = true;
            } else if (Character.isLetter(input.charAt(i))) {
                if (input.charAt(i) == 'I' || input.charAt(i) == 'V' || input.charAt(i) == 'X') isRoman = true;
                else {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("не подходящий символ"); //
                        throw new RuntimeException(e);
                    }
                }
            } else if (operation == ' ') {
                if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/') {
                    operation = input.charAt(i);
                }
                else {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("неподходящий символ"); //
                        throw new RuntimeException(e);
                    }
                }
            } else {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("неподходящий формат"); //
                    throw new RuntimeException(e);
                }
            }

            if (isArabic && isRoman) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("используются разные системы счисления"); //
                    throw new RuntimeException(e);
                }
            }
        }

        if (operation == ' ') {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("строка не является математической операцией"); //
                throw new RuntimeException(e);
            }
        }

        String[] operators;
        operators = input.split("\\"+operation);

        if (isRoman) {
            for(int j = 0; j < 2; j ++) {
                int number = 0;
                byte increase = 0;
                for (int i = operators[j].length()-1; i > -1; i--) {
                    if (operators[j].charAt(i) == 'I') {
                        if (increase <= 1) {
                            number += 1;
                        }
                        else {
                            number -= 1;
                        }
                        increase = 1;
                    }
                    else if (operators[j].charAt(i) == 'V') {
                        number += 5; increase = 5;
                    }
                    else {
                        increase = 10; number += 10;
                    }
                }
                operators[j] = String.valueOf(number);
            }
        }

        int result = 0;

        if (Integer.parseInt(operators[0]) < 1 || Integer.parseInt(operators[0]) > 10 || Integer.parseInt(operators[1]) < 1 || Integer.parseInt(operators[1]) > 10) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("введенное число не входит в диапозон от 1 до 10"); //
                throw new RuntimeException(e);
            }
        }

        result = switch (operation) {
            case '+' -> Integer.parseInt(operators[0]) + Integer.parseInt(operators[1]);
            case '-' -> Integer.parseInt(operators[0]) - Integer.parseInt(operators[1]);
            case '*' -> Integer.parseInt(operators[0]) * Integer.parseInt(operators[1]);
            case '/' -> Integer.parseInt(operators[0]) / Integer.parseInt(operators[1]);
            default -> result;
        };

        String output = "";

        if (isArabic) {
            output = String.valueOf(result);
        }
        else if (isRoman) {
            if (result < 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("римское число должно быть положительным");
                    throw new RuntimeException(e);
                }
            }
            while(result > 0) {
                if (result == 100) {
                    output = "C"; result -= 100;
                }
                else if (result >= 90) {
                    output = output.concat("XC"); result -= 90;
                }
                else if (result >= 50) {
                    output = output.concat("L"); result -= 50;
                }
                else if (result >= 40) {
                    output = output.concat("XL"); result -= 40;
                }
                else if (result >= 10) {
                    output = output.concat("X"); result -= 10;
                }
                else if (result == 9) {
                    output = output.concat("IX"); result -= 9;
                }
                else if (result >= 5) {
                    output = output.concat("V"); result -= 5;
                }
                else if (result == 4) {
                    output = output.concat("IV"); result -= 4;
                }
                else {
                    output = output.concat("I"); result -= 1;
                }
            }
        }
        return (output);
    }
}