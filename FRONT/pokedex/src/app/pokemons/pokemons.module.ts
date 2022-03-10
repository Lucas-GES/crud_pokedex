import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { PokemonsEditComponent } from "./pokemons-edit/pokemons-edit.component";
import { PokemonsListComponent } from "./pokemons-list/pokemons-list.component";
import { PokemonsRoutingModule } from "./pokemons-routing.module";

@NgModule({
    imports: [
        CommonModule,
        PokemonsRoutingModule,
        ReactiveFormsModule
    ],
    declarations: [PokemonsListComponent, PokemonsEditComponent]
})
export class PokemonsModule {}