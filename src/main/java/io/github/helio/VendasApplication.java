package io.github.helio;

import io.github.helio.domain.entity.Cliente;
import io.github.helio.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;



@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.salvar(new Cliente("Cliente-Helio"));
            clientes.salvar(new Cliente("Cliente-Thiago"));

            java.util.List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            System.out.println("Buscando clientees");
            clientes.buscarPorNome("Heli").forEach(System.out::println);


//            System.out.println("Delentado clientes");
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });
            todosClientes = clientes.obterTodos();
            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum Cliente encontrado");
            } else {
                todosClientes.forEach(System.out::println);
            }



        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}