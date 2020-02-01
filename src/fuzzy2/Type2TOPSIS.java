/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;

/**
 *
 * @author Taiwo Amoo
 */
/**
  Create an object of Type2Deffuzification class and initialise the function for calculating defuzzification
  values as a variable so values  from Type2Deffuzification are returned into TOPSIS class 
  instead of statically assigning it again in line 59
  
  Create an object of Type2FuzzyAHP at line 106 and initialise the show_finalEigenvector as a variable
  * so the weight values can be generated automatically instead  of statically that u see there
  * 
  * Create a user interface to generate all results in the println() for all functions;
 */

public class Type2TOPSIS {
    
    public double [][] centroidDef =  Type2YagersMinMax.centroidDef;
    int nAlt, n;
    
    public double [][] normalizedDecisionMatrix;
    public double [][] weightedNormalizedDecisionMatrix;
    public double [] positifIdealSolution;
    public double [] negatifIdealSolution;
    public double []distanceAlternativeToPositifIdealSolution;
    public double []distanceAlternativeToNegatifIdealSolution;
    public double [] closenesCoefficient;
    public double [] sortClosenesCoefficient;    

    public Type2TOPSIS() {
//        CriteriaAHP cri=new CriteriaAHP();
//        Deffuzification def=new Deffuzification();
        nAlt = centroidDef.length;
        n = centroidDef[0].length;
    }
    

  
    
    public double approx(double value, int dp)
            
    {
        double multiplier = Math.pow(10, dp);
        value *= multiplier;
        double new_value = Math.round(value);
        new_value /= multiplier;
        return new_value;
    }
    
   
   public double [][] calculateNormalizedDecisionMatrix(){
        
        normalizedDecisionMatrix = new double[nAlt][n];
//       double [][] centroidDef= {{0.8666,0.7550,0.7550,0.7550,0.5000,0.6330,0.8106,0.5660,0.7000,0.7000,0.7000,0.7550,0.8106,
//     0.7000,0.5000,0.4443},{0.5660,0.5660,0.6776,0.5000,0.6330,0.6330,0.5000,0.2440,0.3110,0.4330,0.3660,0.3000,0.5000,
//         0.1333,0.2330,0.1883},{0.5660,0.5660,0.6220,0.5660,0.4330,0.6330,0.5000,0.3773,0.4886,0.4330,0.5000,0.5000,0.3110,0.3000,0.3110,0.3110}};
        /*
         * Calculate Normalized Decision Matrix
         */
        double [] sumPowSqrt = new double[n];
        for(int col= 0; col<n;col++)
        {
            
            double sumPow=0.d;
            for(int row = 0;row<nAlt;row++)
            {
                sumPow = sumPow + Math.pow(centroidDef[row][col],2);//add all the defuzzification/performance values of each alternative for 
                                                                    //the particular criterion
            }
            sumPowSqrt[col]= Math.sqrt(sumPow);
            for(int row= 0;row<centroidDef.length;row++)//instead of centroidDef.length,nAlt can be used
            {
                normalizedDecisionMatrix[row][col]=centroidDef[row][col]/sumPowSqrt[col];
            }
        }  
    

 /*
         * Show normalize decision matrix
         */
        System.out.println("+++++++++++++++++++NORMALIZED DECISION MATRIX++++++++++++++++++");
        
        for(int row = 0; row<nAlt;row++)
           {
            
            for(int col = 0;col<n;col++)
            {
                System.out.print("\t" + approx(normalizedDecisionMatrix[row][col],4));//create a method called (round) to 4 decimal places in another
                                                                                                     //class called defuzzification
            }
            System.out.println(" ");
        }
        System.out.println("+++++++++++++++++++NORMALIZED DECISION MATRIX++++++++++++++++++");
        return normalizedDecisionMatrix;
   }
   
   
   public double [][] calculateWeightedNormalizedDecisionMatrix()
    {
        weightedNormalizedDecisionMatrix = new double[nAlt][n]; 
         //double [] weights={0.0725,0.0660,0.0810,0.1510,0.0489,0.0549,0.1188,0.0630, 0.0571,0.1012, 0.0622,0.0371,0.0307,0.0272,0.0151,0.0125};
        double [] weights = Type2FuzzyAHP.comparisons;
        for(int col = 0; col<n;col++)
        {
            for(int row = 0;row<nAlt;row++)
            {
                weightedNormalizedDecisionMatrix[row][col] = normalizedDecisionMatrix[row][col] * weights[col];
            }
       }
        System.out.println("+++++++++++++++++++WEIGHTED NORMALIZED DECISION MATRIX++++++++++++++++++");
        for(int i = 0; i<nAlt;i++)
        {
            for(int j = 0; j<n;j++)
            {
                System.out.print("\t" +approx(weightedNormalizedDecisionMatrix[i][j],4));//need to define the round method with a known class
                
            }
            System.out.println("");
        }
        System.out.println("+++++++++++++++++++WEIGHTED NORMALIZED DECISION MATRIX++++++++++++++++++");
        return weightedNormalizedDecisionMatrix;
        
    }
    
