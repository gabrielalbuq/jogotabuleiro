public class JogadorSortudo extends Jogador {

    public JogadorSortudo(String cor, int casaAtual) {
        super(cor, casaAtual);
        super.tipoJogador = "Sortudo";
    }

    @Override
    public void jogar() {

        do {
            this.setDado01 ((int) (Math.random() * 6 + 1));
            this.setDado02 ((int) (Math.random() * 6 + 1));

        } while(this.getDado01() + this.getDado02() < 7);
        this.somaDados = this.getDado01() + this.getDado02();

        if (this.getDado01() == this.getDado02()) {
            resultadosIguais = true;
        }

        this.casaAtual += this.somaDados;
    }
}