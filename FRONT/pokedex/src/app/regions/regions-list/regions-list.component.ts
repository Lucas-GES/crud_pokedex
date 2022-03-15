import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, EMPTY, Observable } from 'rxjs';
import { Region } from '../region';
import { Region2Service } from '../region2.service';

@Component({
  selector: 'app-regions-list',
  templateUrl: './regions-list.component.html',
  styleUrls: ['./regions-list.component.css']
})
export class RegionsListComponent implements OnInit {

  regions$!: Observable<Region[]>;

  regionSelected!: Region;

  constructor(
    private service: Region2Service,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.onRefresh();
  }

  onRefresh(){
    this.regions$ = this.service.list()
      .pipe(
        catchError(error => {
          console.error(error);
          this.handleError();
          return EMPTY;
        })
      );
  }

  handleError(){
    alert("Error 404");
  }

  editar(id: any){
    this.router.navigate(['editar', id], { relativeTo: this.route})
  }

  onDelete(region: any){
    this.regionSelected = region;

    this.service.remove(this.regionSelected.id).subscribe(
      success => {console.log("Success"),
      this.onRefresh();
    },
    error => {
      console.error(error);
    }
    )
  }

}
