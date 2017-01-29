package exercises;

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
        
        //TODO Zeilenverschiebung versuchen, falls erste Zeile nicht mit 1 anfängt
        
        
        int originalColumn = 0;
        int originalRow = 0;
        int startRow = 1; //line to start the iteration
        for(int row = startRow; row < n; row++) 
        {
        		//check if multiplications are necessary
        		double diagonalValue = matrix[row][row];
        		if(matrix[row][originalColumn] - diagonalValue == 0) {
        			matrix[row][originalColumn] = 0;
					for(int col = originalColumn + 1; col < n; col++) {
						matrix[row][col] = matrix[row][col] - matrix[originalRow][col];
					}
        			testDet.showMatrix(matrix, 1);
        			System.out.println("\n if \n");
        		}
        		else {
        			matrix[row][originalColumn] = matrix[startRow - 1][originalRow] * matrix[row][originalColumn] - matrix[row][originalColumn] * matrix[row][originalRow];
        			nrOfMult++;
        			nrOfMult++;
        			
        			for(int x = originalColumn + 1; x < n; x++) {
						matrix[row][x] = matrix[row][originalRow] * matrix[row][x] - matrix[row][x] * matrix[originalRow][x];
					}
        			testDet.showMatrix(matrix, 1);
        			System.out.println("\n");
        		}
        		if(row == n - 1) {
        			originalColumn++;
        			startRow++;
        			originalRow++;
        			row = originalRow;
        		}
        		//Abbruchbedingung
        		if(matrix[n - 1][n - 2] == 0) {
        			double diagonalenProdukt = 1;
        			int diagonalenCounter = 0;
        			while(diagonalenCounter < n) {
        				diagonalenProdukt *= matrix[diagonalenCounter][diagonalenCounter];
        				diagonalenCounter++;
        				nrOfMult++;
        			}
        			return diagonalenProdukt;
        		}
        }        
        return Double.NaN;
    }

    public static double calcDetRec(double[][] matrix)
    {
		double det = 0;
		
		double res = 0;
		for (int i = 0; i < matrix.length; i++) {

			if (matrix.length - 1 == 0) {
				return matrix[0][0];
			}
			double[][] subMatrix = createSubmatrix(matrix, i, 0);
			
			//calculate result of each submatrix
			res = ((Math.pow(-1, (0 + i))) * matrix[i][0]) * calcDetRec(subMatrix);
			
			det = det + res;
		}

		return det;
	}

	// Gibt Submatrix zurück
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