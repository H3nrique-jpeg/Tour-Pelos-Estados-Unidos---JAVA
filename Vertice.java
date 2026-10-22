import java.util.ArrayList;

public class Vertice<V> {
    private V dado;
    private ListaSimplesDesordenada<Aresta<V>> arestasEntrada;
    private ListaSimplesDesordenada<Aresta<V>> arestasSaida;

    public Vertice(V valor) {
        this.dado = valor;
        this.arestasEntrada = new ListaSimplesDesordenada<Aresta<V>>();
        this.arestasSaida = new ListaSimplesDesordenada<Aresta<V>>();
    }

    public V getDado() {
        return dado;
    }

    public void setDado(V dado) {
        this.dado = dado;
    }

    public ListaSimplesDesordenada<Aresta<V>> getArestasEntrada() { return arestasEntrada; }
    public ListaSimplesDesordenada<Aresta<V>> getArestasSaida() { return arestasSaida; }

    public void adicionarArestaEntrada(Aresta<V> aresta) throws Exception {
        if(aresta == null) throw new Exception("Aresta Ausente");

        this.arestasEntrada.guardeUmItemNoInicio(aresta);
    }

    public void adicionarArestaSaida(Aresta<V> aresta) throws Exception {
        if(aresta == null) throw new Exception("Aresta Ausente!");

        this.arestasSaida.guardeUmItemNoFinal(aresta);
        
    }


    
}
