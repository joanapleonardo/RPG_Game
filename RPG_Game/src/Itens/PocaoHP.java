package Itens;

import java.util.ArrayList;

public class  PocaoHP extends ItemHeroi {

    private int curar;

    /**
     * Método construtor da classe PocaoHP
     * @param nome
     * @param preco
     * @param tiposHeroi
     */

    public PocaoHP(String nome, int preco, ArrayList<TiposHeroi> tiposHeroi, int curar) {
        super(nome, preco, tiposHeroi);
        this.curar = curar;
    }
    @Override
    public void mostrarDetalhes() {
        System.out.println("Nome: " +getNome());
        System.out.println("Preço: " +getPreco());
        System.out.println("Cura: " +getCurar());
    }

    public int getCurar() {
        return curar;
    }

}