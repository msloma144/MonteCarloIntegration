/**
 * Created by Michael Sloma on 3/4/2017.
 * Basic function evaluation exclusively for polynomials with coefficients w/ length 1 and powers of length 1.
 */
public class FunctionEvaluation {
    static boolean tryParseInt(String input) {
        try {
            int i = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    static String reduceFunction(String function){
        //reduce the input function down by taking the portion of the function before the next operation
        int i;
        for(i = 0; i < function.length(); i++){
            if(function.substring(i,i+1).equals("+")) break;
            else if(function.substring(i,i+1).equals("-")) break;
            else if(function.substring(i,i+1).equals("*")) break;
        }

        if(i == 0){
            i = function.length() - 1; //if there is no function attached to the sub-function, just return it
        }

        function = function.substring(0, i); // return the smaller sub function
        return function;
    }

    static int subfunclength(String function){
        //runs the function through 2 cycles of reduction to
        return reduceFunction(function).length();
    }

    static double evaluationAtPoint(double point, String function) {
        String character;
        double sum = 0;
        int iteration = 0; //iteration

        while (!(function.length() == 0)) {
            if (iteration > 10) break; //iteration time out to prevent infinite run

            //System.out.println("Function: " + function);
            character = function.substring(0, 1);
            //System.out.println("char: " + character);

            if (character.equals("x")) {
                //if the x is something that has a power, put the power in
                if (function.length() > 1) {
                    if(function.substring(1, 2).equals("^")){
                        //System.out.println("pow");
                        sum += Math.pow(point, Double.parseDouble(function.substring(2, 3)));
                        //function = function.substring(3);
                        function = "";
                    }
                    else{
                        sum += point;
                        function = function.substring(1);
                        //function = "";
                    }
                }
                else{
                    sum += point;
                    function = function.substring(1);
                    //function = "";
                }
            }
            //if the leading char is + then reduce the function and add what remains to the already computed sum
            else if (character.equals("+")) {
                //System.out.println("add Rec");
                //System.out.println("Reduced: " + reduceFunction(function.substring(1)));
                sum += evaluationAtPoint(point, reduceFunction(function.substring(1)));//take off the front operator and submits it to
                                                                                        //be reduced to the sub function
                //the problem is the whole 4 goodness when the +3x^2 is of len 4 so the 2 gets rolled over
                function = function.substring(subfunclength(function.substring(1)) + 1);
            }
            //if the leading char is - then reduce the function and subtract what remains to the already computed sum
            else if (character.equals("-")) {
                //System.out.println("sub Rec");
                //System.out.println("Reduced: " + reduceFunction(function.substring(1)));
                sum -= evaluationAtPoint(point, reduceFunction(function.substring(1))); //take off the front operator and submits it to
                                                                                        //be reduced to the sub function\
                function = function.substring(subfunclength(function.substring(1)) + 1);
            }
            //if the leading char is * then reduce the function and mult what remains to the already computed sum
            else if (character.equals("*")) {
                //System.out.println("mult Rec");
                //System.out.println("Reduced: " + reduceFunction(function.substring(1)));
                sum = sum * evaluationAtPoint(point, reduceFunction(function.substring(1)));//take off the front operator and submits it to
                                                                                                //be reduced to the sub function
                function = function.substring(subfunclength(function.substring(1)) + 1);
            }

            else if(tryParseInt(character)){
                if(function.substring(1,2).equals("+") || function.substring(1,2).equals("-")){
                    sum += Double.parseDouble(character);
                    function = function.substring(1);
                }
                else {
                    double num = Double.parseDouble(character);
                    sum += num * evaluationAtPoint(point, reduceFunction(function.substring(1)));
                    function = function.substring(subfunclength(function.substring(1)) + 1);
                }
            }
            iteration++;
            //System.out.println("SUM: " + sum);
        }
        return sum;
    }
}