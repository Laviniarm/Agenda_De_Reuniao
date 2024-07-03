package modelo;

import java.util.ArrayList;

public class Reuniao {
	private int id;
	private String data;
	private String assunto;
	private ArrayList <Pessoa> pessoas = new ArrayList<>();
	
	public Reuniao(int id, String data, String assunto) {
		this.id = id;
		this.data = data;
		this.assunto = assunto;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public ArrayList<String> getNomesPessoas() {
		ArrayList<String> nomespessoas = new ArrayList<>();
		for (Pessoa p : pessoas)
			nomespessoas.add(p.getNome());
		return nomespessoas;
	}

	public void addPessoa(Pessoa pessoa) {
			pessoas.add(pessoa);
	}


	@Override
	public String toString() {
		return "Reuniao [id=" + id + ", data=" + data + ", assunto=" + assunto + ", pessoas=" + getNomesPessoas() + "]";
	}

}
