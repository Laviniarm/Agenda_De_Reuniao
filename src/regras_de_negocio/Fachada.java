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
    private Fachada() {
    }

    private static DAOPessoa daoPessoa = new DAOPessoa();
    private static DAOReuniao daoReuniao = new DAOReuniao();

    public static void inicializar() {
        DAO.open();
    }

    public static void finalizar() {
        DAO.close();
    }

    public static Pessoa criarPessoa(String nome)throws Exception {
        DAO.begin();
        try {
            Pessoa pessoaExist = daoPessoa.read(nome);
            if (pessoaExist != null) {
                throw new Exception("Pessoa já existe");
            }
            Pessoa pessoa = new Pessoa(nome);
            daoPessoa.create(pessoa);
            DAO.commit();
            return pessoa;

        } catch (Exception e) {
            throw e;
        }
    }

    public static void criarReuniao(int id, String data, String assunto, ArrayList<String> nomesPessoas) throws Exception {
        DAO.begin();

        try {
            LocalDate dt = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate hoje = LocalDate.now();
            if (dt.isBefore(hoje)) {
                throw new Exception("A data não pode ser anterior a de hoje.");
            }
        } catch (DateTimeParseException e) {
            throw new Exception("Formato de data inválido: " + data);
        }

        if (nomesPessoas.size() < 2) {
            throw new Exception("Uma reunião deve ter no mínimo 2 pessoas.");
        }

        Reuniao reuniao = new Reuniao(id, data, assunto);

        for (String nome : nomesPessoas) {
            Pessoa p = daoPessoa.read(nome);

            if (p == null) {
                throw new Exception("Pessoa " + nome + " não encontrada.");
            }

            for (Reuniao r : p.getReuniao()) {
                if (r.getData().equals(data)) {
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

    public static void alterarAssuntoReuniao(int idReuniao, String novoAssunto) throws Exception {
        DAO.begin();

        try {
            Reuniao reuniao = daoReuniao.read(idReuniao);
            if (reuniao == null) {
                throw new Exception("Reunião não encontrada.");
            }
            reuniao.setAssunto(novoAssunto);
            daoReuniao.update(reuniao);
            DAO.commit();

        } catch (Exception e) {
            throw e;
        }

    }

    public static void alterarNomePessoa(String nome, String novoNome) throws Exception {
        DAO.begin();
        try {
            Pessoa pessoa = daoPessoa.read(nome);
            if (pessoa == null) {
                throw new Exception("Pessoa não encontrada");
            }
            pessoa.setNome(novoNome);
            daoPessoa.update(pessoa);
            DAO.commit();
        } catch (Exception e) {
            throw e;
        }

    }
    public static void deletarReuniao(int id) throws Exception {
        DAO.begin();

        try {
            Reuniao reuniao = daoReuniao.read(id);
            if (reuniao == null) {
                throw new Exception("Reunião não encontrada.");
            }
            for (Pessoa pessoa : reuniao.getPessoas()) {
                pessoa.remover(reuniao);
                daoPessoa.update(pessoa);
            }

            daoReuniao.delete(reuniao);
            DAO.commit();

        } catch (Exception e) {
            throw e;
        }
    }
    public static void deletarPessoa(String nome) throws Exception {
        DAO.begin();
        try {
            Pessoa pessoa = daoPessoa.read(nome);
            if (pessoa == null ) {
                throw new Exception("Pessoa não encontrada");
            }
            if (!pessoa.getReuniao().isEmpty()) {
                throw new Exception("Não é possível deletar a pessoa, pois ela está participando de uma ou mais reuniões.");
            }

            daoPessoa.delete(pessoa);
            DAO.commit();
        } catch (Exception e) {
            throw e;
        }

    }

        public static List<Pessoa> listarPessoas () {
            return daoPessoa.readAll();
        }

        public static List<Reuniao> listarReunioes () {
            return daoReuniao.readAll();
        }

        public static List<Reuniao> consultarReunioes (String data){
            return daoReuniao.reunioesNaData(data);
        }
        public static List<Reuniao> reuniaosComPessoa (String nome){
            return daoReuniao.reunioesComPessoa(nome);
        }
        public static List<Pessoa> pessoasComMaisDeNReunioes ( int n){
            return daoReuniao.pessoasComMaisDeNReunioes(n);
        }
    }

