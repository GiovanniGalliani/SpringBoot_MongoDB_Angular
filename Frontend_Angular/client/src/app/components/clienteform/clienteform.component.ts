import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/cliente';
import { ClienteService } from 'src/app/service/cliente.service';

@Component({
  selector: 'app-clienteform',
  templateUrl: './clienteform.component.html',
  styleUrls: ['./clienteform.component.css']
})
export class ClienteformComponent implements OnInit {
  cliente!:Cliente;

  formCliente: FormGroup;
  nome: FormControl;
  cognome: FormControl;
  username: FormControl;
  password: FormControl;
  email: FormControl;


  constructor(private _clienteService: ClienteService, private _router:Router, fb: FormBuilder) {
    this.formCliente = fb.group({
      'nome':['', Validators.required],
      'cognome':['', Validators.required],
      'username':['', Validators.required],
      'password':['', Validators.required],
      'email':['', Validators.required],
    })
    this.nome = this.formCliente.controls['nome'] as FormControl;
    this.cognome = this.formCliente.controls['cognome'] as FormControl;
    this.username = this.formCliente.controls['username'] as FormControl;
    this.password = this.formCliente.controls['password'] as FormControl;
    this.email = this.formCliente.controls['email'] as FormControl;
   }

  ngOnInit(): void {
    this.cliente = this._clienteService.getter();
  }

  processaForm() {
    if (this.cliente.id == undefined) {

      this._clienteService.createCliente(this.cliente).subscribe((c) => {
        console.log(c);
        this._router.navigate(['/']);
      });
    } else {
      this._clienteService.updateCliente(this.cliente).subscribe((c) => {
        console.log(c);
        this._router.navigate(['/']);
      });
    }
  }
}