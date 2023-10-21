import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int averageNum;
        int inputNum;
        Scanner scnr = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a number to find its' average of the sum of its digits.");

            try {
                inputNum = scnr.nextInt();
            } catch (Exception e) {
                break;
            }


            averageNum = roundInput(inputNum);
            System.out.println(averageNum);
        }
        scnr.close();
    }

    /**
     * Averages the input, int number, to the nearest whole number
     * @param number
     * @return Average of the input to the nearest whole number
     */
    public static int roundInput(int number) {
        String num = Integer.toString(number);
        int sum = 0;

        for(int i = 0; i < num.length(); i ++) {
            sum += Character.getNumericValue(num.charAt(i));
        }
        
        return (int) Math.round((double)sum / num.length());
    }
}
