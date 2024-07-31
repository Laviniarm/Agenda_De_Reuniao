package daodb4o;

import com.db4o.query.Query;

import modelo.Pessoa;

import java.util.List;

public class DAOPessoa extends DAO<Pessoa> {

	public Pessoa read(Object chave) {
		String nome = (String) chave;
	    Query q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("nome").constrain(nome);
		List<Pessoa> resultados = q.execute();
		return resultados.isEmpty() ? null : resultados.get(0);
	}
}
