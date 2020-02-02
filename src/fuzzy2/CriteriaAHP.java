/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class CriteriaAHP {

    private static void print(String[] toConvert) {
        for (int i = 0; i < toConvert.length; i++) {
            System.out.println();
           System.out.print(toConvert[i]+" "); 
        }
    }

//    private static void print(double[][] d) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    
//     int numCriteria;
     int NUMBER_COMPARISON;
     public static double [] comparisons;//made static and public so that other code could access the value generated from this module.
     double[][] a;
     double [] s;
     public static int alternatives;
     public String [] criteria;
     public double weights[];
     public  double threshold;
     private String filenames;
     private String delimiter;
    
public CriteriaAHP(String  filenames,String delimiter,double threshold)throws Exception{
//    this.numCriteria = numCriteria;
    this.filenames = filenames;
    this.delimiter =delimiter;
    this.threshold = threshold;
}

public void initiateProcess() throws Exception{
    if (filenames== null) {
        throw new IllegalArgumentException("the File names passed is empty");
    }
    System.out.println("setting up....please wait....will inform you when done.....");
    ArrayList<double [][][]> toCombine = new ArrayList<>();
    String content = loadFileAsString(filenames);
    String [] tables = content.split(delimiter);
    alternatives = tables.length;
        for (int i = 0; i < tables.length; i++) {
            //double [][][] fromFile = readMatrixFile(filenames[i],delimiter);//passed!
            String tableData = tables[i];
            double [][][] fromFile = stringToMatrix(tableData);
            toCombine.add(fromFile);
        }
    double [][][] average = combineMatrix(toCombine);//passed!
    //display this average array
    System.out.println("the average of the combined matrix: ");
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
    double [][] matrix = defuzzifyArray(average);//passed!
    comparisons = getEigenVector(matrix,threshold);
}

public static String loadFileAsString(String filename) throws Exception{
    FileReader fStream = new FileReader(filename);
    BufferedReader reader= new BufferedReader(fStream);
    String content="";
    String line = "";
    while((line=reader.readLine())!= null){
        content+=(line+"\r\n");
    }
    return content;
}
//function to load file into matrix given file string content
public static double [][][] stringToMatrix(String table){
    ArrayList<double[][]> tempStorage = new ArrayList<>();
    String [] line = table.trim().split("\n");
    for (int i = 0; i < line.length; i++) {
     String [] rowItem = removeEmptyItem(line[i].trim().split(" "));
        double [][] numericalRowItem = new double[rowItem.length][];
        for (int j = 0; j < rowItem.length; j++) {
            String []tempItem = removeEmptyItem(rowItem[j].trim().split(","));
            double [] commaSeparated = stringArrayToDouble(tempItem);
            numericalRowItem[j] = commaSeparated;
        }
        tempStorage.add(numericalRowItem);
      }
    return tempStorage.toArray(new double[0][][]);
}
public static double[] getEigenVector(double[][] matrix,double minValue){
    double [] result = new double[matrix.length];
    double [] temp = new double[matrix.length];
    boolean isFirst =true;
    double comparedResult = Double.MAX_VALUE;
     do{
          matrix = multiply(matrix, matrix);
         double [] rowSum = sumRow(matrix);
        
         if (isFirst) {
             isFirst = false;
             result = normaliseVector(rowSum);
             continue;
         }
         temp = result;
         result = normaliseVector(rowSum);
         comparedResult = compareMatrixEquality(result, temp);
     } while(comparedResult > minValue); 
     return temp;//the latest calculated value
}
    public static double[] sumRow(double[][] nextMatrix) {
        double [] result = new double [nextMatrix.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = sumArray(nextMatrix[i]);
        }
        return result;
    }

    private static double sumArray(double[] d) {
        double sum =0;
        for (int i = 0; i < d.length; i++) {
            sum+=d[i];
        }
        return sum;
    }

    private static double[] normaliseVector(double[] rowSum) {
        double sum = sumArray(rowSum);
        for (int i = 0; i < rowSum.length; i++) {
            rowSum[i]/=sum;
        }
        return rowSum;
    }
public static double compareMatrixEquality(double [] vector1,double [] vector2)throws IllegalArgumentException{
    if (vectorNotSameSize(vector1,vector2)) {
        throw new IllegalArgumentException("matrix must be of the same size.");
    }
    double sumDiff =0;
    for (int i = 0; i < vector1.length; i++) {
         sumDiff+=Math.abs(vector1[i]-vector2[i]);
    }
    return sumDiff;
}
private static boolean vectorNotSameSize(double [] m,double []n){
    return m.length !=n.length;
}
//function to read matrix file from a file
public static double [][][] readMatrixFile(String fileName,String delimiter) throws Exception{
    ArrayList tempStorage = new ArrayList();
    FileReader fStream = new FileReader(fileName);
    BufferedReader reader= new BufferedReader(fStream);
    String line= "";
    while((line = reader.readLine())!=null){
        String [] rowItem = removeEmptyItem(line.trim().split(delimiter));
        double [][] numericalRowItem = new double[rowItem.length][];
        for (int i = 0; i < rowItem.length; i++) {
            String []tempItem = rowItem[i].split(",");
            double [] commaSeparated = stringArrayToDouble(tempItem);
            numericalRowItem[i] = commaSeparated;
        }
        tempStorage.add(numericalRowItem);
}
    double [][][]result = new double[tempStorage.size()][][];
    for (int i = 0; i < tempStorage.size(); i++) {
        result[i] = (double [][])tempStorage.get(i);
    }
    return result;
}

private static double [] stringArrayToDouble(String [] toConvert){
//    if (toConvert.length !=3) {
//            System.out.println("index  length is not equal to 3");
//            print(toConvert);
//        }
    double [] commaSeparated = new double[toConvert.length];
        for (int j = 0; j < commaSeparated.length; j++) {
//            System.out.println(toConvert[j]);
            commaSeparated [j] = Double.parseDouble(toConvert[j]);
        }
        return commaSeparated;
}

//function to combine two matrix together
    public static double [][][] combineMatrix(ArrayList allMatrix){
        double [][][] first = (double [][][])allMatrix.get(0);
        double [][][] result = new double[first.length][first[0].length][first[0][0].length];
        for (int i = 0; i < allMatrix.size(); i++) {
            double [][][] temp =(double [][][])allMatrix.get(i);
            for (int j = 0; j < temp.length; j++) {
                for (int k = 0; k < temp[j].length; k++) {
                    for (int l = 0; l < temp[j][k].length; l++) {
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
                    result[i][j][k]/=size;
                }
            }
        }
        return result;
    }
public static double[][] defuzzifyArray(double[][][] matrixArray){
    if (matrixArray.length ==0) {
        throw new IllegalArgumentException("matrix is empty");
    }
    int m = matrixArray.length;
    int n = matrixArray[0].length;
    double [][] result = new double[m][n];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            result[i][j] = defuzzify(matrixArray[i][j]);
        }
    }
    return result;
}

