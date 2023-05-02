import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PetService } from '../pets/pet.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Pet } from '../pets/pet';
import { UserService } from '../users/user.service';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: ['./pet-form.component.css']
})
export class PetFormComponent {

  submitted = false;
  id: number | null = null;

  petForm = this.formBuilder.group({
    id: new FormControl(null),
    name: new FormControl(null, Validators.required),
    code: new FormControl(null, Validators.required),
    type: new FormControl(null, Validators.required),
    furColor: new FormControl(null, Validators.required),
    countryOfOrigin: new FormControl(null, Validators.required)
  });

  constructor(private route: ActivatedRoute,
    public petService: PetService, private formBuilder: FormBuilder, private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    if (!this.userService.user) this.router.navigate(['/']);
    this.route.params.subscribe(async params => {
      this.id = params['id'];
      if (this.id) {
        // if had pet id from url, fetch
        this.petService.getPet(this.id).subscribe((pet: any) => {
          this.petForm.setValue(pet);
        });
      }
    });
  }

  get f() {
    return this.petForm.controls;
  }

  onDelete() {
    this.petService.deletePet(this.id as number).subscribe(() => this.router.navigate(['/pets']));
  }

  onSubmit() {
    this.submitted = true;
    if (this.petForm.invalid) return;
    const pet: Pet = this.petForm.value as Pet;

    // save pet and navigate to table
    this.petService.savePet(pet).subscribe(() => this.router.navigate(['/pets']));
  }
}
