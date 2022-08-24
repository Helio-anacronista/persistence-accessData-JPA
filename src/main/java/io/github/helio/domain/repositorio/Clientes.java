package io.github.helio.domain.repositorio;

import io.github.helio.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    @Autowired
    private EntityManager entityManager;

    //Salvar
    @Transactional
    public Cliente salvar(Cliente cliente)  {
        entityManager.persist(cliente);
        return cliente;
    }

    //Atualizar
    @Transactional
    public Cliente  atualizar(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    // deletar por id ou cliente
    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar(Integer id) {
       Cliente cliente = entityManager.find(Cliente.class, id);
       deletar(cliente);
    }


    //Buscar por tal
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        String jpql = " select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }


    //lista todos
    @Transactional(readOnly = true)
    public List<Cliente> obterTodos()  {
        return entityManager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
}
