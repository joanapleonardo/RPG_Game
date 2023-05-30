package Entidades;

import Itens.Arma;
import Itens.ItemHeroi;
import Itens.PocaoHP;
import Itens.TiposHeroi;

import java.util.ArrayList;
import java.util.Scanner;


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
        System.out.println("Inventário do Vendedor:\n");
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
    /**
     * Método vender que recebe como parametros um heroi da classe Heroi e um item da classe ItemHeroi
     * @param heroi
     */
    public void vender(Heroi heroi) {
        boolean continuarCompra = true;
        ArrayList<ItemHeroi> itensComprados = new ArrayList<>();

        while (continuarCompra) {
            System.out.println("\nEscreve o número do item que queres comprar ou 0 para seguir em frente:");
            Scanner scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            if (index == 0) {
                // Opção para seguir em frente sem comprar mais itens
                System.out.println("\nEscolheste seguir em frente sem comprar mais itens.");
                System.out.println("--------------------\n");
                continuarCompra = false;
            } else if (index >= 1 && index <= inventario.size()) {
                ItemHeroi item = inventario.get(index - 1);

                if (!itensComprados.contains(item)) {
                    boolean permitido = false;

                    for (TiposHeroi heroiPermitido : item.getTiposHeroi()) {
                        if (heroiPermitido.toString().equals(heroi.getClass().getSimpleName().toUpperCase())) {
                            permitido = true;
                            break;
                        }
                    }
                    if (permitido) {
                        if (heroi.getOuro() >= item.getPreco()) {
                            if (item instanceof Arma) {
                                Arma arma = (Arma) item;
                                heroi.setArma(arma);
                            } else if (item instanceof PocaoHP) {
                                PocaoHP pocao = (PocaoHP) item;
                                heroi.adicionarPocao(pocao);
                            }

                            heroi.decrementarOuro(item.getPreco());
                            itensComprados.add(item);
                            inventario.remove(item);
                            System.out.println("\nCompra realizada com sucesso!\n");
                            System.out.println("\nCompraste o item: " + item.getNome());
                            System.out.println("O valor do item é: " + item.getPreco());
                            System.out.println("Seu ouro restante: " + heroi.getOuro() + " moedas.");
                            System.out.println("--------------------\n");
                            imprimirInventario();
                        } else {
                            System.out.println("\nNão tens moedas de outro suficientes para essa compra.\n");
                        }
                    } else {
                        System.out.println("\nItem do vendedor não encontrado\n");
                    }
                    heroi.mostrarDetalhes();
                } else {
                    System.out.println("\nEsse item já foi comprado. Escolhe outro item.\n");
                }
            } else {
                System.out.println("\nEscolha inválida!\n");
            }
        }



    }

}



