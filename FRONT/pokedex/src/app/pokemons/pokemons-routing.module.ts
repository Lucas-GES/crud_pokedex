import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { PokemonResolverGuard } from "./pokemon-resolver.guard";
import { PokemonsEditComponent } from "./pokemons-edit/pokemons-edit.component";
import { PokemonsListComponent } from "./pokemons-list/pokemons-list.component";

const routes: Routes = [
    { path: '', component: PokemonsListComponent },
    {
        path: 'novo', component: PokemonsEditComponent,
        resolve: {
            pokemon: PokemonResolverGuard
        } 
    },
    {
        path: 'editar/:id', component: PokemonsEditComponent,
        resolve: {
            pokemon: PokemonResolverGuard
        }
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PokemonsRoutingModule { }