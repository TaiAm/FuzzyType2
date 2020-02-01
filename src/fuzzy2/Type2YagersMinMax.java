/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Taiwo Amoo
 */
/* 
Create an object of the Type2FuzzyAHP class and initialise the show_finalEigenvector() as a variable(weight)
in line 77 so i dont have to assign values to weights[] statically in this class since
it has been calculated in CriteriaAHP class

In line 99,please, Create an object of Type2Deffuzification class and initialise the function that 
calculates the deffuzification values as a variable so values are returned into YagersMinMax class since 
they have been calculated in the Type2Deffuzification class instead of statically assigning in line 58
  
In line 130 the calculateMinValueOfAlternatives(); the alternative [] storing the name of institution
is not showing 

In line 161 , the calculateSortMaxOfMinOfAlternatives(),the sorting is not working and the alternative[] for storing institution name
is still null, please help me sort the highest value to be displayed as the first institution, 
and the next highest as the second institutions name and so on...

Create a user interface to generate all results in the println() for all functions
*/
public class Type2YagersMinMax {
    
     public static double [][] centroidDef;
    double [] weights;
    double yagersWeights [];
    public static double yagersWeightedDecisionMatrix[][];
    double minValueOfAlternatives[];
    double SortMaxOfMinOfAlternatives [];
    String [] alternative;
    int nAlt, n;
    
     public double approx(double value, int dp)
            
    {
        double multiplier = Math.pow(10, dp);
        value *= multiplier;
        double new_value = Math.round(value);
        new_value /= multiplier;
        return new_value;
    }
    
    
    
