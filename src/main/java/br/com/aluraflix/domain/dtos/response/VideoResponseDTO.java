package br.com.aluraflix.domain.dtos.response;

import br.com.aluraflix.domain.Video;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VideoResponseDTO(

@NotNull(message="Id Nulo")
@NotBlank(message = "Id Em Branco")
Long id,


@Size(
    min = 5,
    max = 100,
    message = "O título deve conter  de {min} a {max} caracteres"
)
@NotNull(message="Título Nulo")
@NotBlank(message = "Título Em Branco") String titulo, 


@Size(
    min = 10,
    max = 5000,
    message = "A descrição deve conter de {min} a {max} caracteres"
)
@NotNull(message="Descrição Nula")
@NotBlank(message = "Descrição Em Branco") String descricao, 

@Pattern(regexp = "^(?:https?:\\/\\/)?(?:www\\.)?(?:m\\.)?(?:youtu\\.be\\/|youtube\\.com\\/(?:embed\\/|v\\/|watch\\?v=))([a-zA-Z0-9\\-_]{11})", message = "Formato Inválido da URL")
@NotNull(message="Url Nula")
@NotBlank(message = "Url em Branco") String url,

@NotNull(message="Status Nulo")
@NotBlank(message = "Status em Branco") int status) {

    public VideoResponseDTO(Video video){
        this(video.getId(),video.getTitulo(), video.getDescricao(), video.getUrl(), video.getStatus());
    }
    
}

