package br.com.aluraflix.adapters.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/videos/{id}")
    public ResponseEntity<?> listOne(@PathVariable Long id){

        Video video = this.servicePort.getOne(id);
        
        return new ResponseEntity<>(new VideoResponseDTO(video), HttpStatus.OK);
    }

    @GetMapping("/videos")
    public ResponseEntity<?> listAll(){
        
        List<Video> videos = this.servicePort.getAll();
        List<VideoResponseDTO> dtosResponse = new ArrayList<>();

        for (var video : videos) {
            dtosResponse.add(new VideoResponseDTO(video));
        }

        return new ResponseEntity<>(dtosResponse, HttpStatus.OK);
    }

    @PostMapping("/videos")
    public ResponseEntity<VideoResponseDTO> create(@RequestBody @Valid VideoRequestDTO dto) throws SQLException{
        
        Video video = this.servicePort.save(new Video(dto));
        
        return new ResponseEntity<>(new VideoResponseDTO(video), HttpStatus.CREATED);
    }
    
    @PutMapping("/videos/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id,  @RequestBody @Valid VideoRequestDTO dtoRequest) throws SQLException{
        
        Long IdAtualizado = this.servicePort.update(id, new Video(dtoRequest));
        
        return new ResponseEntity<>("Filme Atualizado: "+IdAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/videos/{id}")
    public ResponseEntity<?> removeOne(@PathVariable Long id) throws SQLException{

        boolean condition = this.servicePort.removeOne(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
