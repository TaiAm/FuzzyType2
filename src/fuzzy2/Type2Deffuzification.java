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
/*
A file data is meant to be read from excel spreadsheet which has the 
 * name of each column as criterion name:         C1                         ........C33
 * each row of C1 representing 12 values 1) 2,5,10,10;1,1; 7,9,10,10;0.8,0.8        
                                         2) 1,4,10,10;1,1; 7,9,10,10;0.6,0.6  till the 50th row for each criterion 

            and there would be all in total 4  different institutions' C1-C33
 
THE PROGRAMS ARITHMETIC OPERATIONS
An Average of each column  from the file for a particular institution should be done:(1+2)/2 for example:
      C1s overall performance values:
(2+1)/2),(5+4)/2),(10+10)/2),(10+10)/2);min(1,1),min(1,1); ((7+7)/2),((9+9)/2),(10+10)/2),(10+10)/2);min(0.8,0.4);min(0.8;0.4)             
      

After averaging, we have a new single dimensional array/matrix of 12 values representing each criterion:
                                         .........C33
 for example:  
        C1                                                    C2
(1.5,4.5,10,10; 1,1); (7,9,10,10; 0.4,0.4)      (1.5,5.0,10,10;1,1); (7,9,10,10; 0.6,0.6)  till........C33      
   


then defuzzification is performed for each 12 values in the matrix using:
       see formula on page51 on the topmost right hand side of the paper attached
 We wil now have a single value representing each criterion: for example
C1=2.5
C2=0.5
...........till C33

This process is now repeated for the files of the other 3 institutions and multi-dimensional array is 
created to store the values of C1-C33 of the 4 institutions 
 
Then Create interface

The Type2CentroidDef Array should be multi-dimensional because we have 4 institutions; 
* that means 4 different data files that this process will have to be repeated with: 

* 
 */
public class Type2Deffuzification {
    private String filename;
    private String delimiter;
    public static double [][] centroidDef;
    public Type2Deffuzification(String filename,String delimiter){
        this.filename = filename;
        this.delimiter = delimiter;
    }
    public void setFilename(String filename){
        this.filename = filename;
    }
    
    public void setDelimiter(String delimiter){
        this.delimiter = delimiter;
    }
//    public double [] initProcess()throws Exception{
//        double [][][] fromFile =CriteriaAHP.readMatrixFile(filename, delimiter);
//        double [][] average = averageColumn(fromFile);
//        double [] deffuzified = new double[average.length];
//        for (int i = 0; i < average.length; i++) {
//            deffuzified[i] = Type2FuzzyAHP.deffuzify(average[i]);
//        }
//        return deffuzified;
//    }
     public  double [][] initProcess() throws Exception{
        ArrayList<double []> result = new ArrayList<>();
        String content = CriteriaAHP.loadFileAsString(filename);
        String [] tables = content.split(delimiter);
        for (int i = 0; i < tables.length; i++) {
            //System.out.println("table "+(i+1));
        double [][][] fromFile = CriteriaAHP.stringToMatrix(tables[i]);//double [][][] fromFile =CriteriaAHP.readMatrixFile(filename, delimiter);
        double [][] average = averageColumn(fromFile);
                //display this average array
    System.out.println("the average(type 2) of the combined matrix: ");
    for (int j = 0; j < average.length; j++) {
        for (int k = 0; k < average[i].length; k++) {
                System.out.print(average[j][k]);
                System.out.print(" ");
        }
        System.out.println();
    }
        double [] deffuzified = new double[average.length];
        for (int j = 0; j < average.length; j++) {
            deffuzified[j] = Type2FuzzyAHP.deffuzify(average[j]);
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
        double [][] result = matrix[0];//new double[matrix[0].length][matrix[0][0].length];
        for (int i = 1; i < matrix.length; i++) {
           // System.out.println("row "+(i+1));
            result = sum(result,matrix[i]);
        }
        //average the result
        result = divide(result,matrix.length);
        //klmMatrix = result;
        return result;
    }
   private static double[][] sum(double[][] first, double[][] d)throws IllegalArgumentException{
       int rowCount = first.length;
       int columnCount = first[0].length;
       if (d.length != rowCount || d[0].length!=columnCount) {
           throw new IllegalArgumentException("the length of the two array does not match!");
       }
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                 if (j==4  || j==5 || j==10|| j==11) {
                            first[i][j] = Math.min(first[i][j], d[i][j]);
                            continue;
                        }
                first[i][j]+=d[i][j];
            }
        }
        return first;
    }
    private static double[][] divide(double[][] result, int length) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (j==4  || j==5 || j==10|| j==11) {
                        continue;
                    }
                result[i][j]/=length;
            }
        }
        return result;
    }
}
