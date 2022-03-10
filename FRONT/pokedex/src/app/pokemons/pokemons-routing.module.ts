import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { PokemonsEditComponent } from "./pokemons-edit/pokemons-edit.component";
import { PokemonsListComponent } from "./pokemons-list/pokemons-list.component";

const routes: Routes = [
    { path: '', component: PokemonsListComponent},
    { path: 'novo', component: PokemonsEditComponent},
    { path: 'editar/:id', component: PokemonsEditComponent}
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PokemonsRoutingModule {}