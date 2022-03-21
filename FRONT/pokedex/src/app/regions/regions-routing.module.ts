import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegionResolverGuard } from "./guards/region-resolver.guard";
import { RegionsEditComponent } from "./regions-edit/regions-edit.component";
import { RegionsListComponent } from "./regions-list/regions-list.component";

const routes: Routes = [
    { path: '', component: RegionsListComponent },
    {
        path: 'novo', component: RegionsEditComponent,
        resolve: {
            region: RegionResolverGuard
        }
    },
    {
        path: 'editar/:id', component: RegionsEditComponent,
        resolve: {
            region: RegionResolverGuard
        }
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RegionsRoutingModule { }