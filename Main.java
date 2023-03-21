import java.util.*;

public class Main {

    public static String convertToPostfix(String infix) {

        java.util.Stack<Character> operatorStack = new java.util.Stack<>();
        StringBuilder postfix = new StringBuilder();


        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);


            if (Character.isWhitespace(c)) {
                continue;
            }


            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            }

            else if (c == '(') {
                operatorStack.push(c);
            }

            else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    throw new IllegalArgumentException("Invalid expression: mismatched parentheses");
                } else {
                    operatorStack.pop();
                }
            }

            else {
                while (!operatorStack.isEmpty() && precedence(c) <= precedence(operatorStack.peek())) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(c);
            }
        }


        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek() == '(') {
                throw new IllegalArgumentException("Invalid expression: mismatched parentheses");
            }
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }



    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Infix input: ");
        String infix = scanner.nextLine();
        String postfix = convertToPostfix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
        scanner.close();
    }

}
