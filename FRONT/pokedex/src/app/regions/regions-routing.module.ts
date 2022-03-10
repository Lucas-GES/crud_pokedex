import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegionsEditComponent } from "./regions-edit/regions-edit.component";
import { RegionsListComponent } from "./regions-list/regions-list.component";

const routes: Routes = [
    { path: '', component: RegionsListComponent},
    { path: 'novo', component: RegionsEditComponent},
    { path: 'editar/:id', component: RegionsEditComponent}
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegionsRoutingModule{}