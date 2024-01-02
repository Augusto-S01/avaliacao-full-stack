import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'moedaReal',
  standalone: true
})
export class MoedaRealPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    if (typeof value === 'number') {
      return value.toString().replace('.', ',');
    }
    return null;
  }

}
