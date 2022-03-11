import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, EMPTY, Observable } from 'rxjs';
import { Pokemon } from '../pokemon';
import { Pokemon2Service } from '../pokemons2.service';

@Component({
  selector: 'app-pokemons-list',
  templateUrl: './pokemons-list.component.html',
  styleUrls: ['./pokemons-list.component.css']
})
export class PokemonsListComponent implements OnInit {

  pokemons$!: Observable<Pokemon[]>;

  pokemonSelecionado!: Pokemon;

  constructor(
    private service: Pokemon2Service,
    private router: Router,
    private route: ActivatedRoute  
  ) { }

  ngOnInit(): void {

    this.onRefresh();

  }

  onRefresh(){
    this.pokemons$ = this.service.list()
      .pipe(
        catchError(error => {
          console.error(error);
          this.handleError();
          return EMPTY;
        })
      );
  }

  handleError(){
    alert("Error 404");
  }

  editar(id: any){
    this.router.navigate(['editar', id], { relativeTo: this.route})
  }

  onDelete(pokemon: any){
    this.pokemonSelecionado = pokemon;

    this.service.remove(this.pokemonSelecionado.id).subscribe(
      success => {console.log("Success"),
      this.onRefresh();
    },
    error => {
      console.error(error);
    }
    )
  }

}
