import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindo!");

        int escolha = 0;
        Tabuleiro tabuleiro = new Tabuleiro();
        ArrayList<Jogador> jogadores = tabuleiro.getJogadores();
        Scanner teclado = new Scanner(System.in);

        do {

            System.out.println("+-----------------------+");
            System.out.println("|  Menu de  opções:     |");
            System.out.println("| 1 - Cadastrar Jogador |");
            System.out.println("| 2 - Iniciar Jogo      |");
            System.out.println("| 3 - Sair              |");
            System.out.print("+-----------------------+\n");
            System.out.print("| Digite: ");

            if (teclado.hasNextInt()) {
                escolha = teclado.nextInt();
                teclado.nextLine();
            } else {
                System.out.println("| Entrada inválida! Digite um número entre 1 e 3.");
                teclado.next();
                continue;
            }

            System.out.println("+-----------------------+\n");

            switch (escolha){
                case 1:{
                    tabuleiro.criarJogadores();
                    break;
                }
                case 2:{
                    if (!jogadores.isEmpty()) {
                        while (!tabuleiro.getExisteVencedor()) {
                            int escolha2 = 0;
                            System.out.print("+-------------------------------------+\n");
                            System.out.println("| Iniciando Nova Rodada\n");

                            do {
                                System.out.print("+-------------------------------------+\n");
                                System.out.println("|          Menu de  opções:           |");
                                System.out.println("| 1 - Continuar Rodada                |");
                                System.out.println("| 2 - Modo Debug                      |");
                                System.out.print("+-------------------------------------+\n");
                                System.out.print("| Digite: ");

                                if (teclado.hasNextInt()) {
                                    escolha2 = teclado.nextInt();
                                    teclado.nextLine();
                                } else {
                                    System.out.println("| Entrada inválida! Digite 1 ou 2.");
                                    teclado.next();
                                    continue;
                                }

                                System.out.println("+-------------------------------------+\n");

                                if (escolha2 == 2) {
                                    tabuleiro.ativarModoDebug();
                                }else if (escolha2 != 1) {
                                    System.out.println("| Opção inválida! Escolha 1 ou 2.");
                                }
                            } while (escolha2 != 1);

                            tabuleiro.executarRodada();
                            System.out.println("\nRodada encerrada!");
                            System.out.println("+-------------------------------------+\n");
                        }

                        System.out.println("+--------------PLACAR FINAL------------+");
                        System.out.printf("| Jogador Vencedor: %s\n", tabuleiro.getVencedor().getCor());

                        for (int i = 0; i < jogadores.size(); i++) {
                            System.out.printf("| Jogador %s teve %d jogadas\n", jogadores.get(i).getCor(), jogadores.get(i).getQtdJogadas());
                            System.out.printf("| Jogador %s casa final: %d\n",jogadores.get(i).getCor(), jogadores.get(i).getCasaAtual());
                        }

                        System.out.println("+--------------------------------+");
                        escolha = 3;
                    } else {
                        System.out.print("| Nenhum jogador foi Cadastrado!\n");
                    }
                    break;
                }
                case 3:{
                    System.out.println("Jogo Encerrado!");
                    break;
                }

                default:
                    System.out.println("| Opção inválida! Digite um número entre 1 e 3.");
            }
            System.out.println();
        } while (escolha != 3);

    }
}