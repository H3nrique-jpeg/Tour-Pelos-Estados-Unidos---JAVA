import java.util.ArrayList;

public class MatrizEsparsa<V> {
    private ArrayList<Vertice<V>> vertices;
    private ListaSimplesDesordenada<ElementoMatriz<V>> elementos;

    public MatrizEsparsa(ArrayList<Vertice<V>> verticesDoGrafo)  {
        this.vertices = new ArrayList<Vertice<V>>(verticesDoGrafo);
        this.elementos = new ListaSimplesDesordenada<>();
    }

    public void adicionarVertice(Vertice<V> vertice) throws Exception {
        if(vertice == null) throw new Exception("Vertice Ausente!");

        if(this.vertices.contains(vertice)) throw new Exception("Vertice já cadastrado!");

        this.vertices.add(vertice);
    }

    private int localizarIndice(V dado) throws Exception {      // Fazer a procura das cidades pelo índice
        if (dado == null) throw new Exception("Dado ausente!");

        for (int i = 0; i < this.vertices.size(); i++) {
            Vertice<V> vertice = this.vertices.get(i);

            if (vertice.getDado() != null && vertice.getDado().equals(dado)) {
                return i;
            }
        }

        throw new Exception("Vértice inexistente!");
    }
    
    public double getDistancia(V origem, V destino) throws Exception {
        int l = localizarIndice(origem), c = localizarIndice(destino);
        for (int i = 0; i < elementos.getQuantidade(); i++) {
            ElementoMatriz<V> e = elementos.getPosicao(i);
            if (e.getLinha() == l && e.getColuna() == c) return e.getDistancia();
        }
        return Double.POSITIVE_INFINITY; // sem estrada direta
    }

    public void removerVertice(Vertice<V> vertice) throws Exception {
        if (vertice == null) throw new Exception("Vertice Ausente!");

        int indiceRemover = this.localizarIndice(vertice.getDado());
        

        for(int i = 0; i < this.elementos.getQuantidade(); ) {
            ElementoMatriz<V> elemento = this.elementos.getPosicao(i);

            if(elemento.getLinha() == indiceRemover || elemento.getColuna() == indiceRemover) {
                this.elementos.removaItemIndicado(i);
            } else {
                if(elemento.getLinha() > indiceRemover) {
                    elemento.setLinha(elemento.getLinha() - 1);
                }
                if(elemento.getColuna() > indiceRemover) {
                    elemento.setColuna(elemento.getColuna() - 1);
                }

                i++;
            }
            
        }

        this.vertices.remove(indiceRemover);

    }

    private boolean existeElemento(int linha, int coluna) throws Exception {     // Compara os caminhos, ex: Los Angeles -> Las Vegas = 450; Las Vegas -> Los Angeles = 470
        for (int i = 0; i < this.elementos.getQuantidade(); i++) {
            ElementoMatriz<V> elemento = this.elementos.getPosicao(i);

            if (elemento.getLinha() == linha && elemento.getColuna() == coluna) {
                return true;
            }
        }
        return false;
    }
    private void tsp(int atual, boolean[] usada, int visitadas, double parcial, ArrayList<Integer> caminho, double[][] d, ArrayList<Percurso<V>> top3) {
        int n = d.length;
        if (top3.size() == 3 && parcial >= top3.get(2).total) return; // poda
        if (visitadas == n) {
            double total = parcial + d[atual][0]; // volta à cidade inicial
            // montar Percurso com 'caminho' + cidade 0, inserir ordenado em top3, manter só 3
            return;
        }
        for (int i = 1; i < n; i++) {
            if (usada[i]) continue;
            usada[i] = true; caminho.add(i);
            tsp(i, usada, visitadas + 1, parcial + d[atual][i], caminho, d, top3);
            caminho.remove(caminho.size() - 1); usada[i] = false;
        }
    }

    public void adicionarElemento(V origem, V destino, double distancia) throws Exception {
        if (origem == null || destino == null) throw new Exception("Origem e destino não podem ser nulos!");
        if (distancia < 0 || Double.isNaN(distancia)) throw new Exception("Distância inválida!");

        int linha = localizarIndice(origem);
        int coluna = localizarIndice(destino);

        if (existeElemento(linha, coluna)) {
            throw new Exception("Conexão já cadastrada!");
        }

        ElementoMatriz<V> novo = new ElementoMatriz<>(linha, coluna, distancia);
        this.elementos.guardeUmItemNoFinal(novo);
    }
}