public class ElementoMatriz<V> {
    private int linha;
    private int coluna;

    private Vertice<V> cidadeOrigem;
    private Vertice<V> cidadeDestino;

    private double distancia;

    public ElementoMatriz(int linha, int coluna, Vertice<V> origem, Vertice<V> destino, double distancia) {
        this.linha = linha;
        this.coluna = coluna;
        this.cidadeOrigem = origem;
        this.cidadeDestino = destino;
        this.distancia = distancia;
    }

    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }
    public Vertice<V> getCidadeOrigem() { return cidadeOrigem; }
    public Vertice<V> getCidadeDestino() { return cidadeDestino; }
    public double getDistancia() { return distancia; }
}

/* esta classe dterminará as info do nó:


--->    ele detrminará a linha e coluna para pegar por INDICE as cidades relacionadas.
--->    depois ilustrativo para o toString, a cidade de origem e destino.
 
*/