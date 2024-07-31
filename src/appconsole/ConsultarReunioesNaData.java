package appconsole;

import modelo.Reuniao;
import regras_de_negocio.Fachada;

public class ConsultarReunioesNaData {
	public ConsultarReunioesNaData(){
		
		try {
			Fachada.inicializar();
			System.out.println("Reunioes na data 01/10/2024:");
			var reunioes = Fachada.consultarReunioes("01/10/2024");
			for(Reuniao r : reunioes)
				System.out.println(r);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}


	//=================================================
	public static void main(String[] args) {
		new ConsultarReunioesNaData();
	}
}

