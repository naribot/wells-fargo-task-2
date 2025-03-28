package com.wellsfargo.counselor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.wellsfargo.counselor.repository.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.wellsfargo.counselor.entity.*;

import java.util.Arrays;

@SpringBootApplication
public class Entrypoint {

    public static void main(String[] args) {
        SpringApplication.run(Entrypoint.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(
            AdvisorRepository advisorRepo,
            ClientRepository clientRepo,
            PortfolioRepository portfolioRepo,
            SecurityRepository securityRepo
    )
    {
        return (args) -> {
            // Create and save advisor
            Advisor advisor = new Advisor("Alice", "Johnson", "123 Wall St", "555-9876", "alice.j@email.com");
            advisorRepo.save(advisor);

            // Create and save client
            Client client = new Client("Bob", "Miller", "456 Main Ave", "555-1111", "bob.m@email.com", advisor);
            clientRepo.save(client);

            // Create and save
            Portfolio portfolio = new Portfolio("2025-03-27", client);
            portfolioRepo.save(portfolio);

            // Create and save securities
            Security s1 = new Security("AAPL", "Tech", "2023-06-01", 150.00, 10, portfolio);
            Security s2 = new Security("GOOGL", "Tech", "2023-07-15", 2700.00, 5, portfolio);
            securityRepo.saveAll(Arrays.asList(s1, s2));

            System.out.println("Test data inserted successfully!");
        };
        
    }

}
