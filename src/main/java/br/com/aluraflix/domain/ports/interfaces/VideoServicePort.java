package br.com.aluraflix.domain.ports.interfaces;

import java.sql.SQLException;
import java.util.Optional;

import br.com.aluraflix.domain.Video;

public interface VideoServicePort {
    
    public Optional<?> findByUrl(String url);
    public Video save(Video video) throws SQLException;
}
