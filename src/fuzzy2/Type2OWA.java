/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;
import java.util.Scanner;
import ui.OWAForm;
/**
 *
 * @author Taiwo Amoo
 */

/* 
Create User Interface to generate all results of println()

*/
public class Type2OWA {
    int num_of_cri;
    public CriteriaWeightPair cr[];
    public static InstitutionValueHolder [] valueHolder;
    public Type2OWA(int nInst, int nCriteria)
    {
        num_of_cri = nCriteria;
        cr = new CriteriaWeightPair[num_of_cri];
    }
    
    public void fillObject(double[] weight, double[][] criteria, int instI)
    {
        //@param instI :=> institution's index
        
        //Debug
        /*8System.out.println("Printing criteria for object " + instI + " in fillObject method");
        for(int j=0; j<num_of_cri; j++)
            System.out.print(criteria[instI][j] + " ");
        
        System.out.println("Printing weight in fillObject method");
        System.out.println();
        for(int j=0; j<num_of_cri; j++)
            System.out.print(weight[j] + " ");**/
        //End
        
        //CriteriaWeightPair[] cr = new CriteriaWeightPair[num_of_cri];
        
        for(int i = 0; i<num_of_cri; i++)
            cr[i] = new CriteriaWeightPair();
        
        for(int i = 0; i<num_of_cri; i++)
        {
            cr[i].criteria = criteria[instI][i];
            cr[i].weight = weight[i];
        }
        
        System.out.println("closing");
    }
    public static void main(String ... args)
    {
        double[] weight;
        double[][] criteriaValue;
        int nCriteria = Type2YagersMinMax.centroidDef[0].length, nInst = Type2YagersMinMax.centroidDef.length;
//        Scanner key = new Scanner(System.in);
//        System.out.println("Input the number of criteria: ");
//        nCriteria = key.nextInt();
//        System.out.println("Input the number of institutions: ");
//        nInst = key.nextInt();
        valueHolder = new InstitutionValueHolder[nInst];
        Type2OWA[] objInst = new Type2OWA[nInst];
        for(int i = 0; i<nInst; i++){
            objInst[i] = new Type2OWA(nInst, nCriteria);
            valueHolder[i] = new InstitutionValueHolder();
        }
        
//        weight = new double[nCriteria];
//        criteriaValue = new double[nInst][nCriteria];
        
        //Deffuzification tt = new Deffuzification();
        //CriteriaAHP criAHP = new CriteriaAHP();
        
        weight = Type2FuzzyAHP.comparisons;//weight = criAHP.show_finalEigenvector();
        criteriaValue =Type2YagersMinMax.centroidDef;//criteriaValue = tt.calculateCentroid();
        
        //begin: => debugging
        System.out.println("Weight Display\n");
        for(int i=0; i<nCriteria; i++)
            System.out.print(weight[i] + " ");
        
        System.out.println("\nCriteria Display, each row for each institution\n");
        for(int i=0; i<nInst; i++)
        {
            for(int j=0; j<nCriteria; j++)
                System.out.print(criteriaValue[i][j] + " ");
            System.out.println();
        }
        //end
        
        for(int i = 0; i<nInst; i++)
            objInst[i].fillObject(weight, criteriaValue, i);
        
        System.out.println("Criteria Pair for each institution shown below");
        for(int i=0; i<nInst; i++)
        {
            System.out.println("Criteria Pair for institution " + (i + 1));
            for(int j=0; j<nCriteria; j++)
            {
                System.out.print("Criteria: " + objInst[i].cr[j].criteria + " Weight: " + objInst[i].cr[j].weight);
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        
        //sorting the performance values the criteria of each institution
        for(int k = 0; k<nInst; k++)
        {
            for(int i = 0; i < nCriteria; i++)
            {
                double highest = objInst[k].cr[i].criteria;
                int index = i;
                for(int j = i + 1; j < nCriteria; j++)
                {
                    if(highest < objInst[k].cr[j].criteria)
                    {
                        highest = objInst[k].cr[j].criteria;
                        index = j;
                    }
                }
            
                if (index != i)
                {
                    double temp1 = objInst[k].cr[i].criteria;
                    double temp2 = objInst[k].cr[i].weight;
                    objInst[k].cr[i].criteria = objInst[k].cr[index].criteria;
                    objInst[k].cr[i].weight = objInst[k].cr[index].weight;
                    objInst[k].cr[index].criteria = temp1;
                    objInst[k].cr[index].weight = temp2;
                }
            }
        }
        //the objinst is sorted and it contains the it contans an array of list and criteria pair
        //End Sorting
        
        //Debugging after sorting weights
        System.out.println("Criteria Pair for each institution after sorting shown below");
        for(int i=0; i<nInst; i++)
        {
            System.out.println("Criteria Pair for institution " + (i + 1));
            for(int j=0; j<nCriteria; j++)
            {
                System.out.print("Criteria: " + objInst[i].cr[j].criteria + " Weight: " + objInst[i].cr[j].weight);
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        //End Debugging
        
        //set the value 
        for (int i = 0; i < nInst; i++) {
            valueHolder[i].weight = new double[objInst[i].cr.length];
            valueHolder[i].criteria = new double[objInst[i].cr.length];
            for (int j = 0; j < objInst[i].cr.length; j++) {
                valueHolder[i].weight[j] = objInst[i].cr[j].weight;
                valueHolder[i].criteria[j] = objInst[i].cr[j].criteria;
            }
            
        }
        //Begin Calculating new weights
        double[][] temp = new double[nInst][nCriteria]; 
        for(int k=0; k<nInst; k++)
        {
            for(int i=0; i<nCriteria; i++)
            {
                if(i==0)
                    temp[k][i] =  objInst[k].cr[i].weight * objInst[k].cr[i].weight;
                else
                {
                    double dSum_total = 0.00;
                    double dSum_prev = 0.0;
                    for(int j=i; j>=0; j--)
                    {
                        dSum_total += objInst[k].cr[j].weight;
                    }
                    dSum_prev = dSum_total - objInst[k].cr[i].weight;
                    temp[k][i] = Math.pow(dSum_total, 2) - Math.pow(dSum_prev, 2);
                }
            }
        }
        //End
        
        //storing temporary values into the weight slots fot each OWA object
        for(int i=0; i<nInst; i++)
        {
            for(int j=0; j<nCriteria; j++)
               objInst[i].cr[j].weight = temp[i][j]; 
        }
        
        //Debugging after sorting weights
        System.out.println("New weight display");
        for(int i=0; i<nInst; i++)
        {
            System.out.println("Criteria Pair for institution " + (i + 1));
            for(int j=0; j<nCriteria; j++)
                System.out.print("Weight: " + objInst[i].cr[j].weight); 
            System.out.println();
        }
        //store the third table column
          for (int i = 0; i < nInst; i++) {
            valueHolder[i].sortedWeight = new double[objInst[i].cr.length];
            for (int j = 0; j < objInst[i].cr.length; j++) {
                valueHolder[i].sortedWeight[j] = objInst[i].cr[j].weight;
            }
            
        }
        //End Debugging
        
        //Begin Calculating D(x) for each institution
        InstPair[] D_of_x = new InstPair[nInst];
        for(int i=0; i<nInst; i++)
            D_of_x[i] = new InstPair();
        
        for(int k=0; k<nInst; k++)
        {
            for(int i=0; i<nCriteria; i++)
            {
                D_of_x[k].d_of_x += objInst[k].cr[i].criteria * objInst[k].cr[i].weight;
                D_of_x[k].inst = k;
            }
        }
        //End
        
        //Debugging, printing the D(x) of institutions
        System.out.println();
        for(int j=0; j<nInst; j++)
        {
            System.out.println("Institution: " + (D_of_x[j].inst + 1) + ", " + 
                             "Rank Value: " + D_of_x[j].d_of_x);
            System.out.println();
        }
        //End
        
        //sorting D(x) values
        for(int i = 0; i < nInst; i++)
        {
            double highest = D_of_x[i].d_of_x;
            int index = i;
            for(int j = i + 1; j < nInst; j++)
            {
                if(highest < D_of_x[j].d_of_x)
                {
                    highest = D_of_x[j].d_of_x;
                    index = j;
                }
            }
            
            if (index != i)
            {
                double temp1 = D_of_x[i].d_of_x;
                int temp2 = D_of_x[i].inst;
                D_of_x[i].d_of_x = D_of_x[index].d_of_x;
                D_of_x[i].inst = D_of_x[index].inst;
                D_of_x[index].d_of_x = temp1;
                D_of_x[index].inst = temp2;
            }
        }
        //End Sorting
        for (int i = 0; i < D_of_x.length; i++) {
            valueHolder[i].dx =D_of_x[i].d_of_x;
        }
        //Debugging, checking the ranking of institutions
        System.out.println();
        for(int j=0; j<nInst; j++)
        {
            System.out.println("Institution: " + (D_of_x[j].inst + 1) + ", " + 
                             "Rank Value: " + D_of_x[j].d_of_x);
            System.out.println();
        }
        //End
        //lauch the three interfaces to display the institution value
        for (int i = 0; i < nInst; i++) {
            OWAForm form = new OWAForm(valueHolder[i]);
            form.setTitle("TYPE 2 OWA VALUE FOR INSTITUTION "+(i+1));
            form.setVisible(true);
        }
    }
}