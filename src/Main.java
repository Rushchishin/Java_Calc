import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws IOException{
        String output = "";
        String[] elements = input.split(" ");
        if (elements.length > 3) {
            throw new IllegalArgumentException(input + " invalid format");
        }
        if (isArabicProblem(elements)) {
            output = calcArabicProblem(elements);
        } else if (isRomanProblem(elements)) {
            output = calcRomanProblem(elements);
        } else {
            throw new IllegalArgumentException(input + " invalid format");
        }

        return output;
    }

    private static Boolean isArabicProblem (String[] input) {
        if (isArabic(input[0]) && isSymbol(input[1]) && isArabic(input[2])) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean isRomanProblem (String[] input) {
        if (isRoman(input[0]) && isSymbol(input[1]) && isRoman(input[2])) {
            return true;
        } else {
            return false;
        }
    }

    private static String calcArabicProblem (String[] input) {
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[2]);
        int x = 0;
        switch (input[1]) {
            case "+":
                x = a + b;
                break;
            case "-":
                x = a - b;
                break;
            case "*":
                x = a * b;
                break;
            case "/":
                x = a / b;
                break;
        }
        return Integer.toString(x);
    }

    private static String calcRomanProblem (String[] input) {
        int a = toArabic(input[0]);
        int b = toArabic(input[2]);
        int x = 0;
        switch (input[1]) {
            case "+":
                x = a + b;
                break;
            case "-":
                x = a - b;
                break;
            case "*":
                x = a * b;
                break;
            case "/":
                x = a / b;
                break;
        }
        return toRoman(x);
    }

    private static Boolean isRoman(String input) {
        String[] elements = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String element: elements) {
            if (input.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isArabic(String input) {
        int i;
        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        if ((i > 0) & (i <= 10)) {
            return true;
        }
        return false;
    }

    private static Boolean isSymbol(String input) {
        String[] elements = {"+", "-", "*", "/"};
        for (String element: elements) {
            if (input.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static int toArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int output = 0;

        List<Roman> romanNumerals = Roman.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            Roman symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                output += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return output;
    }

    public static String toRoman(int input) {
        if ((input <= 0) || (input > 4000)) {
            throw new IllegalArgumentException(input + " is not in range (0,4000]");
        }

        List<Roman> romanNumerals = Roman.getReverseSortedValues();

        int i = 0;
        StringBuilder output = new StringBuilder();

        while ((input > 0) && (i < romanNumerals.size())) {
            Roman currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= input) {
                output.append(currentSymbol.name());
                input -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return output.toString();
    }

}
