package br.com.aluraflix.domain;

import javax.xml.stream.events.EndElement;

import br.com.aluraflix.domain.dtos.request.VideoRequestDTO;
import br.com.aluraflix.domain.enums.Status;
import br.com.aluraflix.infraestructure.adapaters.entities.VideoEntity;

public class Video {
    
    private Long Id;
    private String titulo;
    private String descricao;
    private String url;
    private int status;

    public Video(){

    }

    public Video(VideoRequestDTO dto){
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.url = dto.url();
        this.status = Status.ATIVO.getCode();

    }

    public Video(VideoEntity entity){
        this.Id = entity.getId();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
        this.url = entity.getUrl();
        this.status = entity.getStatus();  
    }

    public Long getId() {
        return Id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    

}
