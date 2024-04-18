package modelo;

import java.util.ArrayList;

public class Pessoa {
 private String nome;
 private ArrayList<Reuniao> reunioes = new ArrayList<>();
 
 public Pessoa(String nome) {
	 this.nome = nome;
 }
 
 public String getNome() {
	 return nome;
 }
 
 public void setNome(String nome) {
		this.nome = nome;
	}

public ArrayList<Reuniao> getReuniao() {
	return reunioes;
}

public void adicionar(Reuniao a){
	reunioes.add(a);
}

public void remover(Reuniao a){
	reunioes.remove(a);
}

@Override
public String toString() {
	return "Pessoa [nome=" + nome + ", reunioes=" + reunioes + "]";
}

 
}
