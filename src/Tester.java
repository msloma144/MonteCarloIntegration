import java.util.ArrayList;

/**
 * Created by Michael Sloma on 3/3/2017.
 */
public class Tester {
    public static void main(String[] args){
        double lowerLimit = MonteCarloIntegration.inputLowerLimit();
        double upperLimit = MonteCarloIntegration.inputUpperLimit();
        double subsections = MonteCarloIntegration.inputSubesctions();
        //String function = MonteCarloIntegration.inputFunction();
        String function = "x+x^2";
        ArrayList<Double> samplePoints = MonteCarloIntegration.generatePoints(lowerLimit, upperLimit, subsections);

        System.out.println("Area: " + MonteCarloIntegration.integration(samplePoints, upperLimit, lowerLimit, function));

        //System.out.println(FunctionEvaluation.evaluationAtPoint(2, function));
    }
}
