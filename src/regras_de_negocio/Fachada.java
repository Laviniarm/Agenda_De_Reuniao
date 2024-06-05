package regras_de_negocio;

import java.util.Date;
import java.util.List;

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

}
