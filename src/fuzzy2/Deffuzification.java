/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Taiwo Amoo
 */

/**
 *  A file data is meant to be read from excel spreadsheet which has the 
 * name of each column as criterion name: C1           ........C33
 Each C1 row representing 3 values     (0.3,0.5.0.7) till the 50th row for each criterion 

* ARITHMETIC OPERATIONS:
 * An Average of each column  from the file should be passed to the variable k ,l, m 
 * that i have declared in line 42 for each institution;i.e. for example:
 * C1                                         C2          ......C33
 * 0.2 0.3,0.5                             0.2 0.3,0.5
 * 0.3 0.4 0.6                             0.3 0.4 0.5
                
 (0.2+0.3)/2,(0.3+0.4)/2,(0.5+0.6)/2         (0.2+0.3)/2,(0.3+0.4)/2,(0.5+0.5) /2 
 
 *                k    l    m                         k    l    m
 * Average of C1=0.25,0.35,0.55        Average of C2=0.25,0.35,0.5
 
 and a single value will now be gotten  using the defuzzification formula in 71
 * and stored in each array index CEntroidDef representing each criterion which has been coded by myself already from line 55-71.

The CentroidDef Array is multi-dimensional because we have 4 institutions; 
* that means 4 different data files that this process will have to be repeated with: 
 An interface that prints out the result of the average values of k,l.m and all the functions in the class.
* 
* 
 */




public class Deffuzification {

    

    
//    double k,l,m;
//    int n, nAlt;
    public static double [][] centroidDef;
//    private static double [][] klmMatrix;
    private  String filename;
    private  String delimiter;
    public Deffuzification(String filename,String delimiter){
        this.filename = filename;
        this.delimiter= delimiter;
    }
    
    public void setFilename(String filename){
        this.filename = filename;
    }
    
    public void setDelimiter(String delimiter){
        this.delimiter = delimiter;
    }
    public  double [][] initiateProcess() throws Exception{
        ArrayList<double []> result = new ArrayList<>();
        String content = CriteriaAHP.loadFileAsString(filename);
        String [] tables = content.split(delimiter);
        for (int i = 0; i < tables.length; i++) {
        double [][][] fromFile = CriteriaAHP.stringToMatrix(tables[i]);//double [][][] fromFile =CriteriaAHP.readMatrixFile(filename, delimiter);
        double [][] average = averageColumn(fromFile);
         //display this average array
    System.out.println("the average of the combined matrix: ");
    for (int j = 0; j < average.length; j++) {
        for (int k = 0; k < average[i].length; k++) {
                System.out.print(average[j][k]);
                System.out.print(" ");
        }
        System.out.println();
    }
        double [] deffuzified = new double[average.length];
        for (int j = 0; j < average.length; j++) {
            deffuzified[j] = CriteriaAHP.defuzzify(average[j]);
        }
        result.add(deffuzified);
        }
        centroidDef = new double[result.size()][];
        for (int q = 0; q < result.size(); q++) {
            centroidDef[q] = result.get(q);
        }
       // centroidDef = result.toArray(new Double[2]);
        return centroidDef;
    }
    public static double[][] averageColumn(double[][][] matrix){
        double [][] result = new double[matrix[0].length][matrix[0][0].length];
        for (int i = 0; i < matrix.length; i++) {
            result = sum(result,matrix[i]);
        }
        //average the result
        result = divide(result,matrix.length);
        //klmMatrix = result;
        return result;
    }
    private static double[][] divide(double[][] result, int length) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j]/=length;
            }
        }
        return result;
    }
    private static double[][] sum(double[][] first, double[][] d){
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                first[i][j]+=d[i][j];
            }
        }
        return first;
    }
//    public double[][] calculateCentroid()
//    {
////        Scanner keyboard=new Scanner(System.in);
////        System.out.println("Enter the value for number of Alternative:");
////        nAlt=keyboard.nextInt();
////        System.out.println("Enter the value for number of criteria:");
////        n=keyboard.nextInt();  
//        //centroidDef=new double[nAlt][n];
//        for (int i=0; i< klmMatrix.length; i++)//for (int i=0; i< nAlt; i++)
//        {
//            for (int j=0; j< klmMatrix[i].length; j++)//for (int j=0; j< n; j++)
//            {   
////                System.out.println("Enter alternative's: "+(i+1)+" " +"with Criterion: "+(j+1)+" " +"fuzzy values");
////                System.out.println("Enter the value of k:");
////                k=keyboard.nextDouble();
////                System.out.println("Enter the value of l:");
////                l=keyboard.nextDouble();
////                System.out.println("Enter the value of m:");
////                m=keyboard.nextDouble();
//                //k =0,l=1,m=2
//
//                //centroidDef[i][j] = (((m-k) + (l-k)) / 3.0) + k;
//                centroidDef[i][j] = (((klmMatrix[i][2]-klmMatrix[i][0]) + (klmMatrix[i][1]-klmMatrix[i][0])) / 3.0) + klmMatrix[i][0];
//                //System.out.println("The BNF value for the criterion is:" +centroidDef[i][j]);     
//            } 
//        }
//        
//        //Debugging
//        System.out.println("The Deffuzification value for each criterion with respect to the institution are");
//        for(int x=0; x<centroidDef.length; x++)
//        {
//            for(int y=0; y<centroidDef[x].length; y++)
//            {
//                System.out.print(centroidDef[x][y] + " ");
//            }
//            System.out.println();
//        }
//               
//        return centroidDef;
//    }//end method 
}//end class