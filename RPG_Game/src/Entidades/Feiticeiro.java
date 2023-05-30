package Entidades;

import Itens.PocaoHP;

import java.util.ArrayList;

public class Feiticeiro extends Heroi {
    public Feiticeiro(String nome, int vida, int forca, int nivel, int ouro) {
        super(nome, vida, forca, nivel, ouro);
    }

    /**
     * Método subscrito da classe NPC
     * @param npc
     */

    @Override
    public Entidade atacar(NPC npc) {

        boolean existeArma = true;

        if(this.getArma() == null) {
            existeArma = false;
        }

        if(existeArma == true) {
            while (this.getVida() > 0 && npc.getVida() > 0) {

                // O herói ataca
                int danoHeroi = this.getForca() + this.getArma().getAtaque();
                npc.setVida(npc.getVida() - danoHeroi);
                System.out.println(npc.getNome() +" : "+ npc.getVida());
                // Verifica se o herói ainda está vivo
                // Verifica se o inimigo ainda está vivo
                if (npc.getVida() <= 0) {
                    this.subirNivel();
                    this.incrementarVida(10);
                    this.incrementarForca(1);
                    this.incrementarOuro(10);

                } else {
                    // O inimigo ataca)
                    int danoInimigo = (int) (npc.getForca());
                    this.subtrairVida(danoInimigo); // que é a quantidade passada no parametro da funcao na classe Heroi
                    System.out.println("A tua vida: " + this.getVida());
                }
            }
        }

        if (npc.getVida() <= 0 && existeArma == true) {
            System.out.println("O herói venceu!\n");
            return this;
        } else {
            System.out.println("O herói foi derrotado.\n");
            return npc;
        }
    }


    /**
     * Método vindo da classe Entidade, obrigatório a todas.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Detalhes do Feiticeiro:");
        System.out.println("Nome: " + getNome());
        System.out.println("Vida: " + getVida());
        System.out.println("Força: " + getForca());
        System.out.println("Nível: " + getNivel());
        System.out.println("Ouro: " + getOuro());

        System.out.println("Arma:");
        if (getArma() != null) {
            System.out.println("- " + getArma().getNome());
        } else {
            System.out.println("Nenhuma arma equipada.");
        }

        if(getPocoes().size() > 0) {
            System.out.println("Poções:");
            for (PocaoHP pocao : getPocoes()) {
                System.out.println("- " + pocao.getNome());
            }
        }
        else {
            System.out.println("Sem poções!");
        }
    }
}
