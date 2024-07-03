package appconsole;

import java.util.ArrayList;
import java.util.Arrays;

import regras_de_negocio.Fachada;

public class Cadastrar {
	public Cadastrar(){
		try {
			System.out.println("cadastrando pessoas...");
			Fachada.inicializar();
			
			Fachada.criarPessoa("Raiza");
			Fachada.criarPessoa("Lavinia");
			Fachada.criarPessoa("Lucas");
			Fachada.criarPessoa("Heitor");
			
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando reuniões...");
			String[] nomesArray = {"Raiza", "Lavinia"};
	        ArrayList<String> nomesPessoas = new ArrayList<>(Arrays.asList(nomesArray));
			Fachada.criarReuniao(1, "01/10/2024", "Projeto de POB", nomesPessoas);
			
			String[] nomesArray2 = {"Lucas", "Heitor"};
	        ArrayList<String> nomesPessoas2 = new ArrayList<>(Arrays.asList(nomesArray2));
			Fachada.criarReuniao(2, "17/10/2024", "Aniversário de Lucas", nomesPessoas2);
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("Cadastrado com sucesso!");
	}

	
	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


