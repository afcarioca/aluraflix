package br.com.aluraflix.infraestructure.adapaters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aluraflix.infraestructure.adapaters.entities.VideoEntity;

@Repository
public interface SpringVideoRepository extends JpaRepository<VideoEntity, Long> {
    @Query("SELECT url FROM VideoEntity v WHERE v.url LIKE ?1 ORDER BY v.id LIMIT 1")
    public Optional<VideoEntity> findByUrl(String url);

    @Query("SELECT url FROM VideoEntity v WHERE v.url LIKE ?2 AND v.id != ?1 ORDER BY v.id LIMIT 1")
    public Optional<VideoEntity> findByUrl(Long id, String url);

    
    @Query("SELECT v FROM VideoEntity v WHERE v.status != 0 ")
    public List<VideoEntity> findAll();

    @Query("SELECT v FROM VideoEntity v WHERE v.id = ?1 AND v.status = ?2")
    public Optional<VideoEntity> findById(Long id, int status);

}
