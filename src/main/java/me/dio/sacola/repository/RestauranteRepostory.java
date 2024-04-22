package me.dio.sacola.repository;
import me.dio.sacola.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepostory extends JpaRepository<Restaurante, Long> { }
