public class ElementoMatriz<V> {
    private int linha;
    private int coluna;

    private double distancia;

    public ElementoMatriz(int linha, int coluna, double distancia) {
        this.linha = linha;
        this.coluna = coluna;
        this.distancia = distancia;
    }

    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }
    public double getDistancia() { return distancia; }
}

/* esta classe dterminará as info do nó:


--->    ele detrminará a linha e coluna para pegar por INDICE as cidades relacionadas.
--->    depois ilustrativo para o toString, a cidade de origem e destino.
 
*/