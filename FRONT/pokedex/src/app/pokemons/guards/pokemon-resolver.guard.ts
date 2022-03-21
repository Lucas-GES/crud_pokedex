import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { Pokemon } from "../pokemon";
import { PokemonsService } from "../pokemons.service";

@Injectable({
    providedIn: 'root'
})
export class PokemonResolverGuard implements Resolve<Pokemon>{
    
    constructor(private service : PokemonsService){}
    
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Pokemon | Observable<Pokemon> | Promise<Pokemon> | Observable<any> {
        if(route.params && route.params['id']){
            return this.service.loadByID(route.params['id']);
        }

        return of({
            img: null,
            id: null,          
            nome: null,
            tipo: null,
            iv: null,
            region: null
        })
    }
    
}