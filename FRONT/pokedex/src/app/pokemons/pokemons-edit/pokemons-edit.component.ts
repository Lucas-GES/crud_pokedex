import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Pokemon2Service } from '../pokemons2.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-pokemons-edit',
  templateUrl: './pokemons-edit.component.html',
  styleUrls: ['./pokemons-edit.component.css']
})
export class PokemonsEditComponent implements OnInit {

  form!: FormGroup;
  submitted: boolean = false;

  constructor(
    private fb: FormBuilder,
    private service: Pokemon2Service,
    private location: Location,
    private route: ActivatedRoute  
  ) { }

  ngOnInit(): void {

    const pokemon = this.route.snapshot.data['pokemons'];

    this.form = this.fb.group({
      id: [pokemon.id],
      img: [pokemon.img],      
      nome: [pokemon.nome],
      tipo: [pokemon.tipo],
      iv: [pokemon.iv]
    })

  }

  onSubmit(){
    this.submitted = true;
    if(this.form.valid){
      console.log('submit');

      this.service.save(this.form.value).subscribe(
        success => {
          console.log('success');
          this.location.back();
        },
        error => {console.log(error)}

      );

    }
  }

  onCancel(){
    this.submitted = false;
    this.form.reset();
  }

}
