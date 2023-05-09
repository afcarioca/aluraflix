package br.com.aluraflix.adapters.controllers;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluraflix.domain.Video;
import br.com.aluraflix.domain.dtos.request.VideoRequestDTO;
import br.com.aluraflix.domain.dtos.response.VideoResponseDTO;
import br.com.aluraflix.domain.ports.interfaces.VideoServicePort;
import br.com.aluraflix.interceptor.VideoExistenceException;
import jakarta.validation.Valid;

@RestController
public class VideosController {
    
    
    private final VideoServicePort servicePort;
   
    public VideosController(VideoServicePort servicePort){
        this.servicePort = servicePort;
    }


    @PostMapping("/videos")
    public ResponseEntity<VideoResponseDTO> create(@RequestBody @Valid VideoRequestDTO dto) throws SQLException{
        
        boolean condition = this.servicePort
            .findByUrl(dto.url())
            .isPresent(); 

        Optional.of(!condition)
            .filter(c -> c)
            .orElseThrow(() -> new VideoExistenceException("Vídeo já cadastrado"));
        
        Video video = this.servicePort.save(new Video(dto));
        return new ResponseEntity<>(new VideoResponseDTO(video), HttpStatus.CREATED);
    } 

}
