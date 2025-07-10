import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatronCelular {

    public boolean patron(int u, int v, boolean[][] visitados, List<Integer> camino) {
        if (camino.size() >= 4 && u == camino.get(0)) {
            System.out.println("Patrón válido encontrado: " + camino);
            return true;
        }

        Iterable<Integer> adyacentes = obtenerAdyacentes(v);
        Iterator<Integer> it = adyacentes.iterator();

        while (it.hasNext()) {
            int vecino = it.next();
            if (!visitados[v][vecino]) {
                visitados[v][vecino] = true;
                camino.add(vecino);

                if (patron(v, vecino, visitados, camino)) return true;

                visitados[v][vecino] = false;
                camino.remove(camino.size() - 1);
            }
        }

        return false;
    }

    public void buscarPatron() {
        boolean[][] visitados = new boolean[10][10];
        List<Integer> camino = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            camino.add(i);
            if (patron(i, i, visitados, camino)) return;
            camino.remove(camino.size() - 1);
        }

        System.out.println("Ningún patrón encontrado.");
    }

    // Devuelve Iterable en lugar de array
    private Iterable<Integer> obtenerAdyacentes(int v) {
        List<Integer> vecinos = new ArrayList<>();
        switch (v) {
            case 1: vecinos.addAll(Arrays.asList(2, 4)); break;
            case 2: vecinos.addAll(Arrays.asList(1, 3, 5)); break;
            case 3: vecinos.addAll(Arrays.asList(2, 6)); break;
            case 4: vecinos.addAll(Arrays.asList(1, 5, 7)); break;
            case 5: vecinos.addAll(Arrays.asList(2, 4, 6, 8)); break;
            case 6: vecinos.addAll(Arrays.asList(3, 5, 9)); break;
            case 7: vecinos.addAll(Arrays.asList(4, 8)); break;
            case 8: vecinos.addAll(Arrays.asList(5, 7, 9)); break;
            case 9: vecinos.addAll(Arrays.asList(6, 8)); break;
        }
        return vecinos;
    }

    public static void main(String[] args) {
        new PatronCelular().buscarPatron();
    }
}
