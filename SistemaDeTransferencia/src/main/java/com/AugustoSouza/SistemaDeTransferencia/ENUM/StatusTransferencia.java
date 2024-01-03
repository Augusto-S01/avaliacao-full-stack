package com.AugustoSouza.SistemaDeTransferencia.ENUM;

public enum StatusTransferencia {
    AGENDADA("Agendada"),
    REALIZADA("Realizada"),
    CANCELADA("Cancelada"),
    ERRO("Erro");

    private String status;

    StatusTransferencia(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}