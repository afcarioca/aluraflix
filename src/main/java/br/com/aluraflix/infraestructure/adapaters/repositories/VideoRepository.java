package br.com.aluraflix.infraestructure.adapaters.repositories;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.aluraflix.domain.Video;
import br.com.aluraflix.domain.ports.repositories.VideoRepositoryPort;
import br.com.aluraflix.infraestructure.adapaters.entities.VideoEntity;
import jakarta.transaction.Transactional;

@Component
public class VideoRepository implements VideoRepositoryPort {

    private final SpringVideoRepository springVideoRepository;

    public VideoRepository(SpringVideoRepository springVideoRepository){
        this.springVideoRepository = springVideoRepository;
    }

    @Override
    public Optional<?> findByUrl(String url) {
       return this.springVideoRepository.findByUrl(url);
    }

    

    @Override
    @Transactional
    public Video save(Video video) throws SQLException{
      VideoEntity newEntity = this.springVideoRepository.save(new VideoEntity(video));
      return new Video(newEntity);
    }

    
}
