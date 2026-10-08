public class Grafo <V>{
    private V [] vertices;
    private ListaSimplesDesordenada<X> arestas;
    private int distancias;

    public Grafo(int quantidadeVertices, ListaSimplesDesordenada arestas, int distancias) {
        this.arestas= arestas;
        this.distancias= distancias;

        this.vertices = (V[]) new Object[quantidadeVertices];
    }
     

    public void removeArestas() {

    }

    public void removeVertices() {

    }

    public void adicionaArestas() {

    }

    public void adicionaVertices() {

    }
}