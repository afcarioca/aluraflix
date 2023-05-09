package br.com.aluraflix.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aluraflix.domain.adapters.services.VideoServiceImpl;
import br.com.aluraflix.domain.ports.interfaces.VideoServicePort;
import br.com.aluraflix.domain.ports.repositories.VideoRepositoryPort;

@Configuration
public class BeanConfiguration {
    
    @Bean
    VideoServicePort videoService(VideoRepositoryPort videoRepositoryPort){
        return new VideoServiceImpl(videoRepositoryPort);
    }
}
