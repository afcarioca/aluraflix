package br.com.aluraflix.infraestructure.adapaters.entities;

import br.com.aluraflix.domain.Video;
import br.com.aluraflix.domain.dtos.request.VideoRequestDTO;
import br.com.aluraflix.domain.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "videos")
public class VideoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(length = 100)
    @NotNull(message="Título Nulo")
    @NotEmpty(message = "Título Inexistente")
    @NotBlank(message = "Título Em Branco")
    private String titulo;

    @Column(length = 5000)
    @NotNull(message="Descrição Nula")
    @NotEmpty(message = "Descrição Inexistente")
    @NotBlank(message = "Descrição Em Branco")
    private String descricao;

    
    @Pattern(regexp = "^(?:https?:\\/\\/)?(?:www\\.)?(?:m\\.)?(?:youtu\\.be\\/|youtube\\.com\\/(?:embed\\/|v\\/|watch\\?v=))([a-zA-Z0-9\\-_]{11})", message = "Formato Inválido da URL")
    @NotNull(message="Url Nula")
    @NotEmpty(message = "Url Inexistente")
    @NotBlank(message = "Url em Branco")
    private String url;

    @NotNull(message="Status Nulo")
    private int status;

    @Deprecated
    public VideoEntity(){

    }

    public VideoEntity(VideoRequestDTO dto){
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.url = dto.url();
        this.status = Status.ATIVO.getCode();
    }

    public VideoEntity(Video video){
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.status = Status.ATIVO.getCode();
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

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

}
