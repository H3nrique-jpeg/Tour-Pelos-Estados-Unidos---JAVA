import java.util.ArrayList;

public class Grafo<V> {
    private ListaSimplesDesordenada<Vertice<V>> vertices;
    private ListaSimplesDesordenada<Aresta<V>> arestas;

    public Grafo() {
        this.vertices = new ListaSimplesDesordenada<Vertice<V>>();
        this.arestas = new ListaSimplesDesordenada<Aresta<V>>();
    }

    public void adicionarVertice(V dado) throws Exception {
        if (dado == null) throw new Exception("Valor ausente ao vértice");
        if (this.getVertice(dado) != null) throw new Exception("Vértice já existe");
        this.vertices.guardeUmItemNoFinal(new Vertice<V>(dado));
    }

    public void adicionarAresta(double peso, V dadoInicio, V dadoFim) throws Exception {
        Vertice<V> inicio = this.getVertice(dadoInicio);
        Vertice<V> fim = this.getVertice(dadoFim);

        if (inicio == null || fim == null)
            throw new Exception("Vértice inexistente");

        Aresta<V> aresta = new Aresta<V>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.guardeUmItemNoFinal(aresta);
    }

    public Vertice<V> getVertice(V dado) {
        return null;
    }

    /* To string de exemplo para teste do grafo */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Vertice<V> v : this.vertices) {
            sb.append(v.getDado()).append(" -> ");

            if (v.getArestasSaida().isEmpty()) {
                sb.append("(sem arestas)");
            } else {
                for (int i = 0; i < v.getArestasSaida().size(); i++) {
                    Aresta<V> a = v.getArestasSaida().get(i);
                    sb.append(a.getFim().getDado())
                    .append(" (").append(a.getPeso()).append(")");

                    if (i < v.getArestasSaida().size() - 1)
                        sb.append(", ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}