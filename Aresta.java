public class Aresta<V> {
    private double peso;
    private Vertice<V> inicio;
    private Vertice<V> fim;

    public Aresta(double peso, Vertice<V> inicio, Vertice<V> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public double getPeso() { return peso; }
    public Vertice<V> getInicio() { return inicio; }
    public Vertice<V> getFim() { return fim; }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setInicio(Vertice<V> inicio) {
        this.inicio = inicio;
    }

    public void setFim(Vertice<V> fim) {
        this.fim = fim;
    }
}