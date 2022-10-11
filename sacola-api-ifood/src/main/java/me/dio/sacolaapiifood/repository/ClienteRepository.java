package me.dio.sacolaapiifood.repository;

import me.dio.sacolaapiifood.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Responsavel por interagir com o banco de dados
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
