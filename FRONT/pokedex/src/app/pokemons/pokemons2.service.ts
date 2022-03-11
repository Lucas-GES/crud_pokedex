import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { CrudService } from "../shared/crud-service";
import { Pokemon } from "./pokemon";

@Injectable({
    providedIn: 'root'
})
export class Pokemon2Service extends CrudService<Pokemon>{

    constructor(protected override http: HttpClient){
        super(http, `${environment.API}pokemon`)
    }
}