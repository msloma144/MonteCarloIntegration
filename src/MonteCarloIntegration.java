import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Michael Sloma on 3/3/2017.
 */
public class MonteCarloIntegration {

    public static boolean tryParseInt(String input) {
        try {
            int i = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    static double inputUpperLimit() {
        Scanner in = new Scanner(System.in);
        //input upper limit
        System.out.print("Please enter upper limit: ");
        double upperLimit = in.nextDouble();
        System.out.println();

        return upperLimit;
    }

    static double inputLowerLimit(){
        Scanner in = new Scanner(System.in);
        //input lower limit
        System.out.print("Please enter lower limit: ");
        double lowerLimit = in.nextDouble();
        System.out.println();

        return lowerLimit;
    }

    static String inputFunction(){
        Scanner in = new Scanner(System.in);
        //input function
        System.out.print("Please enter function: ");
        String function = in.next();
        System.out.println();

        return function;
    }

    static double inputSubesctions(){
        Scanner in = new Scanner(System.in);
        //input number of subsections
        System.out.print("Please enter number of subsections: ");
        double subsections = in.nextDouble();
        System.out.println();

        return subsections;
    }

    static ArrayList<Double> generatePoints(double lowerLimit, double upperLimit, double subsections){
        ArrayList<Double> samplePoints = new ArrayList<>(); //list of all the sample points

        double range = upperLimit - lowerLimit; //range of limits

        double increment = range/subsections; //increments
        System.out.println("Increment: " + increment);
        for(long i = 0; i < subsections; i++){
            samplePoints.add(lowerLimit + (i*increment));
        }
        System.out.println("Size: " + samplePoints.size());
        return samplePoints;
    }

    static double integration(ArrayList<Double> samplePoints, double upperLimit, double lowerLimit, String function){
        double area = 0;
        //generating the sum of the points
        for (int i = 0; i < samplePoints.size(); i++) {
            //System.out.println(area);
                        //evaluates the given function at the point,
                        //can just enter a function here and use samplepoint.get(i)
                        //as the x variable
            area += FunctionEvaluation.evaluationAtPoint(samplePoints.get(i), function);
        }
        //limit area times 1 / (# of subsections)
        area = area * ((upperLimit - lowerLimit) * (1/(double)samplePoints.size()));

        return area;
    }
}
