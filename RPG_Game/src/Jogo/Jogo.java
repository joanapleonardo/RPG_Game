package Jogo;
import Entidades.*;
import Itens.Arma;
import Itens.ItemHeroi;
import Itens.PocaoHP;
import Itens.TiposHeroi;

import java.util.ArrayList;
import java.util.Scanner;


public class Jogo {

    public static void labirinto() {

        Scanner scanner = new Scanner(System.in);

        /*********** Selecionar o tipo de herói *******************/
        int heroiEscolhido; // só usei uma int porque é mais eficiente em termos computacionais, apesar de me dar mais trabalho no tratamento de excepções do que uma string
        Heroi heroi = null;
        boolean opcaoValida = false;

        while (!opcaoValida) {
            System.out.println("Selecione o tipo de herói: ");
            System.out.println("1. Cavaleiro");
            System.out.println("2. Feiticeiro");
            System.out.println("3. Arqueiro");

            try {
                String input = scanner.nextLine();
                heroiEscolhido = Integer.parseInt(input);

                switch (heroiEscolhido) {
                    case 1:
                        heroi = criarCavaleiro();
                        opcaoValida = true;
                        break;
                    case 2:
                        heroi = criarFeiticeiro();
                        opcaoValida = true;
                        break;
                    case 3:
                        heroi = criarArqueiro();
                        opcaoValida = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número de 1 a 3.");
            }
        }

        opcaoValida = false;
        int dificuldade = 0;

        while (!opcaoValida) {
            try {
                System.out.println("Selecione a dificuldade: ");
                System.out.println("1. Fácil");
                System.out.println("2. Difícil");

                String input = scanner.nextLine();
                dificuldade = Integer.parseInt(input);

                if (dificuldade == 1 || dificuldade == 2) {
                    opcaoValida = true;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número de 1 a 2.");
            }
        }

        //Definir os pontos de criação de personagem
        int pontosCriacao;
        int pontosVida;
        int pontosForca = 0;

        do {
            if (dificuldade == 1) {
                pontosCriacao = 300;
                System.out.println("Tens tem 300 pontos de criação da personagem.");
            } else if (dificuldade == 2) {
                pontosCriacao = 220;
                System.out.println("Tens tem 220 pontos de criação da personagem.");
            } else {
                System.out.println("Opção inválida. Jogo terminado.");
                return;
            }

            System.out.println("Distribui os pontos de criação entre vida e força.");
            System.out.println("Nota: cada ponto de vida vale um ponto de criação e cada ponto de força vale 5 pontos de criação.");

            System.out.println("Pontos de vida disponíveis: " + pontosCriacao);
            System.out.println("Digite a quantidade de pontos de vida: ");
            pontosVida = scanner.nextInt();

            if (pontosVida > pontosCriacao) {
                System.out.println("Não podes usar mais pontos de vida do que os disponíveis.");
                continue;
            }

            System.out.println("Pontos de força disponíveis: " + ((pontosCriacao - pontosVida) / 5));
            System.out.println("Qual a quantidade de pontos de força? ");

            pontosForca = scanner.nextInt();

            if (pontosForca > (pontosCriacao - pontosVida) / 5) {
                System.out.println("Não podes gastar mais pontos de força do que os disponíveis.");
                continue;
            }

            if (pontosVida + pontosForca * 5 != pontosCriacao) {
                System.out.println("Distribuição inválida. A soma dos pontos de vida e pontos de força deve ser igual aos pontos de criação.");
            }
        } while (pontosVida + pontosForca * 5 != pontosCriacao);

        //Atribuir vida e força ao herói
        heroi.setVida(pontosVida);
        heroi.setForca(pontosForca);

        // Atribuir ouro ao herói
        int ouro;
        if (dificuldade == 1) {
            ouro = 20;
        } else {
            ouro = 15;
        }
        heroi.setOuro(ouro);

        // Iniciar o labirinto
        System.out.println("A iniciar o labirinto com o herói " + heroi.getClass().getSimpleName() + " " + heroi.getNome() +
                " na dificuldade " + (dificuldade == 1 ? "Fácil" : "Difícil") + ". Detalhes do herói: " +
                "Força: " + heroi.getForca() + ", Vida: " + heroi.getVida() + ", Ouro: " + heroi.getOuro() + ".");


        // instanciar inimigos
        NPC npc1 = new NPC("Lorde das Trevas", 100, 10);
        //npc1.mostrarDetalhes();

        NPC npc2 = new NPC("Dragão Ancião", 80, 8);
        //npc2.mostrarDetalhes();

        NPC npc3 = new NPC("Necromante", 120, 12);
        //npc3.mostrarDetalhes();

        NPC npc4 = new NPC("Bruxa Malévola ", 150, 15);
        //npc4.mostrarDetalhes();

        NPC npc5 = new NPC("Demônio das Sombra", 90, 9);
        //npc5.mostrarDetalhes();
        // Resto da lógica do labirinto...

        // instância do arraylist que tem que ser passado como parâmetro na instância da PocaoHP
        ArrayList<TiposHeroi> tiposHeroi = new ArrayList<>();
        tiposHeroi.add(TiposHeroi.CAVALEIRO);
        tiposHeroi.add(TiposHeroi.ARQUEIRO);
        tiposHeroi.add(TiposHeroi.FEITICEIRO);


        PocaoHP pocao1 = new PocaoHP("Poção de Cura de Veneno", 300, tiposHeroi, 60);

        PocaoHP pocao2 = new PocaoHP("Poção de Cura de Maldição", 20, tiposHeroi, 85);

        PocaoHP pocao3 = new PocaoHP("Poção de Cura Completa", 40, tiposHeroi, 90);

        PocaoHP pocao4 = new PocaoHP("Poção de Cura Menor", 10, tiposHeroi, 25);


        // Todos os tipo de heroi
        ArrayList<TiposHeroi> todosTiposHeroi = new ArrayList<>();
        todosTiposHeroi.add(TiposHeroi.CAVALEIRO);
        todosTiposHeroi.add(TiposHeroi.ARQUEIRO);
        todosTiposHeroi.add(TiposHeroi.FEITICEIRO);


        // Combinação 5
        ArrayList<TiposHeroi> heroiCavaleiro = new ArrayList<>();
        heroiCavaleiro.add(TiposHeroi.CAVALEIRO);
        // Faça o que precisa com a combinacao5

        // Combinação 6
        ArrayList<TiposHeroi> heroiArqueiro = new ArrayList<>();
        heroiArqueiro.add(TiposHeroi.ARQUEIRO);
        // Faça o que precisa com a combinacao6

        // Combinação 7
        ArrayList<TiposHeroi> heroiFeiticeiro = new ArrayList<>();
        heroiFeiticeiro.add(TiposHeroi.FEITICEIRO);
        // Faça o que precisa com a combinacao7

        Arma arma1 = new Arma("Lança", 50, todosTiposHeroi, 20);
        Arma arma2 = new Arma("Adaga", 50, todosTiposHeroi, 20);
        Arma arma3 = new Arma("Cetro Arcano", 50, todosTiposHeroi, 20);
        Arma arma4 = new Arma("Varinha Mágica", 50, heroiFeiticeiro, 20);
        Arma arma5 = new Arma("Livro de feitiço", 50, heroiFeiticeiro, 20);
        Arma arma6 = new Arma("Espada Longa", 50, heroiCavaleiro, 20);
        Arma arma7 = new Arma("Arco Longo", 50, heroiArqueiro, 20);
        Arma arma8 = new Arma("Besta", 50, heroiArqueiro, 20);
        Arma arma9 = new Arma("Machado de batalha", 50, heroiCavaleiro, 20);


        //instanciar vendedor com inventário
        Vendedor vendedor1 = new Vendedor();

        vendedor1.adicionarItem(arma1);
        vendedor1.adicionarItem(arma2);
        vendedor1.adicionarItem(arma3);
        vendedor1.adicionarItem(arma4);
        vendedor1.adicionarItem(pocao1);
        vendedor1.adicionarItem(pocao2);
        vendedor1.adicionarItem(pocao3);
        vendedor1.adicionarItem(pocao4);

        vendedor1.imprimirInventario();
    }

    /******************* Instanciar os personagens *************************/
    public static Cavaleiro criarCavaleiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Cavaleiro:");
        String nome = scanner.nextLine();
        return new Cavaleiro(nome, 0, 0, 0, 0);
    }

    public static Feiticeiro criarFeiticeiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Feiticeiro:");
        String nome = scanner.nextLine();
        return new Feiticeiro(nome, 0, 0, 0, 0);
    }


    public static Arqueiro criarArqueiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Arqueiro:");
        String nome = scanner.nextLine();
        return new Arqueiro(nome, 0, 0, 0, 0);
    }

    /********************* Instancia 13 itens **********************/

    public static void main(String[] args) {
        labirinto();
    }
}

//! APÓS A CONCLUSÃO FAZER JAVADOC!