import java.util.ArrayList;

public class Grafo<V> {
    private ArrayList<Vertice<V>> vertices;
    private ListaSimplesDesordenada<Aresta<V>> arestas;
    private MatrizEsparsa<V> matriz;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<V>>();
        this.arestas = new ListaSimplesDesordenada<Aresta<V>>();
    }

    public void adicionarVertice(V dado) throws Exception {
        if (dado == null) throw new Exception("Valor ausente ao vértice");
        if (this.getVertice(dado) != null) throw new Exception("Vértice já existe");
        this.vertices.add(new Vertice<V>(dado));
    }

    public void adicionarAresta(double peso, V dadoInicio, V dadoFim) throws Exception {
        if (peso < 0 || Double.isNaN(peso))
            throw new Exception("Peso inválido!");

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
        for (Vertice<V> v : this.vertices)
            if (v.getDado().equals(dado))
                return v;
        return null;
    }

    public void montarMatriz() throws Exception {
        this.matriz = new MatrizEsparsa<V>(this.vertices);

        for (int i = 0; i < this.arestas.getQuantidade(); i++) {
            Aresta<V> a = this.arestas.getPosicao(i);

            this.matriz.adicionarElemento(
                a.getInicio().getDado(),
                a.getFim().getDado(),
                a.getPeso()
            );
        }
    }

    public MatrizEsparsa<V> getMatriz() {
        return this.matriz;
    }

    public void exibir() {
        String linha = "=".repeat(44);

        System.out.println(linha);
        System.out.println("   GRAFO - TOUR PELOS ESTADOS UNIDOS");
        System.out.println(linha);

        try {
            for (Vertice<V> v : this.vertices) {
                System.out.println();
                System.out.println("[" + v.getDado() + "]");

                ListaSimplesDesordenada<Aresta<V>> saidas = v.getArestasSaida();
                int qtd = saidas.getQuantidade();

                if (qtd == 0) {
                    System.out.println("   (sem conexoes)");
                    continue;
                }

                for (int i = 0; i < qtd; i++) {
                    Aresta<V> a = saidas.getPosicao(i);

                    String prefixo = (i == qtd - 1) ? "   `--> " : "   |--> ";
                    String peso = String.format(java.util.Locale.US, "%.1f", a.getPeso());

                    System.out.printf("%s%-18s %8s%n", prefixo, a.getFim().getDado(), peso);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir o grafo: " + e.getMessage());
        }

        System.out.println();
        System.out.println(linha);
    }

    /* To string de exemplo para teste do grafo */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        try {
            for (Vertice<V> v : this.vertices) {
                sb.append(v.getDado()).append(" -> ");

                ListaSimplesDesordenada<Aresta<V>> saidas = v.getArestasSaida();

                if (saidas.getQuantidade() == 0) {
                    sb.append("(sem arestas)");
                } else {
                    for (int i = 0; i < saidas.getQuantidade(); i++) {
                        Aresta<V> a = saidas.getPosicao(i);

                        sb.append(a.getFim().getDado())
                          .append(" (")
                          .append(a.getPeso())
                          .append(")");

                        if (i < saidas.getQuantidade() - 1) {
                            sb.append(", ");
                        }
                    }
                }

                sb.append("\n");
            }
        } catch (Exception e) {
            sb.append("Erro ao montar o grafo: ").append(e.getMessage());
        }

        return sb.toString();
    }

    public void removeVertices() {

    }

    public void adicionaArestas(ListaSimplesDesordenada<Aresta<V>> novasArestas) throws Exception {
        if (novasArestas == null) throw new Exception("Aresta Ausente!");

    }

    public void adicionaVertices() {

    }
}