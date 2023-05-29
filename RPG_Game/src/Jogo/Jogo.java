package Jogo;
import Entidades.*;
import Itens.Arma;
import Itens.ItemHeroi;
import Itens.PocaoHP;
import Itens.TiposHeroi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Jogo {


    /******************* Instanciar os personagens *************************/
    public static Cavaleiro criarCavaleiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nQual é o nome do Cavaleiro?");
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

        public static void main (String[]args){

        Scanner scanner = new Scanner(System.in);
        boolean venceu;
        String resposta = "";
        do {
            /*********** Selecionar o tipo de herói *******************/
            int heroiEscolhido; // só usei uma int porque é mais eficiente em termos computacionais, apesar de me dar mais trabalho no tratamento de excepções do que uma string
            Heroi heroi = null;
            boolean opcaoValida = false;

            while (!opcaoValida) {
                System.out.println("\nBem-vindo ao Reino de Eldoria!");
                System.out.println("\nAqui, a magia fluía livremente e criaturas míticas vagueavam pela terra. \nO reino estava sob a proteção de um rei sábio e justo, que governava com benevolência e sabedoria. \nNo entanto, o equilíbrio do reino foi ameaçado quando um antigo mal despertou das sombras.");
                System.out.println("\nUm artefato místico conhecido como a Pedra das Trevas, que continha um poder incrível e perigoso, foi roubado de seu local de descanso!");
                System.out.println("\nA Pedra das Trevas possui a capacidade de corromper qualquer ser que a tocasse, transformando-o num agente do caos e da destruição. \nAgora, nas mãos erradas, essa relíquia pode causar o fim de Eldoria!!");
                System.out.println("\nO Rei, preocupado com o destino de seu reino, convocou os maiores heróis e aventureiros de toda a terra para uma missão épica: \nrecuperar a Pedra das Trevas e salvar Eldoria! \nEntre os convocados, estão um cavaleiro valente, um arqueiro habilidoso e um feiticeiro poderoso.");
                System.out.println("\nJuntos partirão numa jornada perigosa e cheia de desafios. Juntos, viajarão por terras desconhecidas, enfrentando monstros aterrorizantes. \nAo longo de sua jornada, eles encontrarão aliados improváveis!");

                System.out.println("\nEscolhe o teu personagem: ");
                System.out.println("1. Cavaleiro");
                System.out.println("2. Feiticeiro");
                System.out.println("3. Arqueiro");
                System.out.print("->");

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
                            System.out.println("Opção inválida. Por favor, escolhe uma opção válida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Por favor, insere um número de 1 a 3.");
                }
            }

            opcaoValida = false;
            int dificuldade = 0;

            while (!opcaoValida) {
                try {
                    System.out.println("\nEscolhe o nível de dificuldade: ");
                    System.out.println("1. Fácil");
                    System.out.println("2. Difícil");
                    System.out.print("->");

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
                    System.out.println("\nTens 300 pontos de criação da personagem.");
                } else if (dificuldade == 2) {
                    pontosCriacao = 220;
                    System.out.println("Tens tem 220 pontos de criação da personagem.");
                } else {
                    System.out.println("Opção inválida. Jogo terminado.");
                    return;
                }

                System.out.println("Distribui os pontos de criação entre vida e força.");
                System.out.println("Nota: cada ponto de vida vale um ponto de criação e cada ponto de força vale 5 pontos de criação.");

                System.out.println("\nPontos de vida disponíveis: " + pontosCriacao);
                System.out.println("Quantos pontos pretendes atribuir a vida? ");
                pontosVida = scanner.nextInt();

                if (pontosVida > pontosCriacao) {
                    System.out.println("Não podes usar mais pontos de vida do que os disponíveis.");
                    continue;
                }

                System.out.println("Pontos de força disponíveis: " + ((pontosCriacao - pontosVida) / 5));
                System.out.println("Quantos pontos pretendes atribuir a força? ");

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
            System.out.println("\nO " + heroi.getClass().getSimpleName() + " " + heroi.getNome() +
                    " vai iniciar esta jornada épica na dificuldade " + (dificuldade == 1 ? "Fácil" : "Difícil") + ". \nDetalhes do herói: " +
                    "Força: " + heroi.getForca() + ", Vida: " + heroi.getVida() + ", Ouro: " + heroi.getOuro() + ".");







            venceu = labirinto(0, heroi);

            if(venceu==true){
                System.out.println("yei");
            } else {
                System.out.println("game over");
                System.out.println("deseja tentar de novo? S/N");
                resposta = scanner.next();
                resposta = resposta.toUpperCase();
                //input de reposta. se resposta for sim corre tudo de novo
            }

        }while (resposta.equals("S"));
    }



    public static boolean labirinto(int op, Heroi heroi){
        Scanner scanner = new Scanner(System.in);

// instanciar inimigos
        NPC Hades = new NPC("Hades", 100, 10);
        //npc1.mostrarDetalhes();

        NPC Dragao = new NPC("Dragão Ancião", 80, 8);
        //npc2.mostrarDetalhes();

        NPC Necromante = new NPC("Necromante", 120, 12);
        //npc3.mostrarDetalhes();

        NPC Bruxa = new NPC("Bruxa Malévola ", 150, 15);
        //npc4.mostrarDetalhes();

        NPC Demonio = new NPC("Demônio das Sombra", 90, 9);
        //npc5.mostrarDetalhes();

        // instância do arraylist que tem que ser passado como parâmetro na instância da PocaoHP
        ArrayList<TiposHeroi> tiposHeroi = new ArrayList<>();
        tiposHeroi.add(TiposHeroi.CAVALEIRO);
        tiposHeroi.add(TiposHeroi.ARQUEIRO);
        tiposHeroi.add(TiposHeroi.FEITICEIRO);


        PocaoHP pocaoSOS = new PocaoHP("Poção de Cura SOS", 5, tiposHeroi, 60);

        PocaoHP pocaoCompleta = new PocaoHP("Poção de Cura Completa", 10, tiposHeroi, 90);

        PocaoHP pocaoMenor = new PocaoHP("Poção de Cura Menor", 15, tiposHeroi, 25);


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

        Arma lanca = new Arma("Lança", 5, todosTiposHeroi, 20);
        Arma adaga = new Arma("Adaga", 5, todosTiposHeroi, 20);
        Arma cetroArcano = new Arma("Cetro Arcano", 10, todosTiposHeroi, 20);
        Arma varinhaMagica = new Arma("Varinha Mágica", 8, heroiFeiticeiro, 2);
        Arma livroDeFeitiço = new Arma("Livro de feitiço", 10, heroiFeiticeiro, 20);
        Arma espadaLonga = new Arma("Espada Longa", 50, heroiCavaleiro, 20);
        Arma arcoLongo = new Arma("Arco Longo", 50, heroiArqueiro, 20);
        Arma besta = new Arma("Besta", 50, heroiArqueiro, 20);
        Arma machadoDeBatalha = new Arma("Machado de batalha", 50, heroiCavaleiro, 20);
        Arma espada = new Arma("Espada", 10, heroiCavaleiro, 20);


        //instanciar vendedor com inventário
        Vendedor vendedor1 = new Vendedor();

        vendedor1.adicionarItem(pocaoCompleta);
        vendedor1.adicionarItem(pocaoSOS);
        vendedor1.adicionarItem(pocaoMenor);
        vendedor1.adicionarItem(lanca);
        vendedor1.adicionarItem(adaga);
        vendedor1.adicionarItem(cetroArcano);
        vendedor1.adicionarItem(varinhaMagica);
        vendedor1.adicionarItem(livroDeFeitiço);
        vendedor1.adicionarItem(espadaLonga);
        vendedor1.adicionarItem(arcoLongo);
        vendedor1.adicionarItem(besta);
        vendedor1.adicionarItem(machadoDeBatalha);
        vendedor1.adicionarItem(espada);


        //int opcao =
        switch (op)
        {
            case 0: {
                System.out.println("\nÀs portas de Eldoria e antes de iniciares a tua jornada, tens um vendedor onde podes comrar tudo o que precisas para enfrentar as maiores adversidades.\n");

                // Exibindo os itens disponíveis para compra
                vendedor1.imprimirInventario();

                System.out.println("Deseja comprar algum item? (Digite o número correspondente ou 0 para avançar sem comprar)");

                int opcao = scanner.nextInt() - 1;
                vendedor1.vender(heroi, opcao);

                do {
                    System.out.println("Inicio da jornada tem 2 opções");
                    System.out.println("1 - Vale dos mortos");
                    System.out.println("2 - Montanha");

                    op = scanner.nextInt();

                } while (op < 0 && op > 2);

                labirinto(op, heroi);
            }
            break;

            case 1: {
                System.out.println("\nCorajoso! É preciso audácia para enfrentar o Vale dos Mortos.");
                System.out.println("\nTerá de enfrendar Hades, o deus das trevas. Boa sorte!");

                heroi.atacar(new NPC("Hades", 100, 10));

                System.out.println("\nEscolha uma opção: ");
                System.out.println("5 - Cidade");
                System.out.println("6 - Floresta");

                do{
                    op = scanner.nextInt();
                }while (op < 5 && op > 6);

                labirinto(op, heroi);

                break;}

            case 2:
                System.out.println("Casa 2");
                //MONTANHA


                break;

            case 3:



                break;

            case 4:



                break;

            case 5:
                System.out.println("casa5");



                break;

            case 6: System.out.println("casa6");



                break;
            case 7:



                break;
            case 8:



                break;
            case 9:


                break;
            case 10:



                break;
            case 11:



                break;
            case 12:



                break;
            default:

                break;
        }
        return true;
    }


    }


//! APÓS A CONCLUSÃO FAZER JAVADOC!