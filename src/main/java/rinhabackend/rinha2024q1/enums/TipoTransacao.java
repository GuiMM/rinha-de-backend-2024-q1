package rinhabackend.rinha2024q1.enums;

import lombok.Data;


public enum TipoTransacao {
    DEBITO("d"),
    CREDITO("c");

    private String tipo;
    TipoTransacao(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
