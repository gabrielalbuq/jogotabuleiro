import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {

    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private int indexBlock = 7;
    private boolean existeVencedor = false;
    private Jogador vencedor;
    private int indexDebug = 7;

    Scanner teclado = new Scanner(System.in);

    public void criarJogadores() {
        Random gerador = new Random();

        if(this.jogadores.size() < 6) {
            int tipoJogador1 = (gerador.nextInt(3) + 1);
            String cor;
            boolean corUsada;
            String corDiferente;
            boolean entradaValida;

            do {
                corUsada = false;
                entradaValida = true;

                System.out.println("Escolha a cor do jogador: ");
                cor = teclado.nextLine();

                if (cor.isEmpty()){
                    System.out.println("Não pode ficar vazio");
                    corUsada = true;
                    continue;
                }

                for (char c : cor.toCharArray()) {
                    if (!Character.isLetter(c)) {
                        entradaValida = false;
                        break;
                    }
                }
                if (!entradaValida) {
                    System.out.println("Entrada Invalida");
                    continue;
                }

                for (int i = 0; i < jogadores.size(); i++) {
                    if (jogadores.get(i).getCor().equals(cor)) {
                        corUsada = true;
                        break;
                    }
                }

            } while (corUsada || !entradaValida);

            if (tipoJogador1 == 1) {
                Jogador jogador = new JogadorNormal(cor, 0);
                jogadores.add(jogador);
            }

            if (tipoJogador1 == 2) {
                Jogador jogador = new JogadorSortudo(cor, 0);
                jogadores.add(jogador);
            }

            if (tipoJogador1 == 3) {
                Jogador jogador = new JogadorAzarado(cor, 0);
                jogadores.add(jogador);
            }

            while (jogadores.size() < 2) {
                int tipoJogador2 = 0;
                corUsada = false;

                if (tipoJogador1 == 1) {
                    tipoJogador2 = gerador.nextInt(2, 4);
                }

                if (tipoJogador1 == 2) {
                    tipoJogador2 = gerador.nextInt(3, 5);
                }

                if (tipoJogador1 == 3) {
                    tipoJogador2 = gerador.nextInt(3, 5);
                    if (tipoJogador2 == 3) {
                        tipoJogador2--;
                    }
                }

                do{
                    entradaValida = true;
                    System.out.println("Escolha uma cor diferente para o outro jogador: ");
                    corDiferente = teclado.nextLine();

                    if (corDiferente.isEmpty()){
                        System.out.println("Não pode ficar vazio");
                        continue;
                    }

                    for (char j : corDiferente.toCharArray()) {
                        if (!Character.isLetter(j)) {
                            entradaValida = false;
                            break;
                        }
                    }
                    if (!entradaValida) {
                        System.out.println("Entrada Invalida");
                        continue;
                    }

                    for (int i = 0; i < jogadores.size(); i++) {
                        if (jogadores.get(i).getCor().equals(corDiferente)) {
                            corUsada = true;
                            break;
                        }
                    }

                }while (!entradaValida);

                if (!corUsada) {
                    if (tipoJogador2 == 2) {
                        Jogador jogadorDiferente = new JogadorSortudo(corDiferente, 0);
                        jogadores.add(jogadorDiferente);
                    }

                    if (tipoJogador2 == 3) {
                        Jogador jogadorDiferente = new JogadorAzarado(corDiferente, 0);
                        jogadores.add(jogadorDiferente);
                    }

                    if (tipoJogador2 == 4) {
                        Jogador jogadorDiferente = new JogadorNormal(corDiferente, 0);
                        jogadores.add(jogadorDiferente);
                    }
                }
            }
        } else {
            System.out.println("Limite de Jogadores Ultrapassado!");
        }

        System.out.print("+-------------------------------------+\n");
        System.out.println("Jogadores Cadastrados");
        for (Jogador jogador : jogadores) {
            System.out.println("Jogador: " + jogador.getCor());
        }
    }

    public void ativarModoDebug() {

        int casaEscolhida = 0 ;
        boolean jogadorFoiEscolhido = false ;

        do {
            System.out.print("+-------------------------------------+\n");
            System.out.print("Digite a cor do Jogador Escolhido: ");
            String corEscolhida = teclado.nextLine();

            for (int i = 0; i < this.jogadores.size(); i++) {
                if (this.jogadores.get(i).getCor().equalsIgnoreCase(corEscolhida)) {
                    System.out.print("Digite a nova posição do jogador: ");

                    boolean entradaValida = false;

                    while (!entradaValida) {
                        if (teclado.hasNextInt()){
                            casaEscolhida = teclado.nextInt();
                            teclado.nextLine();
                            entradaValida = true;
                        }else {
                            System.out.println(" Entrada inválida!");
                            System.out.print("Digite a nova posição do jogador: ");
                            teclado.next();

                        }
                    }
                    
                    System.out.print("+-------------------------------------+\n");

                    this.jogadores.get(i).setCasaAtual(casaEscolhida);
                    jogadorFoiEscolhido = true;

                    for (int j = 0; j < this.jogadores.size(); j++){
                        if (this.jogadores.get(j).getCor().equals(corEscolhida)){
                            this.indexDebug = this.jogadores.indexOf(this.jogadores.get(j));
                        }
                    }

                    break;
                }
            }

            if (!jogadorFoiEscolhido) {
                System.out.println("Jogador não encontrado ou entrada inválida! Por favor, tente novamente.!");
                System.out.println("+-------------------------------------+\n");
            }

        }while (!jogadorFoiEscolhido);

        if (jogadorFoiEscolhido) {
            System.out.println("Jogador Escolhido com Sucesso!");
            System.out.println("+-------------------------------------+\n");
        }
    }

    public void executarRodada() {

        for (int i = 0; i < this.jogadores.size(); i++) {
            if (!existeVencedor) {
                System.out.println("Agora é a Vez do Jogador: " + this.jogadores.get(i).getCor());
                System.out.print("+----------------------------------------------------------+\n");
                
                do {

                    if (this.jogadores.get(i).getResultadosIguais()) {
                        System.out.printf("| O jogador %s possui resultados iguais\n", this.jogadores.get(i).getCor());
                        this.jogadores.get(i).setResultadosIguais(false);
                    }

                    if (this.jogadores.indexOf(jogadores.get(i)) == indexBlock) {
                        this.indexBlock = 7;
                        System.out.printf("| Jogador %s bloqueado!\n", this.jogadores.get(i).getCor());
                        System.out.print("+----------------------------------------------------------+\n");
                        break;
                    } else {
                        if (!(this.jogadores.indexOf(jogadores.get(i)) == indexDebug)) {
                            this.jogadores.get(i).jogar();
                            this.jogadores.get(i).setQtdJogadas(this.jogadores.get(i).getQtdJogadas() + 1);
                            System.out.printf("| O jogador %s %s jogou!\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getTipoJogador());
                            System.out.printf("| Dado 01: %d, Dado 02: %d\n", this.jogadores.get(i).getDado01(), this.jogadores.get(i).getDado02());
                            System.out.printf("| Valor da Soma dos Dados: %d\n", this.jogadores.get(i).getSomaDados());
                        } else {
                            System.out.printf("| O jogador %s %s não jogou, foi debugado!\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getTipoJogador());
                            this.indexDebug = 7;
                        }
                    }

                    if (this.jogadores.get(i).getCasaAtual() >= 40) {
                        this.jogadores.get(i).setCasaAtual(40);
                        this.existeVencedor = true;
                        this.vencedor = this.jogadores.get(i);
                        System.out.printf("| jogador %s chegou na última casa: %d\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                        System.out.print("+----------------------------------------------------------+\n");
                        break;
                    }

                    System.out.printf("| jogador %s casa atual: %d\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());

                    // Casa Especial Bloqueio
                    if (this.jogadores.get(i).getCasaAtual() == 10 || this.jogadores.get(i).getCasaAtual() == 25 || this.jogadores.get(i).getCasaAtual() == 38) {
                        System.out.printf("| Jogador %s caiu na casa especial de Bloqueio\n", this.jogadores.get(i).getCor());
                        this.indexBlock = jogadores.indexOf(jogadores.get(i));

                    }
                    // Casa Especial Mudança de Tipo
                    if (this.jogadores.get(i).getCasaAtual() == 13) {
                        System.out.printf("| Jogador %s caiu na casa especial de Troca de Tipo\n", this.jogadores.get(i).getCor());
                        Random gerador = new Random();
                        int carta = gerador.nextInt(1,4);


                        System.out.printf("| Jogador %s puxou a carta número %d\n", this.jogadores.get(i).getCor(), carta);

                        if (carta == 1) {
                            Jogador trocado1 = new JogadorNormal(this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                            trocado1.setQtdJogadas(this.jogadores.get(i).getQtdJogadas());
                            this.jogadores.set(i, trocado1);
                        }

                        if (carta == 2) {
                            Jogador trocado2 = new JogadorSortudo(this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                            trocado2.setQtdJogadas(this.jogadores.get(i).getQtdJogadas());
                            this.jogadores.set(i, trocado2);
                        }

                        if (carta == 3 ) {
                            Jogador trocado3 = new JogadorAzarado(this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                            trocado3.setQtdJogadas(this.jogadores.get(i).getQtdJogadas());
                            this.jogadores.set(i, trocado3);
                        }

                        System.out.printf("| Jogador %s mudou para %s\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getTipoJogador());
                    }

                    // Casa Especial Avançar Casas
                    if (this.jogadores.get(i).getCasaAtual() == 5 || this.jogadores.get(i).getCasaAtual() == 15 || this.jogadores.get(i).getCasaAtual() == 30) {
                        System.out.printf("| Jogador %s caiu na casa especial de Avançar 3 casas\n", this.jogadores.get(i).getCor());

                        if (!this.jogadores.get(i).getTipoJogador().equals("Azarado")) {
                            this.jogadores.get(i).setCasaAtual((this.jogadores.get(i).getCasaAtual() + 3));
                            System.out.printf("| Jogador %s  mudou para casa atual: %d\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                        } else {
                            System.out.printf("| Não mudou, pois Jogador %s é Azarado casa atual: %d\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                        }

                    }
                    // Casa Especial Regresso
                    if (this.jogadores.get(i).getCasaAtual() == 17 || this.jogadores.get(i).getCasaAtual() == 27) {

                        System.out.printf("| Jogador %s caiu na casa especial de Regresso\n", this.jogadores.get(i).getCor());
                        String corEscolhida;
                        boolean jogadorEncontrado;

                        do{
                            System.out.print("| Digite a cor do Jogador Escolhido: ");
                            corEscolhida = teclado.nextLine();
                            jogadorEncontrado = false;

                            if (this.jogadores.get(i).getCor().equals(corEscolhida)) {
                                System.out.println("| Jogador Não Pode escolher ele mesmo, escolha outro jogador");
                            }else {
                                for (int j = 0; j < this.jogadores.size(); j++) {
                                    if (this.jogadores.get(j).getCor().equals(corEscolhida)) {
                                        this.jogadores.get(j).setCasaAtual(0);
                                        System.out.printf("| Jogador %s tem agora casa atual: %d\n", this.jogadores.get(j).getCor(), this.jogadores.get(j).getCasaAtual());
                                        jogadorEncontrado = true;
                                        break;
                                    }
                                }

                                if (!jogadorEncontrado) {
                                    System.out.println("| Jogador Não Encontrado");
                                }
                            }
                        }while (!jogadorEncontrado);
                    }
                    // Casa Especial Mudança de Casa
                    if (this.jogadores.get(i).getCasaAtual() == 20 || this.jogadores.get(i).getCasaAtual() == 35) {
                        System.out.printf("| Jogador %s caiu na casa especial: Trocar com Menor Casa\n", this.jogadores.get(i).getCor());
                        int menorCasa = 41;
                        int indMenor = 0;

                        for (int j = 0; j < this.jogadores.size(); j++) {
                            if (this.jogadores.get(j).getCasaAtual() < menorCasa) {
                                menorCasa = this.jogadores.get(j).getCasaAtual();
                                indMenor = j;
                            }
                        }

                        int posicaoAtual = this.jogadores.get(i).getCasaAtual();
                        this.jogadores.get(i).setCasaAtual(menorCasa);
                        this.jogadores.get(indMenor).setCasaAtual(posicaoAtual);

                        System.out.printf("| Jogador %s tem agora casa atual: %d\n", this.jogadores.get(i).getCor(), this.jogadores.get(i).getCasaAtual());
                        System.out.printf("| jogador %s tem agora casa atual: %d\n", this.jogadores.get(indMenor).getCor(), this.jogadores.get(indMenor).getCasaAtual());

                    }

                    if (jogadores.isEmpty()){
                        System.out.println("Nenhum jogador jogou nessa rodada!");
                    }

                    System.out.print("+----------------------------------------------------------+\n");

                } while (this.jogadores.get(i).getResultadosIguais());

            }
        }

        if (jogadores.isEmpty()){
            System.out.println("| Nenhum jogador jogou nessa rodada!");
        }
    }

    public boolean getExisteVencedor() {
        return existeVencedor;
    }

    public Jogador getVencedor(){
        return vencedor;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
}
