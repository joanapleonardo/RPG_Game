package Itens;

import java.util.ArrayList;

public class Arma extends ItemHeroi{
    private int ataque;

    public Arma(String nome, int preco, ArrayList<TiposHeroi> tiposHeroi, int ataque) {
        super(nome, preco, tiposHeroi);
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Nome: " +getNome());
        System.out.println("Preço: " +getPreco());
        System.out.println("Tipo Heroi: " +getTiposHeroi());
        System.out.println("Ataque: " +getAtaque());
    }

}


