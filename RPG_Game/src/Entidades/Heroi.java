package Entidades;
import Itens.Arma;
import Itens.PocaoHP;

import java.util.ArrayList;

/**
 * Classe abstrata Heroi, que deriva da classe mãe chamada Entidade
 */
public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private Arma arma;
    private ArrayList<PocaoHP> pocoes;




    /** --------------------------- Construtores ----------------------------------*/

    /**
     * Método construtor da classe Heroi
     * @param nome
     * @param vida
     * @param forca
     * @param nivel
     * @param ouro
     */
    public Heroi(String nome, int vida, int forca, int nivel, int ouro) {
        super(nome, vida, forca);
        this.nivel = nivel;
        this.ouro = ouro;
    }

    /**
     * Método para confrontar o NPC
     * @param npc
     */
    public abstract void atacar(NPC npc);

    /**
     * Método para adicionar uma pocao ao ArrayList de PocaoHP
     * @param pocao
     */
    public void adicionarPocao(PocaoHP pocao) {  // MÉTODO NÃO PEDIDO NO ENUNCIADO
        pocoes.add(pocao);
    }

    /**
     * Método para retirar ouro da conta do Heroi caso ele compre um item
     * @param quantidade
     */
    public void decrementarOuro(int quantidade) {
        ouro -= quantidade;
    }

    /**
     * Metodo que usa pocao para se recuperar
     * @param pocao
     */
    public void usarPocao(PocaoHP pocao) {
        // Imprimir o inventário do herói
        System.out.println("Inventário do Herói:");
        for (PocaoHP p : pocoes) {
            System.out.println(p.getNome());
        }

        // Perguntar qual poção deseja usar
        System.out.println("Qual poção deseja usar?");
        String nomePocao = ""; // Substitua pelo nome da poção selecionada

        // Procurar a poção no inventário
        PocaoHP pocaoSelecionada = buscarPocaoPorNome(nomePocao);

        // Verificar se a poção foi encontrada
        if (pocaoSelecionada != null) {
            // Incrementar a vida do herói com a vida da poção
            int vidaRecuperada = pocaoSelecionada.getCurar();
            incrementarVida(vidaRecuperada);

            // Remover a poção do inventário
            pocoes.remove(pocaoSelecionada);

            System.out.println("Poção usada com sucesso!");
        } else {
            System.out.println("A poção selecionada não está no inventário do herói.");
        }
    }

    /**
     *  Método que busca a ppocao por nome
     * @param nomePocao
     * @return
     */
    private PocaoHP buscarPocaoPorNome(String nomePocao) {
        for (PocaoHP pocao : pocoes) {
            if (pocao.getNome().equals(nomePocao)) {
                return pocao;
            }
        }
        return null;
    }

    /**
     * Método que incrementa vida ao Heroi
     * @param quantidade
     */
    // Método para incrementar a vida do herói
    public void incrementarVida(int quantidade) {
        setVida(getVida() + quantidade);
    }


    /**
     * Método que subtrai vida do Heroi
     * @param quantidade
     */
    public void subtrairVida(int quantidade) {  // metodo chamado nos herois em si que recebem danos NPC
        setVida(getVida() - quantidade);
    }

    /**
     * Metodo para incrementar força quando o Herói vencer
     * @param quantidade
     */
    public void incrementarForca(int quantidade) {
        setForca(getForca() + quantidade);
    }

    /**
     * Método que sobe o nível do Heroi
     */
    public void subirNivel () {
        nivel++;
    }

    /**
     * Método para incrementar ouro quando o Herói vence
     * @param quantidade
     */
    public void incrementarOuro(int quantidade) {
        ouro += quantidade;
    }



    /** --------------------------- GETTERS AND SETTERS  ----------------------------------*/

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public ArrayList<PocaoHP> getPocoes() {
        return pocoes;
    }

    public void setPocoes(ArrayList<PocaoHP> pocoes) {
        this.pocoes = pocoes;
    }


}
