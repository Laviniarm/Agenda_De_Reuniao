package daodb4o;

import com.db4o.query.Query;
import modelo.Pessoa;

public class DAOPessoa extends DAO<Pessoa> {

	public Pessoa read(Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("nome").constrain(nome);
		return (Pessoa) q.execute().get(0);
	}
}
