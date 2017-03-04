/**
 * Created by Michael Sloma on 3/3/2017.
 * Calculates pi based upon a simulation of throwing darts randomly at a dart board.
 *
 * Michael Sloma
 * 12/01/2014
 *
 */
import java.util.Scanner;
import java.util.Random;

public class Darts
{
    //gets how many times the dart is thrown
    public static int inputPerTrial()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("How many times should the dart be thrown per trial? ");
        String thrown = in.next();
        int thrownInt = Integer.parseInt(thrown);
        return thrownInt;
    }

    //input trials
    public static int inputTrials()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("How many trials? ");
        String trials = in.next();
        int trialsInt = Integer.parseInt(trials);
        return trialsInt;
    }

    //calculate random numbers and assign pi estimate to array to save for average and output
    public static double[] random(int thrown, int trials)
    {
        double[] piEst = new double[trials]; // stores the results of the trials

        for (int e = 0; e < trials; e++)
        {
            double randomsInZone = 0;
            int n;
            Random rand = new Random();
            for(n = 0; n < thrown; n++)
            {
                //make randoms
                double random1 = rand.nextDouble();
                double random2 = rand.nextDouble();

                //compare randoms to 1
                double t = (Math.pow(random1, 2) + Math.pow(random2, 2));

                if (t <= 1)
                {
                    randomsInZone++;
                }
            }
            piEst[e] = 4 * (randomsInZone / n);
        }
        return piEst;
    }

    //claculates pi based upon average of results
    public static double piAvg(double [] piEst)
    {
        double sum = 0;
        for (double pi : piEst)sum += pi;
        double piAvg = sum / piEst.length;
        return piAvg;
    }
    //output pi
    public static void printResults(double [] piEst, double piAvg)
    {
        for (int n = 0; n < piEst.length; n++)
        {
            System.out.printf("%s %d %s %f\n", "Trial [", n, "]: pi = ", piEst[n]);
        }
        System.out.println("Estimate of pi = " + piAvg);
    }

    public static void main(String[] args)
    {
        int thrown = inputPerTrial();
        int trials = inputTrials();
        double[] piEst = random(thrown, trials);
        double piAvg = piAvg(piEst);
        printResults(piEst, piAvg);
    }
}
