package testes;


import modelo.Pessoa;
import regras_de_negocio.Fachada;

public class AlterarNomePessoa {
    public AlterarNomePessoa() {
        try {
            Fachada.inicializar();
            System.out.println("Alterando o nome da pessoa 'Lavinia' para 'Lavinia Morais'...");
            Fachada.alterarNomePessoa("Lavinia", "Lavinia Morais");

            System.out.println("Listagem de pessoas após alteração:");
            for(Pessoa p : Fachada.listarPessoas())
                System.out.println(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nfim do programa");
    }

    // =================================================
    public static void main(String[] args) {
        new AlterarNomePessoa();
    }
}
