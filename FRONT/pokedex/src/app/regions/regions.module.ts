import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { RegionsEditComponent } from "./regions-edit/regions-edit.component";
import { RegionsListComponent } from "./regions-list/regions-list.component";
import { RegionsRoutingModule } from "./regions-routing.module";

@NgModule({
    imports: [
        CommonModule,
        RegionsRoutingModule,
        ReactiveFormsModule
    ],
    declarations: [RegionsListComponent, RegionsEditComponent]
})
export class RegionsModule{}