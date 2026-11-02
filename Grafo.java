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

        try {
            for (int i = 0; i < this.vertices.getQuantidade(); i++) {
                Vertice<V> v = this.vertices.getPosicao(i);
                sb.append(v.getDado()).append(" -> ");

                if (v.getArestasSaida().isVazia()) {
                    sb.append("(sem arestas)");
                } 
                else {
                    int qteArestas = v.getArestasSaida().getQuantidade();
                    for (int j = 0; j < qteArestas; j++) {
                        Aresta<V> a = v.getArestasSaida().getPosicao(j);
                        sb.append(a.getFim().getDado())
                        .append(" (").append(a.getPeso()).append(")");

                        if (j < qteArestas - 1)
                            sb.append(", ");
                    }
                }
                sb.append("\n");
            }
        } catch (Exception e) {
            sb.append("Erro ao formatar o grafo: ").append(e.getMessage());
        }

        return sb.toString();
    }
}