public class Grafo <V> {
    private V [] vertices;
    private ListaSimplesDesordenada<V> arestas;
    private int distancias;

    public Grafo( ListaSimplesDesordenada<V> arestas, int distancias) {
        this.arestas= arestas;
        this.distancias= distancias;

        this.vertices = (V[]) new Object[10];
    }

    public V[] getVertices() {
        return vertices;
    }

    public void setVertices(V[] vertices) {
        this.vertices = vertices;
    }

    public ListaSimplesDesordenada<V> getArestas() {
        return arestas;
    }

    public void setArestas(ListaSimplesDesordenada<V> arestas) {
        this.arestas = arestas;
    }

    public int getDistancias() {
        return distancias;
    }
    
    public void setDistancias(int distancias) {
        this.distancias = distancias;
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