package Entidades;

import Itens.Arma;
import Itens.ItemHeroi;
import Itens.PocaoHP;
import Itens.TiposHeroi;

import java.util.ArrayList;


public class Vendedor {

    private ArrayList<ItemHeroi> inventario; // Declaração do atributo inventario como um ArrayList de ItemHeroi

    /**
     * Construtor da classe Vendedor
     */
    public Vendedor() {
        this.inventario = new ArrayList<>();
    }

    /**
     * Método para adicionar item ao atributo inventario
     * @param item
     */
    public void adicionarItem(ItemHeroi item) {
        // Adiciona um item ao inventario
        inventario.add(item);
    }

    /**
     * Método de imprimir inventário (ArrayList que recebe um item da classe ItemHeroi)
     */
    public void imprimirInventario() {
        System.out.println("Inventário do Vendedor:");
        int contador = 1;
        for (ItemHeroi item : inventario) {
            System.out.println("Item " + contador);

                // Percorre o inventario e imprime os detalhes de cada item
                item.mostrarDetalhes();
                System.out.println("--------------------");
            contador++;
            }

    }

    public ArrayList<ItemHeroi> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<ItemHeroi> inventario) {
        this.inventario = inventario;
    }

    /**
     * Método vender que recebe como parametros um heroi da classe Heroi e um item da classe ItemHeroi
     * @param heroi
     * @param index
     */
    public void vender(Heroi heroi, int index) { //this.inventario.getIndex()
        // Verifica se o heroi possui ouro suficiente para comprar o item
        if (index >= 0 && index < inventario.size()) { // verifica se o indice está entre 0 (item 1) e o maior valor de índice do array inventário
            ItemHeroi item = inventario.get(index);    // escolhe o item que está no arraylist inventário que tem como parametro o indice introduzido(aparece na lista de inventario)

            boolean permitido = false;
            for (TiposHeroi heroiPermitido : item.getTiposHeroi()) {
                //System.out.println("tipo permitido" + heroiPermitido); // debug pra ver o tipo de  heroi permitido
                //System.out.println("Debug" + heroi.getClass().getSimpleName() ); // debug que retorna o tipo de heroi
                if (heroiPermitido.toString().equals(heroi.getClass().getSimpleName().toUpperCase())) {
                    permitido = true;
                }
            }

            if (permitido) {

                if (heroi.getOuro() >= item.getPreco()) {  // validação se o herói possui ouro
                    // Verifica se o item é uma Arma e atualiza a arma do heroi
                    if (item instanceof Arma) { // verifica se item do tipo é uma instancia de Arma
                        Arma arma = (Arma) item; // casting, trata o item como uma instancia de arma, ja qe foi verificado acima
                        heroi.setArma(arma);    // arma da classe Heroi armazena um item (que ja foi verificado e convertido pro tipo arma)
                    }
                    // Se não é uma arma é uma poção, então, verifica se o item é uma PocaoHP e adiciona a poção ao heroi
                    else if (item instanceof PocaoHP) { // verifica se item do tipo é uma instancia de PocaoHP
                        PocaoHP pocao = (PocaoHP) item;
                        heroi.adicionarPocao(pocao);    // pocao da classe Heroi armazena um item (que ja foi verificado e convertido pro tipo pocao)
                    }



                    heroi.decrementarOuro(item.getPreco()); // Decrementa o ouro do heroi pelo preço do item

                    inventario.remove(item);                // Remove o item do inventario do vendedor
                    System.out.println("Compra realizada com sucesso!");
                    ItemHeroi itemSelecionado = getInventario().get(index - 1);
                    System.out.println("Você comprou o item: " + itemSelecionado.getNome());
                    System.out.println("Seu ouro restante: " + heroi.getOuro());



                } else {
                    System.out.println("O herói não possui ouro suficiente para comprar este item.");
                }
            } else {
                System.out.println("Item do vendedor não encontrado");

            }

            heroi.mostrarDetalhes();
        }
    }
}



