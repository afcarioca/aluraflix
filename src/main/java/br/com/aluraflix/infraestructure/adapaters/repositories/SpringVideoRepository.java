package br.com.aluraflix.infraestructure.adapaters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aluraflix.infraestructure.adapaters.entities.VideoEntity;

@Repository
public interface SpringVideoRepository extends JpaRepository<VideoEntity, Long> {
    @Query("SELECT url FROM VideoEntity v WHERE v.url LIKE ?1 ORDER BY v.id LIMIT 1")
    public Optional<VideoEntity> findByUrl(String url);
    

}
