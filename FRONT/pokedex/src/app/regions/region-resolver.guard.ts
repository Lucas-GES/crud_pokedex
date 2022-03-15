import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { Region } from "./region";
import { RegionsService } from "./regions.service";

@Injectable({
    providedIn: 'root'
})
export class RegionResolverGuard implements Resolve<Region>{
    
    constructor(private service : RegionsService){}
    
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Region | Observable<Region> | Promise<Region> | Observable<any> {
        if(route.params && route.params['id']){
            return this.service.loadByID(route.params['id']);
        }

        return of({            
            id: null,          
            nome: null            
        })
    }
    
}