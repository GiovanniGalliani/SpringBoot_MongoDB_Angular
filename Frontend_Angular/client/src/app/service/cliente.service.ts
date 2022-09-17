import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, catchError, of } from 'rxjs';
import { Cliente } from '../cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private cliente!: Cliente;
  private baseUrl: string = 'http://127.0.0.1:8080/api'; // Richiamo il Backend
  private httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin' : '*',
      'Access-Control-Allow-Methods' : 'GET,POST,PUT,DELETE,OPTIONS',
      'Content-Type' : 'application/json'
    })
  };

  constructor(private _http: HttpClient) { }

  getClienti(): Observable<any> {
    return this._http.get(this.baseUrl+'/contatti',this.httpOptions).pipe(
      map((response => response)),
      catchError(this.errorHandler<any>('Get clienti')));   
  }

  createCliente(cliente:Cliente):Observable<any>{
    return this._http.post(this.baseUrl+'/save', cliente,this.httpOptions).pipe(
      map((response => response)),
      catchError(this.errorHandler<any>('Create clienti')));
  }

  updateCliente(cliente:Cliente):Observable<any>{
    return this._http.put(this.baseUrl+'/update',cliente,this.httpOptions).pipe(
      map((response => response)),
      catchError(this.errorHandler<any>('Update clienti')));
  }

  deleteCliente(id:string):Observable<any> {
    return this._http.delete(this.baseUrl+'/delete/'+id,this.httpOptions).pipe(
      map((response => response)),
      catchError(this.errorHandler<any>('Delete clienti')));
  }

  setter(cliente : Cliente) {
    this.cliente = cliente;
  }

  getter() {
    return this.cliente;
  }

  private errorHandler<T>(operation = 'Operazione', result?: T){
    return(error:any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }
}