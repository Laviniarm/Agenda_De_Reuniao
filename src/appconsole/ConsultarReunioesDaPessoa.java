package appconsole;

import modelo.Reuniao;
import regras_de_negocio.Fachada;

public class ConsultarReunioesDaPessoa {
    public ConsultarReunioesDaPessoa() {
        try {
            Fachada.inicializar();
            System.out.println("Reuniões com a pessoa de nome Raiza:");
            var reunioes = Fachada.reuniaosComPessoa("Raiza");
            for (Reuniao r : reunioes) {
                System.out.println(r);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nfim do programa");
    }

    // =================================================
    public static void main(String[] args) {
        new ConsultarReunioesDaPessoa();
    }
}
