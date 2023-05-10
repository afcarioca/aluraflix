package br.com.aluraflix.domain.adapters.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import br.com.aluraflix.domain.Video;
import br.com.aluraflix.domain.ports.interfaces.VideoServicePort;
import br.com.aluraflix.domain.ports.repositories.VideoRepositoryPort;

public class VideoServiceImpl implements VideoServicePort {

    private final VideoRepositoryPort repositoryPort;

    public VideoServiceImpl( VideoRepositoryPort repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<?> findByUrl(String url) {
        return this.repositoryPort.findByUrl(url);
    }

    @Override
    public Video save(Video video) throws SQLException {
        return this.repositoryPort.save(video);
    }

    @Override
    public List<Video> getAll() {
        return this.repositoryPort.getAll();
    }
    
}
