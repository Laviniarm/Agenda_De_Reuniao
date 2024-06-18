package daodb4o;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public void open() {
		manager = Util.conectarBanco();
	}

	public static void close() {
		Util.desconectar();
	}

	public void create(T obj) {
		manager.store(obj);
	}

	public abstract T read(Object chave); // sobrescrito nas subclasses

	public T update(T obj) {
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		manager.ext().purge(); // limpar cache do manager

		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}

	@SuppressWarnings("unchecked")
	// deletar todos objetos de um tipo (e subtipo)
	public void deleteAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		Query q = manager.query();
		q.constrain(type);
		for (Object t : q.execute()) {
			manager.delete(t);
		}
	}

	// --------transacao---------------
	public static void begin() {
	} // tem que ser vazio

	public static void commit() {
		manager.commit();
	}

	public static void rollback() {
		manager.rollback();
	}

}
