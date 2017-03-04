/**
 * Created by Michael Sloma on 3/4/2017.
 */
public class FunctionEvaluation {
    static String reduceFunction(String function){
        int i;
        for(i = 0; i < function.length(); i++){
            if(function.substring(i,i+1).equals("+")) break;
            if(function.substring(i,i+1).equals("-")) break;
            if(function.substring(i,i+1).equals("*")) break;
        }
        function = function.substring(0, i);
        return function;
    }

    static double evaluationAtPoint(double point, String function) {
        double area = 0; //sum of small section
        String character;

        int iteration = 0; //iteration

        while (!(function.length() == 0)) {
            if (iteration > 20) break; //iteration time out to prevent infinite run

            //System.out.println("Function: " + function);
            character = function.substring(0, 1);
            //System.out.println("char: " + character);

            if (character.equals("x")) {
                //if the x is something that has a power, put the power in
                if (function.substring(1, 2).equals("^")) {
                    //System.out.println("pow");
                    area += Math.pow(point, Double.parseDouble(function.substring(2, 3)));
                    function = function.substring(3);
                }

                else{
                    area += point;
                    function = function.substring(1);
                }
            }

            else if (function.substring(0, 1).equals("+")) {
                //System.out.println("add Rec");
                //System.out.println("Reduced: " + reduceFunction(function.substring(1)));
                area += evaluationAtPoint(point, reduceFunction(function.substring(1)));
                function = function.substring(4);
            }

            else if (function.substring(0, 1).equals("-")) {
                //System.out.println("sub Rec");
                //System.out.println("Reduced: " + reduceFunction(function.substring(1)));
                area -= evaluationAtPoint(point, reduceFunction(function.substring(1)));
                function = function.substring(4);
            }

            else if (function.substring(0, 1).equals("*")) {
                //System.out.println("mult Rec");
                //System.out.println("Reduced: " + reduceFunction(function.substring(1)));
                area = area * evaluationAtPoint(point, reduceFunction(function.substring(1)));
                function = function.substring(4);
            }
            iteration++;
            //System.out.println("Area: " + area);
        }
        return area;
    }

}