    public double [] calculateYagersWeight(){
        
//    Scanner keyboard=new Scanner(System.in);
//
//    System.out.println("Enter the number of alternatives");
//    System.out.println("nAlt=");
//    nAlt=keyboard.nextInt();
//    
//    System.out.println("Enter the number of criteria");
//    n=keyboard.nextInt();
//
//    alternative=new String[nAlt];
//    System.out.println("Enter the name of Alternative:");
//    for(int i=0; i<nAlt;i++)
//    {
//        System.out.print("Alternative "+(i+1)+":");
//        alternative[i]=keyboard.next();
//    }
//       
//       /* 
//       Calculate Yagers Weight 
//       */
//     //collect the parameter needed to initiate the criteriaAhp class
//    System.out.println("how many file do you want to combine for the ahp?");
//        
//    int numFiles = keyboard.nextInt();
//    String [] paths = new String[numFiles];
//    for (int i = 0; i < numFiles; i++) 
//    {
//         System.out.println("Enter the path to the file "+(i+1));
//        paths[i] = keyboard.nextLine();
//    }
//    
//    System.out.println("Enter the delimiter for separating each cell content");
//    String delimiter = keyboard.next();
//    Type2FuzzyAHP ahp2 = null;
//        try {
//            double threshold =0;
//            ahp2 = new  Type2FuzzyAHP(paths, delimiter, threshold);
//            ahp2.init();
//        } catch (Exception ex) {
//            Logger.getLogger(YagersMinMax.class.getName()).log(Level.SEVERE, null, ex);
//            System.exit(1);
//        }
        double [] weights=  Type2FuzzyAHP.comparisons;
        n = weights.length;
        //double [] weights={0.0725,0.0660,0.0810,0.1510,0.0489,0.0549,0.1188,0.0630, 0.0571,0.1012, 0.0622,0.0371,0.0307,0.0272,0.0151,0.0125};
        yagersWeights =new double[n];
                for (int i=0;i<n;i++)
        {
       yagersWeights[i]=weights[i]*n;  
        }
        
 /*
         * Show normalize decision matrix
         */
        System.out.println("+++++++++++++++++++YAGERS WEIGHTS++++++++++++++++++");
        for(int row = 0; row<n;row++)
        {
            
       System.out.print("\t" + approx(yagersWeights[row],4));//create a method called (round) to 4 decimal places in another
                                                                                                     //class called defuzzification
        }
            System.out.println(" ");
        
        System.out.println("+++++++++++++++++++YAGERS WEIGHT++++++++++++++++++");
   return yagersWeights;
}
//    private double[][] getCentroid2Def() throws Exception{
//        double [][] result = new double[nAlt][];
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("enter the delimiter for the files");
//        String delimiter = keyboard.next();
//        Type2Deffuzification def = new Type2Deffuzification(null, delimiter);
//        for (int i = 0; i < nAlt; i++) {
//            System.out.println("Enter the file path for file"+(i+1));
//            String filename = keyboard.next();
//            def.setFilename(filename);
//            result[i] =def.initProcess();
//        }
//        
//        return result;
//    }
    public double [][]calculateYagersWeightedDecisionmatrix(){
//        double [][] centroidDef= {{0.8666,0.7550,0.7550,0.7550,0.5000,0.6330,0.8106,0.5660,0.7000,0.7000,0.7000,0.7550,0.8106,
//     0.7000,0.5000,0.4443},{0.5660,0.5660,0.6776,0.5000,0.6330,0.6330,0.5000,0.2440,0.3110,0.4330,0.3660,0.3000,0.5000,
//         0.1333,0.2330,0.1883},{0.5660,0.5660,0.6220,0.5660,0.4330,0.6330,0.5000,0.3773,0.4886,0.4330,0.5000,0.5000,0.3110,0.3000,0.3110,0.3110}};
               // double [][] centroidDef=null;
        try {
            centroidDef= Type2Deffuzification.centroidDef;//getCentroid2Def();
        } catch (Exception ex) {
            Logger.getLogger(YagersMinMax.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        nAlt  = centroidDef.length;
        yagersWeightedDecisionMatrix=new double[nAlt][n];
        for (int i=0;i<nAlt;i++)
        {
          
           for(int j=0;j<n;j++)
           {
           yagersWeightedDecisionMatrix[i][j]=Math.pow(centroidDef[i][j],yagersWeights[j]);
               
           } 
        }
        /*
         * Show yagers decision matrix
         */
        System.out.println("+++++++++++++++++++YAGERS WEIGHTED DECISION MATRIX++++++++++++++++++");
        for(int i = 0; i<nAlt;i++)
        { 
        for(int j=0;j<n;j++)
        { 
            
       System.out.print("\t" + approx(yagersWeightedDecisionMatrix[i][j],4));
        }
            System.out.println(" ");
        }
        System.out.println("+++++++++++++++++++YAGERS WEIGHTED DECISION MATRIX++++++++++++++++++");
      return yagersWeightedDecisionMatrix;
         }
      
    public double [] calculateMinValueOfAlternatives(){
          double min=1d;
          
          minValueOfAlternatives=new double[nAlt];
          for(int i=0;i<nAlt;i++)
          { 
              min=1d;
              for(int j=0;j<n;j++){
                
                if(yagersWeightedDecisionMatrix[i][j]<min)
                {
                    min=yagersWeightedDecisionMatrix[i][j];
                }
                minValueOfAlternatives[i]=min;    
            }
         
          }
          System.out.println("+++++++++++++++++++MINIMUM VALUE FOR EACH ALTERNATIVE++++++++++++++++++");
          
         //alternative=new String[nAlt];
          
          for(int i = 0; i<nAlt;i++)
        {
         //   System.out.println("The minimum value for alternative:" +alternative[i] +approx(minValueOfAlternatives[i],4));
        }
     
        System.out.println("+++++++++++++++++++MINIMUM VALUE FOR EACH ALTERNATIVE++++++++++++++++++");
           return minValueOfAlternatives;
      
              }
    
      public double [] calculateSortMaxOfMinOfAlternatives(){//not well done
          double t;
          String ts;
      SortMaxOfMinOfAlternatives=new double[nAlt];                
           for (int j=1;j<nAlt;j++)
           {
               if(minValueOfAlternatives[j-1] < minValueOfAlternatives[j])
                {
                    ts = alternative[j-1];
                    t = minValueOfAlternatives[j-1];
                    
                    minValueOfAlternatives[j-1]=minValueOfAlternatives[j];
                    alternative[j-1]=alternative[j];
                    
                    minValueOfAlternatives[j]=t;
                    alternative[j]=ts;
                   SortMaxOfMinOfAlternatives [j-1]= minValueOfAlternatives[j-1];
                }
               
           }
         System.out.println("+++++++++++++++++++YAGERS OPTIMAL(MAX) VALUE FOR EACH ALTERNATIVE++++++++++++++++++");//not well done 
         for (int j=0;j<nAlt;j++)
         { 
            // System.out.println("The Yagers optimal value of each alternative"+alternative[j]+approx(SortMaxOfMinOfAlternatives [j],4));
             
         }
         return SortMaxOfMinOfAlternatives;
          }
//    public static void main(String ... args){
//           
//           YagersMinMax ya = new YagersMinMax ();
//           ya.calculateYagersWeight();
//           ya.calculateYagersWeightedDecisionmatrix();
//           ya.calculateMinValueOfAlternatives();
//           ya.calculateSortMaxOfMinOfAlternatives();
//    }
}
