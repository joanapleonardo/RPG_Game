package Jogo;
import Entidades.*;
import Itens.Arma;
import Itens.PocaoHP;
import Itens.TiposHeroi;
import java.util.ArrayList;
import java.util.Scanner;


public class Jogo {


    /**
     * Método para instanciar um objeto Cavaleiro
     * @return
     */
    public static Cavaleiro criarCavaleiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nQual é o nome do Cavaleiro?");
        String nome = scanner.nextLine();
        return new Cavaleiro(nome, 0, 0, 0, 0);
    }

    /**
     * Método para instanciar um objeto Feiticeiro
     * @return
     */
    public static Feiticeiro criarFeiticeiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nQual é o nome do Feiticeiro?");
        String nome = scanner.nextLine();
        return new Feiticeiro(nome, 0, 0, 0, 0);
    }

    /**
     * Método para instanciar um objeto Arqueiro
     * @return
     */
    public static Arqueiro criarArqueiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nQual é o nome do Arqueiro?");
        String nome = scanner.nextLine();
        return new Arqueiro(nome, 0, 0, 0, 0);
    }


        public static void main (String[]args){

        Scanner scanner = new Scanner(System.in);
        boolean venceu;
        String resposta = "";
        do {
            int heroiEscolhido;
            Heroi heroi = null;
            boolean opcaoValida = false;
//inicio do jogo/introdução
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
                } else if ( dificuldade == 2) {
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
                    "\nForça: " + heroi.getForca() + "\nVida: " + heroi.getVida() + "\nOuro: " + heroi.getOuro() + ".");


            venceu = labirinto(0, heroi);

            if(venceu==true){
                System.out.println("O inimigo foi derrotado");
                heroi.mostrarDetalhes();
            } else {
                System.out.println("game over");
                System.out.println("deseja tentar de novo? S/N");
                resposta = scanner.next();
                resposta = resposta.toUpperCase();
            }

        }while (resposta.equals("S"));
    }

    /**
     * Metodo que permite reiniciar o jogo em caso de vitória ou derrota
     * @param message
     */
    private static void reset(String message)
    {
        if(message != null) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + message + "\n");

            int op;
            do{
                System.out.println("\n1 - Continuar a jogar!");
                System.out.println("\n2 - Sair");
                op = scanner.nextInt();
            }while (op < 1 || op > 2);

            if(op == 1) {
                main(null);
            }
        }
    }

    /**
     * inicio do labirinto
     * @param op
     * @param heroi
     * @return
     */
    public static boolean labirinto(int op, Heroi heroi){
        Scanner scanner = new Scanner(System.in);

        boolean p = false;
        // instanciar inimigos
        NPC Hades = new NPC("Hades", 90, 30);
        //npc1.mostrarDetalhes();

        NPC Dragao = new NPC("Dragão Ancião", 90, 30);
        //npc2.mostrarDetalhes();

        NPC Necromante = new NPC("Necromante", 80, 40);
        //npc3.mostrarDetalhes();

        NPC Bruxa = new NPC("Bruxa Malévola ", 15, 5);
        //npc4.mostrarDetalhes();

        NPC Demonio = new NPC("Demônio das Sombra", 100, 50);
        //npc5.mostrarDetalhes();

        // instância do arraylist que tem que ser passado como parâmetro na instância da PocaoHP
        ArrayList<TiposHeroi> tiposHeroi = new ArrayList<>();
        tiposHeroi.add(TiposHeroi.CAVALEIRO);
        tiposHeroi.add(TiposHeroi.ARQUEIRO);
        tiposHeroi.add(TiposHeroi.FEITICEIRO);


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



        PocaoHP pocaoSOS = new PocaoHP("Poção de Cura SOS", 5, tiposHeroi, 5);
        PocaoHP pocaoCompleta = new PocaoHP("Poção de Cura Completa", 15, tiposHeroi, 20);
        PocaoHP pocaoMenor = new PocaoHP("Poção de Cura Menor", 10, tiposHeroi, 10);

        Arma lanca = new Arma("Lança", 5, todosTiposHeroi, 20);
        Arma adaga = new Arma("Adaga", 8, todosTiposHeroi, 30);
        Arma cetroArcano = new Arma("Cetro Arcano", 10, todosTiposHeroi, 40);
        Arma varinhaMagica = new Arma("Varinha Mágica", 8, heroiFeiticeiro, 30);
        Arma livroDeFeitiço = new Arma("Livro de feitiço", 10, heroiFeiticeiro, 40);
        Arma espadaLonga = new Arma("Espada Longa", 20, heroiCavaleiro, 80);
        Arma arcoLongo = new Arma("Arco Longo", 20, heroiArqueiro, 80);
        Arma besta = new Arma("Besta", 20, heroiArqueiro, 80);
        Arma machadoDeBatalha = new Arma("Machado de batalha", 15, heroiCavaleiro, 45);
        Arma espada = new Arma("Espada", 10, heroiCavaleiro, 40);


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


        //Labirinto construido com recursividade, opções para o utilizador e possibilidade de vitória
        switch (op)
        {
            case 0:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nÀs portas de Eldoria e antes de iniciares a tua jornada, tens um vendedor onde podes comrar tudo o que precisas para enfrentar as maiores adversidades.\n");

                //Mostrar itens disponíveis para compra
                vendedor1.imprimirInventario();
                vendedor1.vender(heroi);

                do {
                    System.out.println("\nAgora que estás pronto e equipado para começar esta jornada só temos de escolher um caminho.");
                    System.out.println("1 - Vale dos mortos");
                    System.out.println("2 - Montanha");
                    System.out.print("->");

                    op = scanner.nextInt();

                } while (op != 1 && op != 2);

                labirinto(op, heroi);

            break;

            case 1:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nCorajoso! É preciso audácia para enfrentar o Vale dos Mortos.");
                System.out.println("\nTerá de enfrendar Hades, o deus das trevas. Boa sorte!");

                p = (heroi.atacar(Hades) == heroi);
                if (p == true) {

                    heroi.usarPocao();

                    heroi.mostrarDetalhes();

                    System.out.println("\nEscolha uma opção: ");
                    System.out.println("2 - Montanha");
                    System.out.println("3 - Floresta");

                    do{
                        op = scanner.nextInt();
                    }while (op != 2 && op != 3);

                    labirinto(op, heroi);
                }
                else reset("Game Over!!!");

                break;

            case 2:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nEspero que estejas preparado para atravessar a Montanha do Dragão.");
                System.out.println("\nTem cuidado porque Dragões existem mesmo!!");
                System.out.println("\n OH OH, la vem ele!!!");

                p = (heroi.atacar(Dragao) == heroi);
                if (p == true) {
                    heroi.usarPocao();

                    heroi.mostrarDetalhes();

                    labirinto (4,heroi);
                }
                else reset("Game Over!!!");

                break;

            case 3:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nSempre a avançar!");
                System.out.println("Vamos entrar na floresta encantada!");
                System.out.println("Cuidado com os contrabandistas e vendedores de banha da cobra!");

                heroi.mostrarDetalhes();

                System.out.println("\nVENDEDOR: 'Olá caro amigo viajante!'");
                System.out.println("Interessado em algum dos meus itens?\n");

                vendedor1.imprimirInventario();

                System.out.println("Insira o número correspondente ou 0 para avançar sem comprar)");

                vendedor1.vender(heroi);

                labirinto(5, heroi);

                break;

            case 4:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nUfff!! Que grande batalha contra o Dragão!!");
                System.out.println("Ainda bem que chegamos a uma aldeia bem pitoresca onde poderemos descansar");
                System.out.println("Foi adicionado ao teu inventário uma Poção para recuperares a energia.");

                heroi.adicionarPocao(pocaoSOS);

                System.out.println("\nChegamos a uma encruzilhada: ");
                System.out.println("Por onde vamos seguir");
                System.out.println("5 - Esquerda: Floresta sombria");
                System.out.println("8 - Direita: Caminho pela margem do rio");

                do{
                    op = scanner.nextInt();
                }while (op != 5 && op != 8);

                labirinto(op, heroi);
                break;

            case 5:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nDecisões... Decisões");
                System.out.println("\nEscolha uma opção: ");
                System.out.println("6 - Atravessar o rio a nado?");
                System.out.println("7 - Atravessar uma ponte em muito mau estado?");

                do{
                    op = scanner.nextInt();
                }while (op != 6 && op != 7);

                labirinto(op, heroi);
            break;

            case 6:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("\nO rio está perigoso!");
                System.out.println("Mas há perigos maiores do que a corrente...");

                System.out.println("\nNECROMANTE: Olá, Olá! Quem temos aqui?");
                System.out.println("Espero que gostes da vista porque a tua alma ficará aprisionada aqui para sempre!");
                System.out.println("HA HA HA HA HA!");

                p = (heroi.atacar(Necromante) == heroi);
                if (p == true) {

                    heroi.usarPocao();

                    heroi.mostrarDetalhes();

                    System.out.println("\nEscolha uma opção: ");
                    System.out.println("7 - Atravessar uma ponte em muito mau estado?");
                    System.out.println("11 - Pedir boleia ao barqueiro");

                    do {
                        op = scanner.nextInt();
                    } while (op != 7 && op != 11);

                    labirinto(op, heroi);
                }
                else reset("Game Over!!!");

                break;

            case 7:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("Esta ponte está muito instável.");
                System.out.println("OOOPPS!!! Cuidado!!!");
                System.out.println("SPLASSSHHH!!");
                System.out.println("\n\nConseguiste chegar até à margem mas perdesde muita energia\n");

                heroi.setVida(heroi.getVida() - 15);
                heroi.mostrarDetalhes();
                heroi.usarPocao();

                System.out.println("\nE agora? Seguimos por um atalho até à Gruta das Sombras? \nOu vamos pelo caminho da margem?");
                System.out.println("8 - Caminho da margem");
                System.out.println("9 - Atalho para a Gruta das Sombras");

                do{
                    op = scanner.nextInt();
                }while (op != 8 && op != 9);

                labirinto(op, heroi);

                break;
            case 8:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("Que caminho tão bonito e calmo!");
                System.out.println("Oh oh... Falei cedo demais!");
                System.out.println("Cuidado!!");
                System.out.println("\n\nBRUXA: Ha ha ha ha. És meu!!!");

                p = (heroi.atacar(Bruxa) == heroi);
                if (p == true) {

                    heroi.usarPocao();

                    heroi.mostrarDetalhes();


                    labirinto(10, heroi);
                }
                else reset("Game Over!!!");

                break;
            case 9:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("Chegamos finalmente à Gruta das Sombras.");
                System.out.println("Julga-se que é aqui que está escondida a Pedra das Trevas...");
                System.out.println("Mas para a recuperarmos temos de enfrentar quem a roubou... E não vai ser pêra doce!");

                heroi.usarPocao();

                System.out.println("\n\nO que é este barulho??\nO que é aquilo??");
                System.out.println("UM DEMóNIO!!!");

                p = (heroi.atacar(Demonio) == heroi);
                if (p == true) {

                    heroi.mostrarDetalhes();

                    reset("Conseguimos!!!\n Derrotamos o demónio e recuperamos a Pedra das Trevas!!\n ");
                }
                else reset("Game Over!!!");

                break;
            case 10:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("UFF... Que medo! Que embuscada!");
                System.out.println("Obviamente que o nosso herói conseguiu desenvencilhar-se da Bruxa com relativa facilidade!");

                System.out.println("Mas agora temos de seguir caminho. \nNão sem antes passarmos no vendedor novamente!\n");


                heroi.mostrarDetalhes();

                System.out.println("\nVENDEDOR: 'Olá caro amigo viajante!'");
                System.out.println("Interessado em algum dos meus itens?\n");

                vendedor1.imprimirInventario();

                vendedor1.vender(heroi);

                labirinto (9,heroi);



                break;
            case 11:
                System.out.println("\n-------------------------------------------\n");
                System.out.println("Decisão sensata! \n Barqueiro simpático e no cais temos um vendedor!");
                System.out.println("Tudo o que precisamos para seguir para a Gruta das Sombras!\n");

                heroi.mostrarDetalhes();

                System.out.println("\nVENDEDOR: 'Olá caro amigo viajante!'");
                System.out.println("Interessado em algum dos meus itens?\n");

                vendedor1.imprimirInventario();

                vendedor1.vender(heroi);

                labirinto (9,heroi);
                break;
        }
        return true;
    }


    }