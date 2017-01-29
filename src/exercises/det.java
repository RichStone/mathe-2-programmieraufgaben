package exercises;

import java.util.ArrayList;

public class det 
{
    public static int nrOfMult;
    public static int nrOfMultRec;
    
    //Berechnung mit 1. Normalform
    public static double calcDet(double[][] A)
    {
        double[][] matrix = testDet.readMatrixFromFile(Main.filename);

        //get matrix dimension
        int n = matrix.length;
        
        if(n == 1) {
        	return 1.0;
        }
        if(n == 2) {
        	return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }        
        
        int originalColumn = 0;
        int originalRow = 0;
        int startRow = 1; //line to start the iteration
        ArrayList<Double> detMultiplicators = new ArrayList<Double>();
        for(int currentRow = startRow; currentRow < n; currentRow++) 
        {
        	double diagonalValue = matrix[originalRow][originalColumn];
    		if(diagonalValue != 1) {
    			double divisor = diagonalValue;
    			for(int x = originalColumn; x < n; x++) {
    				matrix[originalRow][x] /= divisor;
    			}
    			detMultiplicators.add(divisor);
    		}
    		if(matrix[currentRow][originalColumn] - diagonalValue == 0) {
    			
    			matrix[currentRow][originalColumn] = 0;
    			
    			//update the other columns
				for(int currentColumn = originalColumn + 1; currentColumn < n; currentColumn++) {
					matrix[currentRow][currentColumn] = matrix[currentRow][currentColumn] - matrix[originalRow][currentColumn];
				}
    		}
    		else {
    			double multiplicator = matrix[currentRow][originalColumn];
    			//imitate the subtraction (no need for multiplications or real subtractions because we already know it is zero)
    			matrix[currentRow][originalColumn] = 0;
    			
    			//calculate the other columns
    			for(int currentColumn = originalColumn + 1; currentColumn < n; currentColumn++) {
					matrix[currentRow][currentColumn] =   matrix[currentRow][currentColumn] - matrix[originalRow][currentColumn] * multiplicator;
					//count the multiplications
        			nrOfMult++;
				}
    		}

    		if(currentRow == n - 1) {
    			originalColumn++;
    			startRow++;
    			originalRow++;
    			currentRow = originalRow;
    		}
        }
        //calculate det from diagonal line
        double det = 1;
        for(int i = 0; i < n; i++) {
        	det *= matrix[i][i];
        	nrOfMult++;
        }
        if(!detMultiplicators.isEmpty()) {
        	for(int i = 0; i < detMultiplicators.size(); i++) {
        		det *= detMultiplicators.get(i);
        	}
        }
        //test print
        System.out.println("det(A): \n");
		testDet.showMatrix(matrix, 1);
		System.out.println("\n");
        return det;
    }
    
    public static double calcDetRec(double[][] matrix)
    {
		double det = 0;
		
		double res = 0;
		for (int i = 0; i < matrix.length; i++) {
			//stop statement
			if (matrix.length - 1 == 0) {
				return matrix[0][0];
			}
			double[][] subMatrix = createSubmatrix(matrix, i, 0);
			
			//calculate result of each submatrix
			res = ((Math.pow(-1, (0 + i))) * matrix[i][0]) * calcDetRec(subMatrix);
			nrOfMultRec++;
			det = det + res;
		}

		return det;
	}

	private static double[][] createSubmatrix(double[][] matrix, int x, int y) 
	{
		int newSize = matrix.length - 1;
		double[][] subMatrix = new double[newSize][newSize];

		int newX = 0;
		int newY = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {

				if (i == y || j == x) {
					continue;
				} 
				else {
					subMatrix[newX][newY] = matrix[j][i];

					if (newX >= subMatrix.length - 1) {
						newX = 0;
						newY++;
					} else {
						newX++;
					}
				}
			}
		}
		return subMatrix;
	}
}