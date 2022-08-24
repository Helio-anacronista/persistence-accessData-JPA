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
            clientes.salvar(new Cliente("Primerio-Cliente"));
            clientes.salvar(new Cliente("Segundo-Cliente"));

            java.util.List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

        };
    }



    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}