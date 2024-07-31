package testes;

import modelo.Pessoa;
import regras_de_negocio.Fachada;

public class DeletarPessoa {
    public DeletarPessoa() {
        try {
            Fachada.inicializar();

            System.out.println("Deletando a pessoa 'Lucas'...");
            Fachada.deletarPessoa("Lucas");

            System.out.println("Listagem de pessoas após deleção:");
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
        new DeletarPessoa();
    }
}
