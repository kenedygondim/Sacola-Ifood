package me.dio.sacola.repository;

import me.dio.sacola.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JPARepository possui diversos métodos de CRUD
@Repository                                        //Classe que queremos acessar, tipo do Id
public interface SacolaRepository extends JpaRepository<Sacola, Long> { }
