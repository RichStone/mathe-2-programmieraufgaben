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
        
        for(int i = 0; i < 1; i++) {
        	for(int j = i; j < n; j++) {
        		if(matrix[i][j] - 1 != 0) {
//        			double[][] tempArr = new double[n - i][n - i];
//        			for()
        		}
        		else {
        			//subtract
        			matrix[i + 1][j] = 0;
        			for(int x = i + 1; x < n; x++) {
        				matrix[x][j + 1] = matrix[x][j] - matrix[x][j + 1];
        			}
        		}
        	}
        }
        
        testDet.showMatrix(matrix, 1);
        return Double.NaN;
    }

    //Rekursive Berechnung mit Def. L.4.1.1 Skript
    public static double calcDetRec(double[][] A){
        return Double.NaN; // Durch Ihren Code ersetzen!
    }
}