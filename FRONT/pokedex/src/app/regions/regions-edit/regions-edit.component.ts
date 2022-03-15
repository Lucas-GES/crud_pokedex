import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Region2Service } from '../region2.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-regions-edit',
  templateUrl: './regions-edit.component.html',
  styleUrls: ['./regions-edit.component.css']
})
export class RegionsEditComponent implements OnInit {

  form!: FormGroup;
  submitted: boolean = false;

  constructor(
    private fb: FormBuilder,
    private service: Region2Service,
    private location: Location,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    const region = this.route.snapshot.data['region'];

    this.form = this.fb.group({      
      id: [region.id],
      name: [region.name]      
    })

  }

  onSubmit(){
    this.submitted = true;
    if(this.form?.valid){
      console.log('submit');
      console.log(this.form.value);
      this.service.save(this.form.value).subscribe({
        next: () => {
          console.log('success');
          this.location.back();
        },
        error: () => {console.log(this.form?.value)}

      });

    }
  }

  onCancel(){
    this.submitted = false;
    this.form?.reset();
  }

}
