package exercises;

public class det {
    public static int nrOfMult;
    
    //Berechnung mit 1. Normalform
    public static double calcDet(double[][] A){
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
        int j = 0;
        int originalLine = 0;
        int startI = 1;
        for(int i = startI; i < n; i++) {
        		if(matrix[i][j] - matrix[i][i] == 0) {
        			matrix[i][j] = 0;
					for(int x = j + 1; x < n; x++) {
						matrix[i][x] = matrix[i][x] - matrix[originalLine][x];
					}
        			testDet.showMatrix(matrix, 1);
        			System.out.println("\n if \n");
        		}
        		else {
        			matrix[i][j] = matrix[i][0] * matrix[i][j] - matrix[i][j] * matrix[i][0];
        			for(int x = j + 1; x < n; x++) {
						matrix[i][x] = matrix[i][x] - matrix[originalLine][x];
					}
        			testDet.showMatrix(matrix, 1);
        			System.out.println("\n");
        		}
        		if(i == n - 1) {
        			j++;
        			startI++;
        			originalLine++;
        			i = originalLine;
        		}
        }        
        return Double.NaN;
    }

    //Rekursive Berechnung mit Def. L.4.1.1 Skript
    public static double calcDetRec(double[][] A){
        return Double.NaN; // Durch Ihren Code ersetzen!
    }
}