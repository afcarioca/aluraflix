package br.com.aluraflix.domain.enums;

public enum Status {
    ATIVO(1),INATIVO(0);

    private int code;

    private Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
