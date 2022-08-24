package io.github.helio.domain.repositorio;

import io.github.helio.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    //Script do jpa
    private static String INSERT = "insert into cliente (nome) values (?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //Salvar os cliente e injetar
    public Cliente salvar(Cliente cliente)  {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente  atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[] {cliente.getNome(), cliente.getId()});
        return cliente;
    }

    //Delete por id
    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }
    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    //Buscar por nome
    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ? "),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }



    //Imprimir na classe Cliente os cliente obtidos que foram salvado no bando de dados
    public List<Cliente> obterTodos()  {
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private static RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

}
