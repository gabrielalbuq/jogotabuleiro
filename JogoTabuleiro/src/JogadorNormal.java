public class JogadorNormal extends Jogador {

    public JogadorNormal(String cor, int casaAtual) {
        super(cor, casaAtual);
        super.tipoJogador = "Normal";
    }

    public void jogar(){
        this.setDado01((int) (Math.random() * 6 + 1));
        this.setDado02((int) (Math.random() * 6 + 1));


        if (this.getDado01() == this.getDado02()) {
            resultadosIguais = true;
        }

        this.somaDados = this.getDado01() + this.getDado02();
        this.casaAtual += this.somaDados;
    }
}