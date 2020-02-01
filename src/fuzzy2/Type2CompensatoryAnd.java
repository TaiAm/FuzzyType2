/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;

import java.util.Scanner;

/**
 *
 * @author Taiwo Amoo
 */
/*
In line 51, Create an object of the Type2YagersMinMax class and initialise the calculateYagersWeightedDecisionmatrix()
as a variable so i dont have to assign values to yagersWeightedDecisionMatrix [] statically in this class since
it has been calculated in Type2YagersMinMax class

In line 108, Do as above

Create user interface to generate all results in the println() for all functions; 
*/

public class Type2CompensatoryAnd {
     double yagersWeightedDecisionMatrix[][] = Type2YagersMinMax.yagersWeightedDecisionMatrix;
     public static double [][] centroidDef;
    double andSet2[];
    double andSet3[];
    double secondAndSet[];
    double secondAndSet2[];
    double finalSet[];
    int nAlt=yagersWeightedDecisionMatrix.length, n= yagersWeightedDecisionMatrix[0].length;
     public double approx(double value, int dp)
            
    {
        double multiplier = Math.pow(10, dp);
        value *= multiplier;
        double new_value = Math.round(value);
        new_value /= multiplier;
        return new_value;
    }
   
     public double [] firstMultiplicationSet(){
    nAlt=yagersWeightedDecisionMatrix.length;
    n=yagersWeightedDecisionMatrix[0].length;
        
//     double [][] yagersWeightedDecisionMatrix= {{0.847,	0.7432,	0.6947,	0.5071,	0.5814,	0.6692,	0.6709,	0.5634,	0.7219,	0.5613,	0.7012,	0.8463,	0.902,	0.8562,	0.8458,	0.8502}
//             ,{0.5167,0.5482,0.6039,0.1874,0.6992,0.6692,0.2678,0.2413,0.344,0.2579,0.3678,0.4893,0.7114,0.416,0.7033,0.7161}
//             ,{0.5167,0.5482,0.5404,0.2528,0.5195,0.6692,0.2678,0.3744,0.5198,0.2579,0.5017,0.6627,0.5634,0.5922,0.7541,0.7917}};
         
     andSet2=new double[nAlt];
     
     for(int i= 0; i<nAlt;i++)
        {
            
            double product=1d;
            for(int j= 0;j<n;j++)
            {

                andSet2[i]= (product*=yagersWeightedDecisionMatrix[i][j]);  
            }
          
           
        }  
        
        
         /*Show andSet(product) values
          */
        System.out.println("++++++++++++++++++CUMMULATIVE PRODUCT OF EACH ALTERNATIVE++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" + approx(andSet2[col],4));
            }
            System.out.println(" ");
        
        System.out.println("+++++++++++++++++++CUMMULATIVE PRODUCT OF EACH ALTERNATIVE++++++++++++++++++");
       return andSet2;
    }
  
    //*Getthe overall product value for each alternative from the andset2 array and raising to power of 0.4*//
    public double [] overallPowerOfEachAlternative(){
        andSet3=new double[nAlt];
        for (int j=0;j<nAlt;j++)
        {
            andSet3[j]=Math.pow(andSet2[j],0.4);
        }
         
        System.out.println("++++++++++++++++++RAISED POWER OF EACH ALTERNATIVE++++++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" + approx(andSet3[col],4));
            }
            System.out.println(" ");
        
        System.out.println("+++++++++++++++++++RAISED POWER OF EACH ALTERNATIVE++++++++++++++++++");
       return andSet3;
    } //end of function 
       public double [] SecondMultiplicationSet()     
       {  
           secondAndSet=new double[nAlt];
//            double [][] yagersWeightedDecisionMatrix = {{0.847,	0.7432,	0.6947,	0.5071,	0.5814,	0.6692,	0.6709,	0.5634,	0.7219,	0.5613,	0.7012,	0.8463,	0.902,	0.8562,	0.8458,	0.8502}
//             ,{0.5167,0.5482,	0.6039,	0.1874,	0.6992,	0.6692,	0.2678,	0.2413,	0.344,0.2579,0.3678,0.4893,0.7114,0.416,0.7033,	0.7161}
//             ,{0.5167,0.5482,	0.5404,	0.2528,0.5195,0.6692,0.2678,0.3744,0.5198,0.2579,0.5017,0.6627,0.5634,0.5922,0.7541,0.7917}};
         
           for(int i= 0; i<nAlt;i++)
        {
            
            double secondproduct=1d;
            for(int j= 0;j<n;j++)
            {
            secondproduct=secondproduct*(1-yagersWeightedDecisionMatrix[i][j]);
            }
           secondAndSet[i]=secondproduct;
           
        } 
           System.out.println("++++++++++++++++++CUMMULATIVE SECONDSET PRODUCT OF EACH ALTERNATIVE++++++++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" + approx( secondAndSet[col],4));
            }
            System.out.println(" ");
        
        System.out.println("+++++++++++++++++++CUMMULATIVE SECONDSET PRODUCT OF EACH ALTERNATIVE++++++++++++++++++");
        return secondAndSet;
       }
       
       public double [] overallPowerOfSecondSetOfEachAlternative()
              {        
                secondAndSet2=new double[nAlt];  
                  for(int j=0;j<nAlt;j++)
                  {
                    secondAndSet2[j]=Math.pow(1-secondAndSet[j],0.6);
                  }
        
      System.out.println("++++++++++++++++++ SECONDSET PRODUCT SUBTRACTION OF EACH ALTERNATIVE++++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" + approx( secondAndSet2[col],4));
            }
            System.out.println(" ");
        
        System.out.println("+++++++++++++++++++SECONDSET PRODUCT SUBTRACTION OF EACH ALTERNATIVE+++++++++++++++++");
        return secondAndSet2;
                  }
               
        public double [] finalCompensatoryValue()
        {
            finalSet=new double[nAlt];
         for (int j=0;j<nAlt;j++)
        {
          finalSet[j]=andSet3[j]*secondAndSet2[j];
        }
          
      System.out.println("++++++++++++++++++ FINAL COMPENSATORY And VALUE FOR EACH ALTERNATIVE+++++++++++++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" + approx( finalSet[col],4));
            }
         System.out.println(" ");
        
        System.out.println("+++++++++++++++++++FINAL COMPENSATORY And VALUE FOR EACH ALTERNATIVE+++++++++++++++++");
      
         return finalSet;
        }
        
         public static void main(String ... args){
           
           CompensatoryAnd co = new CompensatoryAnd ();
           co.firstMultiplicationSet();
           co.overallPowerOfEachAlternative();
           co.SecondMultiplicationSet()  ;
           co.overallPowerOfSecondSetOfEachAlternative();
           co.finalCompensatoryValue();
         }
}
