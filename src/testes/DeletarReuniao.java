package testes;

import modelo.Reuniao;
import regras_de_negocio.Fachada;

public class DeletarReuniao {
    public DeletarReuniao() {
      try {
        Fachada.inicializar();

        System.out.println("Listagem de reuniões antes da exclusão:");
        for(Reuniao r: Fachada.listarReunioes())
            System.out.println(r);

        System.out.println("\nDeletando a reunião com ID 1...");
        Fachada.deletarReuniao(1);

        System.out.println("\nListagem de reuniões após a exclusão:");
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
    new DeletarReuniao();
}
}
