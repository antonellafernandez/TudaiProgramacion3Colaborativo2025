public class RobotLimpieza {

    private static final int[] MovX = {-1, 0, 1, 0};
    private static final int[] MovY = {0, 1, 0, 1}; // ↑→↓←
    private int minimo;

    private static int n = 3;
    private static int m = 4;

    // Método público que se llama desde el exterior
    public int encontrarCaminoMinimo(int[][] mapa, int x0, int y0, int xf, int yf) {
        minimo = Integer.MAX_VALUE;
        boolean[][] visitado = new boolean[n][m];
        visitado[x0][y0] = true;

        // Probar todas las direcciones iniciales
        for (int d = 0; d < 4; d++) {
            caminoMinimoRobotBT(mapa, x0, y0, xf, yf, 0, d, visitado);
        }

        return (minimo == Integer.MAX_VALUE) ? -1 : minimo;
    }

    // Backtracking con penalización por cambio de dirección
    private void caminoMinimoRobotBT(int[][] mapa, int x, int y, int xf, int yf,
                                     int costo, int dirAnterior, boolean[][] visitado) {
        if (x == xf && y == yf) {
            minimo = Math.min(minimo, costo);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + MovX[d];
            int ny = y + MovY[d];

            if (esValido(mapa, nx, ny, visitado)) {
                int penalizacion;
                if (d == dirAnterior) {
                    penalizacion = 0;
                } else {
                    penalizacion = 1;
                }

                visitado[nx][ny] = true;
                caminoMinimoRobotBT(mapa, nx, ny, xf, yf, costo + 1 + penalizacion, d, visitado);
                visitado[nx][ny] = false; // backtrack
            }
        }
    }

    private boolean esValido(int[][] mapa, int nx, int ny, boolean[][] visitado) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m && mapa[nx][ny] == 0 && !visitado[nx][ny];
    }
    
    public static void main(String[] args) {
        int[][] mapa = {
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };

        RobotLimpieza robot = new RobotLimpieza();
        int costoMinimo = robot.encontrarCaminoMinimo(mapa, 0, 0, 2, 3);
        System.out.println("Costo mínimo: " + costoMinimo);
    }
    
}
