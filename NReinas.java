public class NReinas {

    public static void main(String[] args) {
        int n = 8; // Tamaño del tablero
        int[] reinas = new int[n]; // Arreglo para almacenar la posición de las reinas
        char[][] tablero = new char[n][n]; // Matriz para representar el tablero
        
        // Inicializar el tablero con '.'
        inicializarTablero(tablero);
        
        if (nReinas(reinas, n, 0, tablero)) {
            imprimirTablero(tablero);
        } else {
            System.out.println("No se encontró solución.");
        }
    }

    public static boolean nReinas(int[] reinas, int n, int k, char[][] tablero) {
        boolean exito = false;
        if (k == n) { // Se colocaron todas las reinas
            exito = true;
            colocarReinasEnTablero(reinas, tablero); // Se coloca la solución en la matriz
        } else {
            for (int col = 0; col < n; col++) {
                reinas[k] = col; // Intentar colocar la reina en la columna 'col'
                if (comprobar(reinas, k)) {
                    if (nReinas(reinas, n, k + 1, tablero)) {
                        return true; // Si encuentra una solución, se detiene la búsqueda
                    }
                }
            }
        }
        return exito;
    }

    public static boolean comprobar(int[] reinas, int k) {
        for (int i = 0; i < k; i++) {
            if ((reinas[i] == reinas[k]) || 
                (Math.abs(k - i) == Math.abs(reinas[k] - reinas[i]))) {
                return false;
            }
        }
        return true;
    }

    public static void inicializarTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '.'; // Inicializar el tablero con puntos
            }
        }
    }

    public static void colocarReinasEnTablero(int[] reinas, char[][] tablero) {
        for (int i = 0; i < reinas.length; i++) {
            tablero[i][reinas[i]] = 'Q'; // Colocar una reina en la posición correcta
        }
    }

    public static void imprimirTablero(char[][] tablero) {
        for (char[] fila : tablero) {
            for (char casilla : fila) {
                System.out.print(" " + casilla + " ");
            }
            System.out.println();
        }
    }
}
