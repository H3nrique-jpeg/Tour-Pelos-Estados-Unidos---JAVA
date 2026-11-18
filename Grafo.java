
import java.util.ArrayList;

public class Grafo<V> {

    private ArrayList<Vertice<V>> vertices;
    private ListaSimplesDesordenada<Aresta<V>> arestas;
    private MatrizEsparsa<V> matriz;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<V>>();
        this.arestas = new ListaSimplesDesordenada<Aresta<V>>();
            this.matriz = new MatrizEsparsa<V>(this.vertices);
    }

    public void adicionarVertice(V dado) throws Exception {

        if (dado == null)
            throw new Exception("Valor ausente ao vértice");

        if (this.getVertice(dado) != null)
            throw new Exception("Vértice já existe");

        Vertice<V> novo = new Vertice<V>(dado);

        this.vertices.add(novo);
        this.matriz.adicionarVertice(novo);
    }


    public void removerVertice(V vertice) throws Exception {

        if (vertice == null)
            throw new Exception("Vértice ausente!");

        Vertice<V> verticeRemover = this.getVertice(vertice);

        if (verticeRemover == null)
            throw new Exception("Vértice inexistente!");


        for (int i = 0; i < this.arestas.getQuantidade();) {

            Aresta<V> aresta = this.arestas.getPosicao(i);

          
            if (aresta.getInicio() == verticeRemover ||
                aresta.getFim() == verticeRemover) {

        
                aresta.getInicio().removerArestaSaida(aresta);

                
                aresta.getFim().removerArestaEntrada(aresta);

               
                this.arestas.removaItemIndicado(i);

            } else {
                i++;
            }
        }

        
        this.matriz.removerVertice(verticeRemover);
        this.vertices.remove(verticeRemover);
    }



    public void adicionarAresta(double peso, V dadoInicio, V dadoFim)
            throws Exception {

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

        if (dado == null)
            return null;

        for (Vertice<V> v : this.vertices) {

            if (v.getDado().equals(dado))
                return v;
        }

        return null;
    }



    public void montarMatriz() throws Exception {
        this.matriz = new MatrizEsparsa<V>(this.vertices);

        for (int i = 0; i < this.arestas.getQuantidade(); i++) {
            Aresta<V> aresta = this.arestas.getPosicao(i);

            this.matriz.adicionarElemento(
                aresta.getInicio().getDado(),
                aresta.getFim().getDado(),
                aresta.getPeso()
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

                ListaSimplesDesordenada<Aresta<V>> saidas =
                    v.getArestasSaida();

                int qtd = saidas.getQuantidade();

                if (qtd == 0) {
                    System.out.println("   (sem conexoes)");
                    continue;
                }

                for (int i = 0; i < qtd; i++) {

                    Aresta<V> a = saidas.getPosicao(i);

                    String prefixo = (i == qtd - 1)
                        ? "   `--> "
                        : "   |--> ";

                    String peso = String.format(
                        java.util.Locale.US,
                        "%.1f",
                        a.getPeso()
                    );

                    System.out.printf(
                        "%s%-18s %8s%n",
                        prefixo,
                        a.getFim().getDado(),
                        peso
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Erro ao exibir o grafo: " + e.getMessage()
            );
        }

        System.out.println();
        System.out.println(linha);
    }

 
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        try {

            for (Vertice<V> v : this.vertices) {

                sb.append(v.getDado()).append(" -> ");

                ListaSimplesDesordenada<Aresta<V>> saidas =
                    v.getArestasSaida();

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

            sb.append("Erro ao montar o grafo: ")
              .append(e.getMessage());
        }

        return sb.toString();
    }


    public void removeVertices(
        ListaSimplesDesordenada<Vertice<V>> verticesRemover) throws Exception {

        if (verticesRemover == null)
            throw new Exception("Lista de vértices ausente!");

        for (int i = 0; i < verticesRemover.getQuantidade(); i++) {

            Vertice<V> vertice = verticesRemover.getPosicao(i);

            if (vertice == null)
                throw new Exception("Vértice ausente!");

            this.removerVertice(vertice.getDado());
        }
    }

    public void adicionaArestas(
        ListaSimplesDesordenada<Aresta<V>> novasArestas) throws Exception {

        if (novasArestas == null)
            throw new Exception("Lista de arestas ausente!");

        for (int i = 0; i < novasArestas.getQuantidade(); i++) {

            Aresta<V> aresta = novasArestas.getPosicao(i);

            if (aresta == null)
                throw new Exception("Aresta ausente!");

            this.adicionarAresta(
                aresta.getPeso(),
                aresta.getInicio().getDado(),
                aresta.getFim().getDado()
            );
        }
    }

}
