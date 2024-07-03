package regras_de_negocio;

import java.util.ArrayList;  
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import daodb4o.DAO;
import daodb4o.DAOPessoa;
import daodb4o.DAOReuniao;
import modelo.Pessoa;
import modelo.Reuniao;


public class Fachada {
	private Fachada() {}
	
	private static DAOPessoa daoPessoa = new DAOPessoa();
    private static DAOReuniao daoReuniao = new DAOReuniao();
    
    public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}
	
	public static Pessoa criarPessoa(String nome){
		DAO.begin();

		Pessoa pessoa = new Pessoa(nome);
		daoPessoa.create(pessoa);
		DAO.commit();
		return pessoa;
	}

    public static void criarReuniao(int id, String data, String assunto, ArrayList<String> nomesPessoas) throws Exception{
    	DAO.begin();
    	
    	try {
            LocalDate dt = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate hoje = LocalDate.now();
            if (dt.isBefore(hoje)) {
                throw new Exception("A data não pode ser anterior a de hoje.");
            }
        } catch (DateTimeParseException e) {
            DAO.rollback();
            throw new Exception("Formato de data inválido: " + data);
        }
    	
        if (nomesPessoas.size() < 2) {
        	DAO.rollback();
            throw new Exception("Uma reunião deve ter no mínimo 2 pessoas.");
        }
        
        Reuniao reuniao = new Reuniao(id, data, assunto);

        for (String nome : nomesPessoas) {
            Pessoa p = daoPessoa.read(nome);

            if (p == null) {
                DAO.rollback();
                throw new Exception("Pessoa " + nome + " não encontrada.");
            }

            for (Reuniao r : p.getReuniao()) {
                if (r.getData().equals(data)) {
                    DAO.rollback();
                    throw new Exception("A pessoa " + p.getNome() + " já está participando de outra reunião ao mesmo tempo.");
                }
            }
            reuniao.addPessoa(p);
        }

        daoReuniao.create(reuniao);
        for (Pessoa pessoa : reuniao.getPessoas()) {
            pessoa.adicionar(reuniao);
            daoPessoa.update(pessoa);
        }
        DAO.commit();
    }
    
    public static List<Pessoa> listarPessoas() {
    	return daoPessoa.readAll();
	}

    public static List<Reuniao> listarReunioes() {
    	return daoReuniao.readAll();
    }
    
    public static List<Reuniao> consultarReunioes(String data) {
        return daoReuniao.reunioesNaData(data);
    }
}
