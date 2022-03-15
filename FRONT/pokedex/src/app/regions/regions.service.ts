import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { take, tap } from "rxjs";
import { environment } from "src/environments/environment";
import { Region } from "./region";

@Injectable({
    providedIn: 'root'
})
export class RegionsService{

    private readonly API = `${environment.API}region`;

    constructor(private http: HttpClient){}

    list(){
        return this.http.get<Region[]>(this.API)
            .pipe(                
                tap(console.log)
            );
    }

    loadByID(id: any){
        return this.http.get<Region[]>(`${this.API}/${id}`).pipe(take(1));
    }

    private create(region: any){
        return this.http.post(this.API, region).pipe(take(1));
    }

    private update(region: { id: any; }){
        return this.http.put(`${this.API}/${region.id}`, region).pipe(take(1));
    }

    save(region: { id: any; }){
        if(region.id){
            return this.update(region);
        }
        return this.create(region);
    }

    remove(id: any){
        return this.http.delete(`${this.API}/${id}`).pipe(take(1));
    }

}