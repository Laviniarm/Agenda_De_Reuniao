package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Pessoa;
import modelo.Reuniao;



public class Util {
	
	private static ObjectContainer manager=null;
	
	
	public static ObjectContainer conectarBanco() {
		if (manager != null)
			return manager;	
	
	
	EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
	config.common().messageLevel(0);  
	
	
	config.common().objectClass(Pessoa.class).cascadeOnDelete(false);;
	config.common().objectClass(Pessoa.class).cascadeOnUpdate(true);;
	config.common().objectClass(Pessoa.class).cascadeOnActivate(true);
	config.common().objectClass(Reuniao.class).cascadeOnDelete(false);;
	config.common().objectClass(Reuniao.class).cascadeOnUpdate(true);;
	config.common().objectClass(Reuniao.class).cascadeOnActivate(true);
	// indexacao de atributos
	config.common().objectClass(Pessoa.class).objectField("nome").indexed(true);
	config.common().objectClass(Reuniao.class).objectField("id").indexed(true);

	//conexao local
	manager = Db4oEmbedded.openFile(config, "banco.db4o");
	return manager;
}



public static void desconectar() {
	if(manager!=null) {
		manager.close();
		manager=null;
	}
}

}
