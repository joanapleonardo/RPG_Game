package Itens;

import java.util.ArrayList;

public abstract class ItemHeroi{
    private String nome;
    private int preco;
    private ArrayList<TiposHeroi> tiposHeroi;


    /**
     * Construtor da classe Heroi
     * @param nome
     * @param preco
     * @param tiposHeroi
     */
    public ItemHeroi(String nome, int preco, ArrayList<TiposHeroi> tiposHeroi) {
        this.nome = nome;
        this.preco = preco;
        this.tiposHeroi = tiposHeroi;
    }

    /**
     * Método obrigatório da classe heroi
     */
    public abstract void mostrarDetalhes();


    public String getNome() {
        return nome;
    }


    public int getPreco() {
        return preco;
    }

    public ArrayList<TiposHeroi> getTiposHeroi() {
        return tiposHeroi;
    }
}
