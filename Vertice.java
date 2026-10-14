import java.util.ArrayList;

public class Vertice<V> {
    private V dado;
    private ArrayList<Aresta<V>> arestasEntrada;
    private ArrayList<Aresta<V>> arestasSaida;

    public Vertice(V valor) {
        this.dado = valor;
        this.arestasEntrada = new ArrayList<Aresta<V>>();
        this.arestasSaida = new ArrayList<Aresta<V>>();
    }

    public V getDado() {
        return dado;
    }

    public void setDado(V dado) {
        this.dado = dado;
    }

    public ArrayList<Aresta<V>> getArestasEntrada() { return arestasEntrada; }
    public ArrayList<Aresta<V>> getArestasSaida() { return arestasSaida; }

    public void adicionarArestaEntrada(Aresta<V> aresta) throws Exception {
        if(aresta == null) throw new Exception("Aresta Ausente");

        this.arestasEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta<V> aresta) throws Exception {
        if(aresta == null) throw new Exception("Aresta Ausente!");

        this.arestasSaida.add(aresta);
        
    }


    
}
