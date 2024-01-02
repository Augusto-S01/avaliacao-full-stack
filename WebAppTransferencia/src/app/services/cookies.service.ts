import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CookiesService {

  constructor() { }

  getCookie(name: string) {
    const cookies = document.cookie.split(';');
    const cookie = cookies.find((cookie) => cookie.includes(name));
    return cookie ? cookie.split('=')[1] : null;
  }

  setCookie(name: string, value: string) {
    document.cookie = `${name}=${value}`;
  }

  setTimeCookie(name: string, value: string, time: number) {
    const date = new Date();
    date.setTime(date.getTime() + (time * 60 * 1000));
    document.cookie = `${name}=${value}; expires=${date.toUTCString()}`;
  }

  deleteCookie(name: string) {
    document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
  }

}
