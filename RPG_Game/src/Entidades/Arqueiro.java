package Entidades;

import Itens.Arma;
import Itens.PocaoHP;

import java.util.ArrayList;

public class Arqueiro extends Heroi {
    public Arqueiro(String nome, int vida, int forca, int nivel, int ouro) {
        super(nome, vida, forca, nivel, ouro);
    }

    /**
     * Método subscrito da classe NPC
     * @param npc
     */
    @Override
    public void atacar(NPC npc) {


        while (this.getVida()>=0 && npc.getVida()>=0) {

            // O herói ataca
            int danoHeroi = this.getForca() + this.getArma().getAtaque();
            npc.setVida(npc.getVida() - danoHeroi);
            System.out.println(npc.getVida());
            //npc.subtrairVida(danoHeroi);
            // Verifica se o herói ainda está vivo
            // Verifica se o inimigo ainda está vivo
            if (npc.getVida() <= 0) {
                System.out.println("O herói venceu!");
                this.subirNivel();
                this.incrementarVida(10);
                this.incrementarForca(1);
                this.incrementarOuro(10);
            } else {
                // O inimigo ataca)
                int danoInimigo = (int) (npc.getForca() * 1.1); // 10% a mais do que devido a falta de proteçãop
                this.subtrairVida(danoInimigo); // que é a quantidade passada no parametro da funcao na classe Heroi
                System.out.println(this.getVida());

                if (this.getVida() <= 0) {
                    System.out.println("O herói foi derrotado.");
                }
            }
        }
    }

    /**
     * Método vindo da classe Entidade, obrigatório a todas.
     */
    @Override
    public void mostrarDetalhes() {
        System.out.println("Detalhes do Arqueiro:");
        System.out.println("Nome: " + getNome());
        System.out.println("Vida: " + getVida());
        System.out.println("Força: " + getForca());
        System.out.println("Nível: " + getNivel());
        System.out.println("Ouro: " + getOuro());
        System.out.println("Arma: " + getArma().getNome());
        System.out.println("Poções: " + getPocoes().size());
    }
}