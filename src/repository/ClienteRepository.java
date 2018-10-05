package repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cliente;

public class ClienteRepository {

	private EntityManager entityManeger;

	public ClienteRepository(EntityManager entityManager) {
		this.entityManeger = entityManager;
	}

	public Cliente save(Cliente cliente) {

		return getEntityManeger().merge(cliente);
	}

	public void remove(Cliente cliente) {
		cliente = getEntityManeger().merge(cliente);
		getEntityManeger().remove(cliente);
	}

	// metodo para retormart uma lista d clientes

	public List<Cliente> getClientes(String nome) {

		Query query = getEntityManeger()
				.createQuery("SELECT c FROM Cliente c WHERE lower(c.nome)  like lower( :nome )");
		query.setParameter("nome", "%" + nome + "%");

		List<Cliente> lista = query.getResultList();
		if (lista == null) {
			lista = new ArrayList<Cliente>();
		}
		return lista;
	}

	public EntityManager getEntityManeger() {
		return entityManeger;
	}

	public void setEntityManeger(EntityManager entityManeger) {
		this.entityManeger = entityManeger;
	}

}
