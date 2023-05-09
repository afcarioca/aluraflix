package br.com.aluraflix.domain.ports.repositories;

import java.sql.SQLException;
import java.util.Optional;

import br.com.aluraflix.domain.Video;


public interface VideoRepositoryPort {
    
    public Optional<?> findByUrl(String url);
    public Video save(Video video) throws SQLException;
}
