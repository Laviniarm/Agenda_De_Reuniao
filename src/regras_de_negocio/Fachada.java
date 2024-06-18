package regras_de_negocio;

import java.util.ArrayList;
import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOPessoa;
import daodb4o.DAOReuniao;
import modelo.Pessoa;
import modelo.Reuniao;


public class Fachada {
	private DAOPessoa daoPessoa;
    private DAOReuniao daoReuniao;

    public Fachada() {
        daoPessoa = new DAOPessoa();
        daoReuniao = new DAOReuniao();
    }
    public void criarPessoa(String nome){
        daoPessoa.open();
        Pessoa pessoa = new Pessoa(nome);
        daoPessoa.create(pessoa);
        DAO.commit();
        DAO.close();
    }

    public void criarReuniao(int id, String data, String assunto, ArrayList<Pessoa> nomesPessoas) throws Exception{

        if (nomesPessoas.size() < 2) {
            throw new Exception("Uma reunião deve ter no mínimo 2 pessoas.");
        }

        daoReuniao.open();
        daoPessoa.open();

        Reuniao reuniao = new Reuniao(id, data, assunto);

        for (Pessoa pessoa : nomesPessoas) {
            Pessoa p = daoPessoa.read(pessoa.getNome()); // Buscar a pessoa pelo nome

            if (p == null) {
                DAO.close();
                throw new Exception("Pessoa " + pessoa.getNome() + " não encontrada.");
            }

            for (Reuniao r : p.getReuniao()) {
                if (r.getData().equals(data)) {
                    DAO.close();
                    throw new Exception("A pessoa " + p.getNome() + " já está participando de outra reunião ao mesmo tempo.");
                }
            }

            reuniao.addPessoa(p);
        }

        // Salva a reunião e atualiza as pessoas
        daoReuniao.create(reuniao);
        for (Pessoa pessoa : reuniao.getPessoas()) {
            pessoa.adicionar(reuniao);
            daoPessoa.update(pessoa);
        }

        DAO.commit();
        DAO.close();
    }

    public List<Pessoa> listarPessoas() {
        daoPessoa.open();
        List<Pessoa> pessoas = daoPessoa.readAll();
        DAO.close();
        return pessoas;
    }

    public List<Reuniao> listarReunioes() {
        daoReuniao.open();
        List<Reuniao> reunioes = daoReuniao.readAll();
        DAO.close();
        return reunioes;
    }
}