public static double defuzzify(double [] criterion) throws IllegalArgumentException{
    if (criterion.length ==1 ) {
        return criterion[0];
    }
    if (criterion.length == 3) {
        double k= criterion[0];
        double l = criterion[1];
        double m = criterion[2];
        return (((m-k) + (l-k)) / 3.0) + k;
    }
    else{
        throw new IllegalArgumentException("No formula specified for array size greater than three");
    }
}

public static void show_matrix(double [][] matrix)
{
    //display the elements of the matrix a
    System.out.println("\nThe matrix a is:");
        for(int i=0; i<matrix.length;i++)
    {
        for(int j=0; j<matrix[i].length; j++)
            System.out.print(matrix[i][j]+"    ");
        System.out.println();   
    }
}

//code for multiplying matrix
	public static double[][] multiply(final double[][] input1,final double[][] input2) {
		final double[][] result = new double[input1.length][input2[0].length];
		for (int i = 0; i < input1.length; i++) {
			for (int j = 0; j < input2[0].length; j++) {
				for (int k = 0; k < input1[0].length; k++) {
					result[i][j] += input1[i][k] * input2[k][j];
				}
			}
		}

		return result;
	}
        
        public static String [] removeEmptyItem(String [] array){
            ArrayList<String> result = new ArrayList();
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals("")) {
                    continue;
                }
                result.add(array[i]);
            }
            return result.toArray(new String[result.size()]);
        }
        
        public static double [] updateIndex(double [] vector,int index,double value){
            double [] result = new double[vector.length];
            if (index > result.length) {
                JOptionPane.showMessageDialog(null, "ERROR", "INVALID INDEX SPECIFIED", JOptionPane.ERROR_MESSAGE);
                return vector;
            }
            double newValue = vector[index] + value;
            double old = vector[index];
            result[index]=newValue;
            for (int i = 0; i < vector.length; i++) {
                if (i ==index) {
                    continue;
                }
                result[i] =((1-newValue)/(1-old)) * vector[i];
            }
            return result;
        }
