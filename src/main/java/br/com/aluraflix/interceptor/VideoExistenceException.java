package br.com.aluraflix.interceptor;

public class VideoExistenceException extends RuntimeException{
    
    public VideoExistenceException(String msg){
        super(msg);
    }
}