   public double [] calculatePositifIdealSolution()
    {
       positifIdealSolution = new double[n];
       double max = 0d;
       for(int col = 0; col<n;col++)
        {
            max = 0d;
            for(int row = 0;row<nAlt;row++)
            {
                
                if(weightedNormalizedDecisionMatrix[row][col]>max)
                {
                    max=weightedNormalizedDecisionMatrix[row][col];
                }
                positifIdealSolution[col]=max;    
            }
           
       }
        System.out.println("+++++++++++++++++++POSITIF IDEAL SOLUTION++++++++++++++++++");
        for(int i = 0; i<n;i++)
        {
            System.out.print("\t" + approx(positifIdealSolution[i],4));
        }
        System.out.println("");
        System.out.println("+++++++++++++++++++POSITIF IDEAL SOLUTION++++++++++++++++++");
           return positifIdealSolution; 
    }
    
    public double [] calculateNegatifIdealSolution()
    {
        negatifIdealSolution = new double[n];
       double min = 0d;
       for(int col = 0; col<n;col++)
        {
           min = 1;
            for(int row = 0;row<nAlt;row++)
            {
                
                if(weightedNormalizedDecisionMatrix[row][col]<min)
                {
                    min=weightedNormalizedDecisionMatrix[row][col];
                }
                negatifIdealSolution[col]=min;    
            }
           
       }
        System.out.println("+++++++++++++++++++NEGATIF IDEAL SOLUTION++++++++++++++++++");
        for(int i = 0; i<n;i++)
        {
            System.out.print("\t" + approx(negatifIdealSolution[i],4));
        }
        System.out.println("");
        System.out.println("+++++++++++++++++++NEGATIF IDEAL SOLUTION++++++++++++++++++");
           return negatifIdealSolution; 
    }
    public double []calculateDistanceAlternativeToPositifIdealSolution()
    {
        distanceAlternativeToPositifIdealSolution= new double[nAlt];
        double [] temp = new double[nAlt];
        
        for(int i = 0; i<nAlt;i++)
        {
            temp[i]=0d;
        }
        
        for(int row = 0; row<nAlt;row++)
        {
            for(int col = 0;col<n;col++)
            {
                temp[row] = temp[row] + Math.pow((weightedNormalizedDecisionMatrix[row][col]- positifIdealSolution[col]), 2);
                
            }
            
            distanceAlternativeToPositifIdealSolution[row]= Math.sqrt(temp[row]);
       }
        System.out.println("+++++++++++++++++++ALTERNATIF TO POSITIF IDEAL SOLUTION++++++++++++++++++");
        for(int i = 0; i<nAlt;i++)
        {
            System.out.print("\t" + approx(distanceAlternativeToPositifIdealSolution[i],4));
        }
        System.out.println("");
        System.out.println("+++++++++++++++++++ALTERNATIF TO POSITIF IDEAL SOLUTION++++++++++++++++++");
       return distanceAlternativeToPositifIdealSolution;
    }
    
    public double [] calculateDistanceAlternativeToNegatifIdealSolution()
    {
        distanceAlternativeToNegatifIdealSolution= new double[nAlt];
        double [] temp = new double[nAlt];
        
        for(int i = 0; i<nAlt;i++)
        {
            temp[i]=0d;
        }
        for(int row = 0; row<nAlt;row++)
        {
            for(int col = 0;col<n;col++)
            {
                temp[row] = temp[row] + Math.pow((weightedNormalizedDecisionMatrix[row][col]- negatifIdealSolution[col]), 2);
                
            }
            
            distanceAlternativeToNegatifIdealSolution[row]= Math.sqrt(temp[row]);
       }
        System.out.println("+++++++++++++++++++ALTERNATIF TO NEGATIF IDEAL SOLUTION++++++++++++++++++");
        for(int i = 0; i<nAlt;i++)
        {
            System.out.print("\t" + approx(distanceAlternativeToNegatifIdealSolution[i],4));
        }
        System.out.println("");
        System.out.println("+++++++++++++++++++ALTERNATIF TO NEGATIF IDEAL SOLUTION++++++++++++++++++");
       return distanceAlternativeToNegatifIdealSolution;
    } 
   public double [] calculateClosenessCoefficient()
    {
        closenesCoefficient = new double[nAlt];
         for(int i = 0; i<nAlt;i++)
        {
            closenesCoefficient[i] = distanceAlternativeToNegatifIdealSolution[i]/(distanceAlternativeToNegatifIdealSolution[i] + distanceAlternativeToPositifIdealSolution[i]);
        }
        System.out.println("+++++++++++++++++++CLOSENESS COEFFICIENT++++++++++++++++++");
         for(int i = 0; i<nAlt;i++)
        {
            System.out.print("\t" + approx(closenesCoefficient[i],4));
        }
        System.out.println("");
        System.out.println("+++++++++++++++++++CLOSENESS COEFFICIENT++++++++++++++++++");
        return closenesCoefficient;
    }
   
   public static void main(String ... args){
           
           TOPSIS to = new TOPSIS ();
           to.calculateNormalizedDecisionMatrix();
           to.calculateWeightedNormalizedDecisionMatrix();
           to.calculatePositifIdealSolution();
           to.calculateNegatifIdealSolution();
           to.calculateDistanceAlternativeToPositifIdealSolution();
           to.calculateDistanceAlternativeToNegatifIdealSolution();
           to.calculateClosenessCoefficient();
           
           
        
        
   }
    
    
    
    
    
    
    
}
