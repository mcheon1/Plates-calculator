import java.util.Scanner;
import java.util.ArrayList;

public class WeightCalculator
{

//    public static void kgConversion(double weight, ArrayList arr) {
//        int closest = closestWeight(weight);
//    }

    /**
     * Computes the closest exact weight made with 45(s) or if it is equal to the exact weight, then it will
     * return 0.
     * @param weight
     * @return either 0 if it is exact, or the nearest exact weight
     */
    private static double closestWeightLB(double weight) {
        if (weight % 45 == 0) { //the weight entered is an exact weight with 45's
            return weight;
        }

        int weightInt = (int)(weight);
        int weightOnOneSide = (weightInt-45)/2;
        int numOfBigPlates = weightOnOneSide/45; //weight of one side without barbell
        return ((numOfBigPlates*2)*45)+45;
    }

    /**
     * Performs the weight calulation if lbs was entered from the main method
     * @param weight user entered value for weight they want to add to barbell in lbs
     * @param arr ArrayList holding the desired plate values to be placed on barbell
     */
    public static void lbConversion(double weight, ArrayList arr) {
        double closest = closestWeightLB(weight);

        if (closest > 45) { //determining how many 45's
            double closest1 = closest - 45;
            closest1 = (closest1 / 45) / 2;
            for (int i = 0; i < closest1; i++) {
                arr.add(45);
            }
        }

        double weight2 = (weight-closest)/2; //weight per side without 45's

        //Adds weight to the ArrayList
        while(weight2-25 >= 0) {
            weight2-=25;
            arr.add(25);
        }
        while(weight2-10 >= 0) {
            weight2-=10;
            arr.add(10);
        }
        while(weight2-5 >= 0) {
            weight2-=5;
            arr.add(5);
        }
        while(weight2-2.5 >= 0) {
            weight2-=2.5;
            arr.add(2.5);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList plates = new ArrayList();

        System.out.println("This program computes the weight to put on a standard barbell (one side only).");
        System.out.print("Please enter 'kg' or 'lb': ");
        String unit = s.nextLine();

        if (unit.equals("kg")) { //unit entered was kilos
//            System.out.println("\nPlease enter a weight: ");
//            double weight1 = s.nextDouble();
//            kgConversion(weight1, plates);
        } else if (unit.equals("lb")) { //unit entered was pounds
            System.out.println("\nPlease enter a weight ending in 0 or 5 and that is greater than 45: ");
            double weight2 = s.nextDouble();

            if(weight2 < 45) { //less than a standard barbell
                throw new IllegalArgumentException("You must enter a number that is greater than or equal to 45.");
            }
            else if (weight2 == 45) { //enters the weight of a bare barbell
                plates.add(45);
            } else { //more than 45
                lbConversion(weight2, plates);
            }
        } else { //no correct unit was entered
            throw new IllegalArgumentException("You did not enter a correct unit.");
        }

        System.out.println(plates);
    }
}