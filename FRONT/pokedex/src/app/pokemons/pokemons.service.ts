import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { delay, take, tap } from "rxjs";
import { environment } from "src/environments/environment";
import { Pokemon } from "./pokemon";

@Injectable({
    providedIn: 'root'
})
export class PokemonsService{

    private readonly API = `${environment.API}pokemon`;

    constructor(private http: HttpClient){}

    list(){
        return this.http.get<Pokemon[]>(this.API)
            .pipe(                
                tap(console.log)
            );
    }

    loadByID(id: any){
        return this.http.get<Pokemon[]>(`${this.API}/${id}`).pipe(take(1));
    }

    private create(pokemon: any){
        return this.http.post(this.API, pokemon).pipe(take(1));
    }

    private update(pokemon: { id: any; }){
        return this.http.put(`${this.API}/${pokemon.id}`, pokemon).pipe(take(1));
    }

    save(pokemon: { id: any; }){
        if(pokemon.id){
            return this.update(pokemon);
        }
        return this.create(pokemon);
    }

    remove(id: any){
        return this.http.delete(`${this.API}/${id}`).pipe(take(1));
    }

}