
import moment from 'moment';

export function calcularTaxa(quantidade: number, dataAgendada: Date): number {
  var taxaA = false;
  var taxaB = false;
  var taxaC = false;
  var taxaCVal = 0;
  var taxa = 0;

  //quantidade
  if (quantidade <= 1000) {
    taxaA = true;
  } else if (quantidade > 1000 && quantidade <= 2000) {
    taxaB = true;
  } else if (quantidade > 2000) {
    taxaC = true;
  }

  //tempo
  var dataAtual = moment();
  var dataAgendadaMoment = moment(dataAgendada);
  if(dataAtual.isSame(dataAgendadaMoment, 'day')){
    taxaA = true;
  }else if (menosQue10Dias(dataAgendada) ) {
    taxaB = true;
  }

  if (entre10a20Dias(dataAgendada) ) {
    taxaC = true;
    taxaCVal = 0.082;
  }else if (entre20a30Dias(dataAgendada) ) {
    taxaC = true;
    taxaCVal = 0.069;
  }else if (entre30a40Dias(dataAgendada) ) {
    taxaC = true;
    taxaCVal = 0.054;
  }else if (dataAgendadaMoment.diff(dataAtual, 'days') > 40 ) {
    taxaC = true;
    taxaCVal = 0.017;
  }

  //calculo
  if (taxaA){
    taxa=+ 3 + (quantidade * 0.03);
  }
  if (taxaB){
    taxa=+ 12 ;
  }
  if (taxaC && taxaCVal != 0){
    taxa=+ (quantidade * taxaCVal);
  }
  return taxa;

}


function menosQue10Dias(dataAgendada: Date): boolean {
  var dataAtual = moment();
  var dataAgendadaMoment = moment(dataAgendada);
  if (dataAgendadaMoment.diff(dataAtual, 'days') <= 10) {
    return true;
  }
  return false;
}

function entre10a20Dias(dataAgendada: Date): boolean {
  var dataAtual = moment();
  var dataAgendadaMoment = moment(dataAgendada);
  if (dataAgendadaMoment.diff(dataAtual, 'days') > 10 && dataAgendadaMoment.diff(dataAtual, 'days') <= 20) {
    return true;
  }
  return false;
}

function entre20a30Dias(dataAgendada: Date): boolean {
  var dataAtual = moment();
  var dataAgendadaMoment = moment(dataAgendada);
  if (dataAgendadaMoment.diff(dataAtual, 'days') > 20 && dataAgendadaMoment.diff(dataAtual, 'days') <= 30) {
    return true;
  }
  return false;
}

function entre30a40Dias(dataAgendada: Date): boolean {
  var dataAtual = moment();
  var dataAgendadaMoment = moment(dataAgendada);
  if (dataAgendadaMoment.diff(dataAtual, 'days') > 30 && dataAgendadaMoment.diff(dataAtual, 'days') <= 40) {
    return true;
  }
  return false;
}

function maisQue40dias(dataAgendada: Date): boolean {
  var dataAtual = moment();
  var dataAgendadaMoment = moment(dataAgendada);
  if (dataAgendadaMoment.diff(dataAtual, 'days') > 40) {
    return true;
  }
  return false;
}
