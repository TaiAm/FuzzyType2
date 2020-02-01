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

/*
In line 51, Create an object of the YagersMinMax class and initialise the calculateYagersWeightedDecisionmatrix()
as a variable so i dont have to assign values to yagersWeightedDecisionMatrix [] statically in this class since
it has been calculated in YagersMinMax class

In line 108, Do as above

Create user interface to generate all results in the println() for all functions; 
*/
public class CompensatoryAnd {
   
    double yagersWeightedDecisionMatrix[][];
    double normalisedyagersWeightedDecisionMatrix[][];
    public static double [][] centroidDef;
     double yagersWeights [];
    double maxRowValue [];
    double andSet2[];
    double andSet3[];
    double secondAndSet[];
    double secondAndSet2[];
    double finalSet[];
    int nAlt, n;
    public double approx(double value, int dp)
            
    {
        double multiplier = Math.pow(10, dp);
        value *= multiplier;
        double new_value = Math.round(value);
        new_value /= multiplier;
        return new_value;
    }
     
     public double [][]calculateNormalisedYagersWeightedDecisionMatrix ()
     {      
        yagersWeightedDecisionMatrix=new double[centroidDef.length][centroidDef[0].length];
        for (int i=0;i<centroidDef.length;i++)
        {
          
           for(int j=0;j<centroidDef[0].length;j++)
           {
           yagersWeightedDecisionMatrix[i][j]=Math.pow(centroidDef[i][j]/10,yagersWeights[j]);
               
           } 
        }     
        nAlt = yagersWeightedDecisionMatrix.length;
        n = yagersWeightedDecisionMatrix[0].length;
       normalisedyagersWeightedDecisionMatrix = new double[yagersWeightedDecisionMatrix.length][yagersWeightedDecisionMatrix[0].length];
//       maxRowValue=new double[nAlt];   
//       double max = 0d;
//       
//       for(int row = 0; row<nAlt;row++)
//        {
//            max = 0d;
//            for(int col = 0;col<n;col++)
//            {
//                
//                if(yagersWeightedDecisionMatrix[row][col]>max)
//                {
//                    max=yagersWeightedDecisionMatrix[row][col];
//                }
//                 
//            }
//            maxRowValue[row]=max;    
//           
//       }
//       
//        for(int row = 0; row<nAlt;row++)
//        {
//          
//            for(int col = 0;col<n;col++)
//            {
//                normalisedyagersWeightedDecisionMatrix[row][col]=yagersWeightedDecisionMatrix[row][col]/maxRowValue[row];
//                
//                
//            }
//        }   
        System.out.println("+++++++++++++++++++NORMALISED YAGERS WEIGHTED DECISION MATRIX++++++++++++++++++");
        for(int i = 0; i<yagersWeightedDecisionMatrix.length;i++)
        { 
        for(int j=0;j<yagersWeightedDecisionMatrix[0].length;j++)
        { 
            
       System.out.print("\t" +(normalisedyagersWeightedDecisionMatrix[i][j]));
        }
            System.out.println(" ");
        }
        System.out.println("+++++++++++++++++++YAGERS WEIGHTED DECISION MATRIX++++++++++++++++++");
      return normalisedyagersWeightedDecisionMatrix;
    }     


   
     public double [] firstMultiplicationSet(){
//        Scanner keyboard=new Scanner(System.in);
//
//    System.out.println("Enter the number of alternatives");
//    nAlt=keyboard.nextInt();
//    
//    System.out.println("Enter the number of criteria");
//    n=keyboard.nextInt();
       //double [][] yagersWeightedDecisionMatrix= YagersMinMax.yagersWeightedDecisionMatrix;
//     double [][] yagersWeightedDecisionMatrix= {{0.847,	0.7432,	0.6947,	0.5071,	0.5814,	0.6692,	0.6709,	0.5634,	0.7219,	0.5613,	0.7012,	0.8463,	0.902,	0.8562,	0.8458,	0.8502}
//             ,{0.5167,0.5482,0.6039,0.1874,0.6992,0.6692,0.2678,0.2413,0.344,0.2579,0.3678,0.4893,0.7114,0.416,0.7033,0.7161}
//             ,{0.5167,0.5482,0.5404,0.2528,0.5195,0.6692,0.2678,0.3744,0.5198,0.2579,0.5017,0.6627,0.5634,0.5922,0.7541,0.7917}};
        
     andSet2=new double[normalisedyagersWeightedDecisionMatrix.length];
     
     for(int i= 0; i<andSet2.length;i++)
        {
            
            double product=1d;
            for(int j= 0;j<normalisedyagersWeightedDecisionMatrix[i].length;j++)
            {
            andSet2[i]= (product*=normalisedyagersWeightedDecisionMatrix[i][j]) ;  
            }
          
           
        }  
        
        
         /*Show andSet(product) values
          */
        System.out.println("++++++++++++++++++CUMMULATIVE PRODUCT OF EACH ALTERNATIVE++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" + (andSet2[col]));
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
                System.out.print("\t" +(andSet3[col]));
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
            secondproduct=secondproduct*(1-normalisedyagersWeightedDecisionMatrix[i][j]);
            }
           secondAndSet[i]=secondproduct;
           
        } 
           System.out.println("++++++++++++++++++CUMMULATIVE SECONDSET PRODUCT OF EACH ALTERNATIVE++++++++++");
        
            
            for(int col = 0;col<nAlt;col++)
            {
                System.out.print("\t" +( secondAndSet[col]));
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
                System.out.print("\t" + ( secondAndSet2[col]));
            }
            System.out.println(" ");
        
        System.out.println("+++++++++++++++++++SECONDSET PRODUCT SUBTRACTION OF EACH ALTERNATIVE+++++++++++++++++");
       return  secondAndSet2;
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
                System.out.print("\t" + ( finalSet[col]));
            }
         System.out.println(" ");
        
        System.out.println("+++++++++++++++++++FINAL COMPENSATORY And VALUE FOR EACH ALTERNATIVE+++++++++++++++++");
      
         return finalSet;
        }
        
         public static void main(String ... args){
           
           CompensatoryAnd co = new CompensatoryAnd ();
           co.calculateNormalisedYagersWeightedDecisionMatrix();
           co.firstMultiplicationSet();
           co.overallPowerOfEachAlternative();
           co.SecondMultiplicationSet()  ;
           co.overallPowerOfSecondSetOfEachAlternative();
           co.finalCompensatoryValue();
         }
        }

