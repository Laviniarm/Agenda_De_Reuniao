package testes;

import modelo.Reuniao;
import regras_de_negocio.Fachada;

public class AlterarAssuntoReuniao {

    public AlterarAssuntoReuniao() {

        try {
            Fachada.inicializar();
            System.out.println("Alterando o assunto da reunião com ID 1...");
            Fachada.alterarAssuntoReuniao(1, "Novo Assunto");

            System.out.println("\n Listagem de reuniões:");
            for(Reuniao r: Fachada.listarReunioes())
                System.out.println(r);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Fachada.finalizar();
        System.out.println("\nfim do programa");
    }

    // =================================================
    public static void main(String[] args) {
        new AlterarAssuntoReuniao();
    }
}

