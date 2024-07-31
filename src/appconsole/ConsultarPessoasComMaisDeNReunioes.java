package appconsole;

import modelo.Pessoa;
import regras_de_negocio.Fachada;

public class ConsultarPessoasComMaisDeNReunioes {
    public ConsultarPessoasComMaisDeNReunioes() {
        try {
            Fachada.inicializar();
            System.out.println("Iniciando consulta de pessoas com mais de 2 reuniões...");

            System.out.println("Pessoas com mais de 2 reuniões:");
            var pessoas = Fachada.pessoasComMaisDeNReunioes(2);
            for (Pessoa p : pessoas) {
                System.out.println(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nfim do programa");
    }

    // =================================================
    public static void main(String[] args) {
        new ConsultarPessoasComMaisDeNReunioes();
    }
}
