/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;

import java.util.ArrayList;

/**
 *
 * @author Taiwo Amoo
 */
public class Type2FuzzyAHP {

    public static boolean isLastTwoValues(int length, int current) {
        int gapFromBehind =2;
        return (length -1)- current < gapFromBehind;
    }

   
    
    
    /*
    A file is read from the excel spreadsheeet in a format like:
        C1                                     C2                     .........C33
C1      1                          
C2 2,5,10,10;1,1; 7,9,10,10;0.8,0.8        
C3 1,4,10,10;1,1; 7,9,10,10;0.6,0.6     
************************************************************************************8
Another table/matrix like this:
       C1                                      C2                    .........C33
C1     1             
C2 3,5,10,10;1,1; 7,9,10,10;0.4,0.4      
C3 2,6,10,10;1,1; 7,9,10,10;0.7,0.7    

PROGRAMS ARITHMETIC OPERATIONS
Then an addition operation and then average to collapse the two matrices/table into one:
       It is all divided by 2 to get average because there are 2 tables:
    for example: C1 +C1 form the first and second table divided by 2
    C1                                                                                 ....C33              .........C33
C1  (1+1)/2                 
C2 (2+3)/2),(5+5)/2),(10+10)/2),(10+10)/2);min(1,1),min(1,1); ((7+7)/2),(9+9)/2),(10+10)/2),(10+10)/2);min(0.8,0.4);min(0.8;0.4)             
C3 (1+2)/2),(4+6)/2),(10+10)/2),(10+10)/2);min(1,1),min(1,1); (7+7)/2), (9+9)/2),(10+10)/2),(10+10)/2);min(0.6,0.7);min(0.6,0.7)      

The results of the above computation is now a new table with 12 values each for each criterion:
   C1                                         .........C33
C1  1                       
C2  2.5,5,10,10; 1,1   7,9,10,10; 0.4,0.4              
C3  1.5,5,10,10; 1,1   7,9,10,10; 0.6,0.6    

then defuzzification is performed for each 12 values in the matrix using:
       see formula on page51 on the topmost right hand side of the paper attached
    

Then a ready to use table is now achieved which is passed into the show_finalEigenvector().
 Then Create interface
*/
    private String filenames;
    private String delimiter;
    private double threshold;
    public static double [] comparisons;
    public static int alternatives;
   public Type2FuzzyAHP(String filenames,String delimiter,double threshold){
       this.filenames = filenames;
       this.delimiter = delimiter;
       this.threshold = threshold;
   }    
   public double [] init() throws Exception{
  if (filenames== null) {
        throw new IllegalArgumentException("the File names passed is empty");
    }
    System.out.println("setting up....please wait....will inform you when done.....");
    ArrayList<double [][][]> toCombine = new ArrayList<>();
    String content = CriteriaAHP.loadFileAsString(filenames);
    String [] tables = content.split(delimiter);
    alternatives = tables.length;
        for (int i = 0; i < tables.length; i++) {
            //double [][][] fromFile = readMatrixFile(filenames[i],delimiter);//passed!
            String tableData = tables[i];
            double [][][] fromFile = CriteriaAHP.stringToMatrix(tableData);
            toCombine.add(fromFile);
        }
    double [][][] average = combineMatrix(toCombine);
     //display this average array
    System.out.println("the average(type 2) of the combined matrix: ");
    for (int i = 0; i < average.length; i++) {
        for (int j = 0; j < average[i].length; j++) {
            for (int k = 0; k < average[i][j].length; k++) {
                System.out.print(average[i][j][k]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
     //perform defuzzification here
    double [][] matrix = Type2FuzzyAHP.deffuzifyArray(average);
    comparisons = CriteriaAHP.getEigenVector(matrix,threshold);
    return comparisons;
   }
   //just take care of the last two digits here
    
    //function to combine two matrix together
    public static double [][][] combineMatrix(ArrayList allMatrix){
        double [][][] first = (double [][][])allMatrix.get(0);
        double [][][] result = new double[first.length][first[0].length][first[0][0].length];
        for (int i = 0; i < allMatrix.size(); i++) {
            double [][][] temp =(double [][][])allMatrix.get(i);
            for (int j = 0; j < temp.length; j++) {
                for (int k = 0; k < temp[j].length; k++) {
                    for (int l = 0; l < temp[j][k].length; l++) {
                        //do the get minimum or maximum operation for the last two values
                        if (isLastTwoValues(temp[j][k].length/2,l)) {
                            if (i > 0) {//to prevent index not found error
                                double [][][] t =(double [][][])allMatrix.get(i-1);
                                result[j][k][l] = Math.min(temp[j][k][l], t[j][k][l]);//show take the minimum of the two adjascent 
                                continue;
                            }
                            else{
                                result[j][k][l] = temp[j][k][l];
                                continue;
                            }
                           
                        }
                        result[j][k][l]+=temp[j][k][l];
                    }
                }
            }
        }
        return getAverage(result,allMatrix.size());
    }
    
        private static double[][][] getAverage(double[][][] result, int size) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < result[i][j].length; k++) {
                    if (isLastTwoValues(result[i][j].length/2,k)) {
                        continue;
                    }
                    //sud get the sum of the value and then divide or d
                    result[i][j][k]/=size;
                }
            }
        }
        return result;
    }
        public static double[][] deffuzifyArray(double [][][] values) throws Exception{
            double [][]  result = new double[values.length][values[0].length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    result[i][j] = deffuzify(values[i][j]);
                }
            }
            return result;
        }
      public static double deffuzify(double[] d) throws Exception{
        if (d.length !=12) {
            throw new Exception("the length of the array should be 12");
        }
        System.out.println("input array for defuzzification and the final weight");
        CriteriaAHP.printArray(d);
        //there are upper bound and lower bounds here
        double [] upperBound = new double [d.length/2];
        double [] lowerBound = new double [d.length/2];
        System.arraycopy(d, 0, upperBound, 0, d.length/2);
        System.arraycopy(d, d.length/2, lowerBound, 0, d.length/2);
        double betaU = Math.min(upperBound[upperBound.length -2], upperBound[upperBound.length -1]);
        double betaL = Math.min(lowerBound[lowerBound.length -2], lowerBound[lowerBound.length -1]);
        double alphaU = Math.max(upperBound[upperBound.length -2], upperBound[upperBound.length -1]);
        double alphaL = Math.max(lowerBound[lowerBound.length -2], lowerBound[lowerBound.length -1]);
        double uu =upperBound[3];
        double iu =upperBound[0];
        double m1u =upperBound[1];
        double m2u = upperBound[2];
        //for the lower bound
        double ul =lowerBound[3];
        double il =lowerBound[0];
        double m1l =lowerBound[1];
        double m2l = lowerBound[2];
       double result = ((uu-iu)+(betaU*m1u -iu)+(alphaU*m2u -iu))/4 +iu + ((ul-il)+(betaL*m1l -il)+(alphaL*m2l -il))/4 +il;
       result= result/2;
       System.out.println(result);
       return result;
    }
}
