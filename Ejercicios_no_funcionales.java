import java.util.ArrayList;
import java.util.List;

public class Ejercicios_no_funcionales {
    /*
     * Ejercicio 1
     *  void construirPiramide(List<Integer> baseParcial, int nivelActual, int k) {
            if (baseParcial.size() == k) {
                if (esValida(baseParcial)) {
                    imprimirPiramide(baseParcial);
                    return;
                } else {
                    return;
                }
            }
            for (int num = 1; num <= MAX_NUM; num++) {
                baseParcial.add(num);
                
                construirPiramide(baseParcial, nivelActual + 1, k);
                    
                baseParcial.remove(baseParcial.size() - 1);
            }
        }


    *Ejercicio 2

    //Greedy

    public List<List<Persona>> agruparPorFuerza(List<Persona> personas, int k) {
        personas.sort((a, b) -> b.fuerza - a.fuerza); // de mayor a menor
        List<List<Persona>> grupos = new ArrayList<>();

        int[] suma = new int[k];

        for (int i = 0; i < k; i++){
            grupos.add(new ArrayList<>()); //Agrego los K primeros fuertes

        for (Persona p : personas) {

            int mejorGrupo = 0;
            for (int j = 1; j < k; j++) {
                if (suma[j] < suma[mejorGrupo]){
                    mejorGrupo = j; //Veo cómo reacomodar a los restantes
                }
            }

            grupos.get(mejorGrupo).add(p);
            suma[mejorGrupo] += p.fuerza;
        }

        return grupos;
    }


    //Backtracking

    private int mejorDiferencia = Integer.MAX_VALUE;
    private List<List<Persona>> mejorDistribucion;

    public List<List<Persona>> agruparPorFuerza(List<Persona> personas, int k) {
        List<List<Persona>> grupos = new ArrayList<>();
        int[] suma = new int[k];

        for (int i = 0; i < k; i++) {
            grupos.add(new ArrayList<>());
        }

        backtrack(personas, 0, grupos, suma, k);
        return mejorDistribucion;
    }

    private void backtrack(List<Persona> personas, int i, List<List<Persona>> grupos, int[] suma, int k) {
        if (i == personas.size()) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int val : suma) {
                if (val > max) max = val;
                if (val < min) min = val;
            }

            int diferencia = max - min;
            if (diferencia < mejorDiferencia) {
                mejorDiferencia = diferencia;
                mejorDistribucion = copiarGrupos(grupos);
            }
            return;
        }

        Persona actual = personas.get(i);
        for (int j = 0; j < k; j++) {
            grupos.get(j).add(actual);
            suma[j] += actual.fuerza;

            backtrack(personas, i + 1, grupos, suma, k);

            grupos.get(j).remove(grupos.get(j).size() - 1);
            suma[j] -= actual.fuerza;
        }
    }

    private List<List<Persona>> copiarGrupos(List<List<Persona>> grupos) {
        List<List<Persona>> copia = new ArrayList<>();
        for (List<Persona> grupo : grupos) {
            copia.add(new ArrayList<>(grupo));
        }
        return copia;
    }


    *Ejercicio 3

    // Método principal que intenta colorear el grafo
    public boolean colorearGrafo(int[][] grafo, int[] colores, int nodo, int maxColores) {
        if (nodo == grafo.length) return true;

        for (int c = 1; c <= maxColores; c++) {
            if (esValido(grafo, colores, nodo, c)) {
                colores[nodo] = c;

                if (colorearGrafo(grafo, colores, nodo + 1, maxColores)) return true;

                colores[nodo] = 0; // backtrack
            }
        }

        return false;
    }

    // Verifica si es válido asignar color al nodo actual
    private boolean esValido(int[][] grafo, int[] colores, int nodo, int color) {
        for (int vecino = 0; vecino < grafo.length; vecino++) {
            if (grafo[nodo][vecino] == 1 && colores[vecino] == color) {
                return false;
            }
        }
        return true;
    }
        
    // Método de utilidad para probar el algoritmo
    public void resolver(int[][] grafo, int maxColores) {
        int[] colores = new int[grafo.length];

        if (colorearGrafo(grafo, colores, 0, maxColores)) {
            System.out.println("Coloración posible:");
            for (int i = 0; i < colores.length; i++) {
                System.out.println("Nodo " + i + " → Color " + colores[i]);
            }
        } else {
            System.out.println("No es posible colorear el grafo con " + maxColores + " colores.");
        }
    }


    *Ejercicio 4 

    //Greedy
    
    public int llaveMasEficaz(int[] llaves, int[] cerraduras) {
        int mejor = -1, max = 0;

        for (int i = 0; i < llaves.length; i++) {
            int cuenta = 0;
            
            for (int j = 0; j < cerraduras.length; j++) {
                if (puedeAbrir(llaves[i], cerraduras[j])) cuenta++;
            }
            
            if (cuenta > max) {
                max = cuenta;
                mejor = i;
            }
        }
        return mejor;
    }
       
    //Backtracking
    
    void buscarMaximo(int[] llaves, int[] cerraduras, boolean[] usadas, int i, int abiertas, int[] max) {
        if (i == llaves.length) {
            max[0] = Math.max(max[0], abiertas);
            return;
        } else {
            for (int j = 0; j < cerraduras.length; j++) {
                if (!usadas[j] && puedeAbrir(llaves[i], cerraduras[j])) {
                    usadas[j] = true;
                    buscarMaximo(llaves, cerraduras, usadas, i + 1, abiertas + 1, max);
                    usadas[j] = false;
                }
            }
            buscarMaximo(llaves, cerraduras, usadas, i + 1, abiertas, max);
        }
    }


    *Ejercicio 5

    int totalNodos = 0;
    int totalElementos = 0;

    public void recorrerYMostrar(Nodo nodo) {
        if (nodo != null){

            // Procesar nodo actual
            for (int elem : nodo.listaFactoreo) {
                totalElementos++;
            }
            totalNodos++;

            // Recorrer subárbol izquierdo y derecho
            recorrerYMostrar(nodo.izq);
            recorrerYMostrar(nodo.der);
        }
    }

    public void mostrarPromedio(Nodo raiz) {
        totalNodos = 0;
        totalElementos = 0;
        recorrerYMostrar(raiz);

        double promedio = totalElementos / totalNodos;
        System.out.println("Promedio de elementos por nodo:" + promedio);
    }

    */
}
