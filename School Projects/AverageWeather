import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        int numberOfDays;
        int[][] temperatureArray;   // {{morning, noon, night}, {...}, ...}
        int[][] timeArray;          // {{day1, day2, ...}, noon, night}

        System.out.println("How many days of data?");
        numberOfDays = scnr.nextInt();

        // Sets sizes of arrays
        temperatureArray = new int[numberOfDays][3];
        timeArray = new int[3][numberOfDays];
        
        // Appends temperatures to int array
        for(int i = 0; i < numberOfDays; i++) {
            System.out.println("Day " + (i + 1));
            System.out.println("Enter morning temp: ");
            temperatureArray[i][0] = scnr.nextInt();

            System.out.println("Enter noon temp: ");
            temperatureArray[i][1] = scnr.nextInt();

            System.out.println("Enter night temp: ");
            temperatureArray[i][2] = scnr.nextInt();
        }

        System.out.println("\n---Average Report---");

        // Calculates average for each day
        for(int i = 0; i < numberOfDays; i++) {
            System.out.println("Day " + (i + 1) + " average is: " + findAverage(temperatureArray[i]));
        }

        // Calculates the average for each time frame
        for(int i = 0; i < numberOfDays; i++) {
            for(int j = 0; j < 3; j++) {
                timeArray[j][i] = temperatureArray[i][j];
            }
        }

        // Calculates the average for morning, noon, night
        for (int[] time : timeArray) {
            if(time == timeArray[0]){
                System.out.println("Morning average for all days: " + findAverage(time));
            } else if (time == timeArray[1]) {
                System.out.println("Noon average for all days: " + findAverage(time));
            } else {
                System.out.println("Night average for all days: " + findAverage(time));
            }
        }

        scnr.close();
    }

    /**
     * Averages the numbers in an array; rounds up to the nearest whole number
     * @param nums Array of integers
     * @return Int of the average of the array
     */
    public static int findAverage(int[] nums){
        int average = 0;
        for (int i : nums) {
            average += i;
        }
        return Math.round((float)average / nums.length);
    }
}
