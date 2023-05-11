package br.com.aluraflix.interceptor;

public class VideoNotRemoveException extends RuntimeException {
    
    public VideoNotRemoveException(String msg){
            super(msg);
    }
}
