package factory;//nome do pacote 

//importações necessarias

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//classe jpafactory
public class JPAFactory {
//construtor privado para garantir que nunca sera instanciada
	private JPAFactory() {
	}
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrudClienteJavaFX");
	public static EntityManager geEntityManager() {
		return emf.createEntityManager();
	}
}
