
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Percurso<V> {

    public ArrayList<Vertice<V>> cidades = new ArrayList<>();
    public double total;
    public ArrayList<Double> trechos = new ArrayList<>();

    private Vertice<V> buscarVertice(List<Vertice<V>> vertices, V dado) {

        if (dado == null)
            return null;

        for (Vertice<V> v : vertices) {
            if (v.getDado().equals(dado)) {
                return v;
            }
        }

        return null;
    }

    public Percurso<V> menorCaminho(
        List<Vertice<V>> vertices,
        V origem,
        V destino
    ) throws Exception {

        if (vertices == null || origem == null || destino == null)
            throw new Exception("Dados ausentes!");

        int n = vertices.size();

        Vertice<V> verticeOrigem = buscarVertice(vertices, origem);
        Vertice<V> verticeDestino = buscarVertice(vertices, destino);

        int o = vertices.indexOf(verticeOrigem);
        int d = vertices.indexOf(verticeDestino);

        if (o < 0 || d < 0)
            throw new Exception("Cidade inexistente!");

        double[] dist = new double[n];
        int[] ant = new int[n];
        double[] pesoAnt = new double[n];
        boolean[] visto = new boolean[n];

        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(ant, -1);
        Arrays.fill(pesoAnt, 0);

        dist[o] = 0;

        // Algoritmo de Dijkstra
        for (int k = 0; k < n; k++) {

            int u = -1;

            // Encontrar o vértice não visitado de menor distância
            for (int i = 0; i < n; i++) {

                if (!visto[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            if (u == -1 || dist[u] == Double.POSITIVE_INFINITY)
                break;

            visto[u] = true;

            if (u == d)
                break;

            ListaSimplesDesordenada<Aresta<V>> saidas =
                vertices.get(u).getArestasSaida();

            // Percorrer as arestas de saída
            for (int j = 0; j < saidas.getQuantidade(); j++) {

                Aresta<V> a = saidas.getPosicao(j);

                int v = vertices.indexOf(a.getFim());

                if (v < 0)
                    throw new Exception("Aresta aponta para vértice inexistente!");

                if (a.getPeso() < 0 || Double.isNaN(a.getPeso()) ||
                    Double.isInfinite(a.getPeso())) {
                    throw new Exception("Peso de aresta inválido!");
                }

                if (!visto[v] && dist[u] + a.getPeso() < dist[v]) {

                    dist[v] = dist[u] + a.getPeso();

                    ant[v] = u;
                    pesoAnt[v] = a.getPeso();
                }
            }
        }

        if (dist[d] == Double.POSITIVE_INFINITY)
            throw new Exception("Sem caminho!");

        // Montar o percurso encontrado
        Percurso<V> p = new Percurso<>();

        p.total = dist[d];

        for (int i = d; i != -1; i = ant[i]) {

            p.cidades.add(0, vertices.get(i));

            if (ant[i] != -1) {
                p.trechos.add(0, pesoAnt[i]);
            }
        }

        return p;
    }
}
