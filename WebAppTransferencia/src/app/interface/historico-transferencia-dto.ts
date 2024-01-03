export interface HistoricoTransferenciaDTO {

  destinatario : {
    "usuarname": string,
    "accountNumber":  number,
  },
  remetente : {
    "usuarname": string,
    "accountNumber":  number,
  },
  valor : number,
  taxa : number,
  dataTransferencia : Date,
  dataAgendamento : Date,
  status : string,
}

