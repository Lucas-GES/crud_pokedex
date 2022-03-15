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

    const pokemon = this.route.snapshot.data['pokemon'];

    this.form = this.fb.group({
      img: [pokemon.img],
      id: [pokemon.id],
      name: [pokemon.name],
      tipo: [pokemon.tipo],
      iv: [pokemon.iv],
      region_id: [pokemon.region]
    }
      
    )

  }

  onSubmit(){
    this.submitted = true;
    if(this.form?.valid){
      console.log('submit');
      console.log(this.form.value);
      this.service.save(this.form.value).subscribe({
        next: () => {
          console.log('success');
          this.location.back();
        },
        error: () => {console.log(this.form?.value)}

      });

    }
  }

  onCancel(){
    this.submitted = false;
    this.form?.reset();
  }

}
