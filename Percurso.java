public class Percurso<V> {
    
    public ArrayList<V> cidades = new ArrayList<>();
    public double total;

    public Percurso<V> menorCaminho(V origem, V destino) throws Exception {
        int n = vertices.size();
        double[] dist = new double[n];
        int[] ant = new int[n];
        boolean[] visto = new boolean[n];
        java.util.Arrays.fill(dist, Double.POSITIVE_INFINITY);
        java.util.Arrays.fill(ant, -1);

        int o = vertices.indexOf(getVertice(origem));
        int d = vertices.indexOf(getVertice(destino));
        if (o < 0 || d < 0) throw new Exception("Cidade inexistente!");
        dist[o] = 0;

        for (int k = 0; k < n; k++) {
            int u = -1;
            for (int i = 0; i < n; i++)
                if (!visto[i] && (u == -1 || dist[i] < dist[u])) u = i;
            if (u == -1 || dist[u] == Double.POSITIVE_INFINITY) break;
            visto[u] = true;
            
            ListaSimplesDesordenada<Aresta<V>> saidas = vertices.get(u).getArestasSaida();
            for (int j = 0; j < saidas.getQuantidade(); j++) {
                Aresta<V> a = saidas.getPosicao(j);
                int v = vertices.indexOf(a.getFim());
                if (dist[u] + a.getPeso() < dist[v]) {
                    dist[v] = dist[u] + a.getPeso();
                    ant[v] = u;
                }
            }
        }

        if (dist[d] == Double.POSITIVE_INFINITY) throw new Exception("Sem caminho!");

        Percurso<V> p = new Percurso<>();
            p.total = dist[d];
                for (int i = d; i != -1; i = ant[i]) p.cidades.add(0, vertices.get(i).getDado());
            return p;
    }
}