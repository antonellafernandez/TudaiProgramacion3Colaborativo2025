import java.util.*;

public class CicloConArcosUnicos {

    static class Arco {
        int desde, hasta;

        Arco(int desde, int hasta) {
            this.desde = desde;
            this.hasta = hasta;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Arco)) return false;
            Arco otro = (Arco) obj;
            return desde == otro.desde && hasta == otro.hasta;
        }

        @Override
        public int hashCode() {
            return Objects.hash(desde, hasta);
        }

        @Override
        public String toString() {
            return "(" + desde + " -> " + hasta + ")";
        }
    }

    List<Arco> arcosUsados = new ArrayList<>();
    int contador = 0;

    public boolean buscar(List<List<Integer>> grafo, int origen) {
        boolean[] visitados = new boolean[grafo.size()];
        List<Arco> camino = new ArrayList<>();
        return dfs(grafo, origen, origen, camino, visitados);
    }

    private boolean dfs(List<List<Integer>> grafo, int nodo, int origen, List<Arco> camino, boolean[] visitados) {
        if (nodo == origen && !camino.isEmpty()) {
            if (!seRepite(camino)) {
                arcosUsados.addAll(camino);
                contador++;
                System.out.println("Ciclo " + contador + ": " + camino);
                return true;
            } else {
                return false;
            }
            
        } else {

            visitados[nodo] = true;

            for (int v : grafo.get(nodo)) {
                if (!visitados[v] || (visitados[v] && v == origen)) {
                    Arco a = new Arco(nodo, v);
                    camino.add(a);
                    if (dfs(grafo, v, origen, camino, visitados)){
                        return true;
                    }
                    camino.remove(camino.size() - 1);
                }
            }

            visitados[nodo] = false;    
        }
        return false;
    }

    private boolean seRepite(List<CicloConArcosUnicos.Arco> camino) {
        for (Arco a : camino) {
            if (arcosUsados.contains(a)) return true;
        }
        return false;
    }

    // Ejemplo
    public static void main(String[] args) {
        List<List<Integer>> grafo = new ArrayList<>();
        for (int i = 0; i < 5; i++) grafo.add(new ArrayList<>());

        grafo.get(0).add(1);
        grafo.get(1).add(2);
        grafo.get(2).add(0);
        grafo.get(2).add(3);
        grafo.get(3).add(4);
        grafo.get(4).add(2);

        CicloConArcosUnicos buscador = new CicloConArcosUnicos();

        for (int i = 0; i < grafo.size(); i++) {
            buscador.buscar(grafo, i);
        }
    }
}
