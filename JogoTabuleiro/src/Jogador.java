public abstract class Jogador {

    protected String cor;
    protected int casaAtual;
    protected int dado01;
    protected int dado02;
    protected int somaDados = 0;
    protected boolean resultadosIguais = false;
    protected int qtdJogadas = 0;
    protected String tipoJogador = "Vazio";

    public Jogador(String cor, int casaAtual) {
        this.cor = cor;
        this.casaAtual = casaAtual;
    }

    public abstract void jogar();

    public String getCor() {
        return cor;
    }

    public int getCasaAtual() {
        return casaAtual;
    }

    public void setCasaAtual(int casaAtual) {
        this.casaAtual = casaAtual;
    }

    public int getSomaDados() {
        return somaDados;
    }

    public boolean getResultadosIguais() {
        return resultadosIguais;
    }

    public void setResultadosIguais(boolean resultadosIguais) {
        this.resultadosIguais = resultadosIguais;
    }

    public int getQtdJogadas() {
        return qtdJogadas;
    }

    public void setQtdJogadas(int qtdJogadas) {
        this.qtdJogadas = qtdJogadas;
    }

    public String getTipoJogador() {
        return tipoJogador;
    }

    public void setTipoJogador(String tipoJogador) {
        this.tipoJogador = tipoJogador;
    }

    public int getDado01() {
        return dado01;
    }

    public void setDado01(int dado01) {
        this.dado01 = dado01;
    }

    public int getDado02() {
        return dado02;
    }

    public void setDado02(int dado02) {
        this.dado02 = dado02;
    }
}   