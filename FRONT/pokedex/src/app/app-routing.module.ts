import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: ''},
  {path: 'pokemons', loadChildren: () => import('./pokemons/pokemons.module').then(m => m.PokemonsModule)},
  {path: 'regions', loadChildren: () => import('./regions/regions.module').then(m => m.RegionsModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