//public void ComputeComparisonMatrixValues(){
//    
//    Scanner keyboard=new Scanner(System.in);
////    System.out.println("Enter the number of criteria");
////    System.out.println("numCriteria=");
//   // numCriteria=keyboard.nextInt();
//    
//    NUMBER_COMPARISON=(numCriteria*numCriteria-numCriteria)/2;
//    comparisons=new double[NUMBER_COMPARISON];
//
//    double [][]a = new double[numCriteria][numCriteria];
//    String criteria []=new String [numCriteria];
//
//    System.out.println("Enter the criteria:");
//    
//    for(int i=0; i<numCriteria;i++)
//    {
//        System.out.print("Criterion "+(i+1)+":");
//        criteria[i]=keyboard.next();
//    }
//
//    System.out.println("Enter the comparison");
//        int m=0; 
//        for(int i=0; i<numCriteria;i++)
//        {
//            for(int j=i+1; j<numCriteria;j++)
//            {
//                System.out.println("Compare "+criteria[i]+" with "+criteria[j]+":");
//                comparisons[m]=keyboard.nextDouble();
//                System.out.println("comparisons "+m+"   "+comparisons[m]);//just to print out the stored values 
//                                                      //in the respective indexes
//                m++;
//            }
//        }
//  
//    
//   }    

//  public double[][] initialize_matrix()
//{
//    //initialize the matrix a
//    double a[][] =new double[numCriteria][numCriteria];
//   
//    int k=0;        
//    for(int i=0; i<numCriteria; i++)
//    {
//        for(int j=0; j<numCriteria;j++)
//        {
//            if(i==j)
//                a[i][j]=1;
//            else if(i<j)
//            {
//
//                a[i][j]=comparisons[k];
//                k++;
//            }
//
//            else if(i>j)
//                a[i][j]=1/a[j][i];
//        }
//    } 
//    return a;
//    
//}


//public double[] show_finalEigenvector(){
//     double rowsum;
//     rowsum=0;
//    
//     double [] s =new double[numCriteria];
//    
//     
//    for (int i=0; i<numCriteria;i++){
//       s[i] = 0;
//     for(int j=0; j<numCriteria;j++){
//         s[i] += a[i][j];}
//    }
//        
//     for(int k=0; k<numCriteria; k++){
//     System.out.println("The row sum total is:"+" "+s[k]);}  
//
//     for (int m=0; m<numCriteria;m++){
//         rowsum+=s[m];
//     }
//     
//      weights = new double[numCriteria];
//      for (int m=0; m<numCriteria;m++){
//          weights[m] = s[m]/rowsum;
//          System.out.println("The weight of Criterion" +(m+1)+"  "+weights[m] + ", ");
//      }
//      return weights;
//    }
        
        public static void main(String [] args){
            double [] d = new double []{0.2,0.3,0.5};
            int index =0;
            double value =0;
            double [] newValue = updateIndex(d, index, value);
            printArray(newValue);
        }
        
        public static void printArray(double [] arr){
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
 }
