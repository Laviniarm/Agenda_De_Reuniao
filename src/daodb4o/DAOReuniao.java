package daodb4o;

import java.util.List;
import com.db4o.query.Query;

import modelo.Pessoa;
import modelo.Reuniao;

public class DAOReuniao extends DAO<Reuniao> {
	public Reuniao read(Object chave) {
		int id = (Integer) chave;
		Query query = manager.query();
		query.constrain(Reuniao.class);
		query.descend("id").constrain(id);
		return (Reuniao) query.execute().get(0);
	}

	public List<Reuniao> reunioesNaData(String data) {
		Query query = manager.query();
		query.constrain(Reuniao.class);
		query.descend("data").constrain(data);
		List<Reuniao> resultados = query.execute();
		if (resultados.size()>0)
			return resultados;
		else
			return null;
	}

	public List<Reuniao> reunioesComPessoa(String nomePessoa) {
		Query query = manager.query();
		query.constrain(Reuniao.class);
		query.descend("pessoas").descend("nome").constrain(nomePessoa);
		return query.execute();
	}

	public List<Pessoa> pessoasComMaisDeNReunioes(int n) {
		Query query = manager.query();
		query.constrain(Pessoa.class);
		query.descend("reunioes").constrain(n).greater();
		return query.execute();
	}

}
