package br.com.aluraflix.infraestructure.adapaters.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.aluraflix.domain.Video;
import br.com.aluraflix.domain.ports.repositories.VideoRepositoryPort;
import br.com.aluraflix.infraestructure.adapaters.entities.VideoEntity;
import br.com.aluraflix.interceptor.VideoExistenceException;
import jakarta.transaction.Transactional;

@Component
public class VideoRepository implements VideoRepositoryPort {

    private final SpringVideoRepository springVideoRepository;

    public VideoRepository(SpringVideoRepository springVideoRepository){
        this.springVideoRepository = springVideoRepository;
    }

    @Override
    public Optional<?> findByUrl(String url) {
       
      boolean condition = this.springVideoRepository.findByUrl(url).isPresent();
      Optional.of(!condition)
        .filter(c -> c)
        .orElseThrow(() -> new VideoExistenceException("Vídeo já cadastrado"));
        
      return this.springVideoRepository.findByUrl(url);
    }

    @Override
    @Transactional
    public Video save(Video video) throws SQLException{
      VideoEntity entity = this.springVideoRepository.save(new VideoEntity(video));
      
      return new Video(entity);
    }

    @Override
    public List<Video> getAll() {
      
      List<VideoEntity> videosEntity = this.springVideoRepository.findAll();
      List<Video> videos = new ArrayList<>();
      
      for(var entity: videosEntity){
          videos.add(new Video(entity)); 
      }
      
      return videos;       
    }

    @Override
    public Video getOne(Long id) {
      VideoEntity videoEntity = this.springVideoRepository.findById(id).get();
      return new Video(videoEntity);
    }

    @Override
    @Transactional
    public Long update(Long id, Video video) throws SQLException {
     
      VideoEntity videoEntity = this.springVideoRepository.findById(id).get();
      Optional.of(videoEntity != null)
          .filter(c -> c)
          .orElseThrow(() -> new SQLException("Registro Inexistente"));
      
      boolean condition = this.springVideoRepository.findByUrl(id, video.getUrl()).isPresent();
      Optional.of(!condition)
              .filter(c -> c)
              .orElseThrow(() ->  new SQLException("Registro Existente"));

      videoEntity.setTitulo(video.getTitulo());
      videoEntity.setDescricao(video.getDescricao());
      videoEntity.setUrl(video.getUrl());
      videoEntity.setStatus(video.getStatus());

      return this.springVideoRepository.save(videoEntity).getId();
      
    }

    @Override
    @Transactional
    public boolean removeOne(Long id) throws SQLException {
      
      VideoEntity videoEntity = this.springVideoRepository.findById(id).get();
      Optional.of(videoEntity != null)
          .filter(c -> c)
          .orElseThrow(() -> new SQLException("Vídeo Inexistente"));

      boolean condition = this.springVideoRepository.findById(id, 0).isPresent();
      Optional.of(!condition)
          .filter(c -> c)
          .orElseThrow(() -> new SQLException("Vídeo Removido"));
      
      videoEntity.setStatus(0);
      return this.springVideoRepository.save(videoEntity).getId() == id;
    }

    
}
