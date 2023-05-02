import { Component } from '@angular/core';
import { PetService } from '../pets/pet.service';
import { UserService } from '../users/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pets-table',
  templateUrl: './pets-table.component.html',
  styleUrls: ['./pets-table.component.css']
})
export class PetsTableComponent {

  petList: any[] = [];
  sortBy: String = "";
  sortDirection: String = "";

  constructor(private petService: PetService, public userService: UserService, private router: Router) { }

  ngOnInit() {
    // if no logged in user, redirect to main page
    if (!this.userService.user) this.router.navigate(['/']);
    this.petService.getPets().subscribe((pets: any[]) => {
      this.petList = pets;
    });
  }

  delete(id: number) {
    // delete and reload page
    this.petService.deletePet(id).subscribe(() => this.ngOnInit());
  }

  getColor(color: string) {
    return this.petService.furColors[color] ?? color;
  }
  getType(type: string) {
    return this.petService.petTypes[type] ?? type;
  }
  getCountryOfOrigin(country: string) {
    return this.petService.countryOptions[country] ?? country;
  }

  changeSort() {
    if (this.sortDirection !== 'asc') this.sortDirection = 'asc';
    else if (this.sortDirection === 'asc') this.sortDirection = 'desc';
  }

  sortByName() {
    this.sortBy = 'name';
    this.changeSort();

    this.petList.sort((a, b) => {
      const values = this.sortDirection === 'asc' ? [a.name, b.name] : [b.name, a.name];
      return values[0].localeCompare(values[1]);
    });
  }

  sortByCode() {
    this.sortBy = 'code';
    this.changeSort();

    this.petList.sort((a, b) => {
      const values = this.sortDirection === 'asc' ? [a.code, b.code] : [b.code, a.code];
      return parseInt(values[0]) - parseInt(values[1]);
    });
  }

  sortByType() {
    this.sortBy = 'type';
    this.changeSort();

    this.petList.sort((a, b) => {
      const values = this.sortDirection === 'asc' ? [a.type, b.type] : [b.type, a.type];
      return this.petService.petTypes[values[0]].localeCompare(this.petService.petTypes[values[1]]);
    });
  }

  sortByFur() {
    this.sortBy = 'furColor';
    this.changeSort();

    this.petList.sort((a, b) => {
      const values = this.sortDirection === 'asc' ? [a.furColor, b.furColor] : [b.furColor, a.furColor];
      return this.petService.furColors[values[0]].localeCompare(this.petService.furColors[values[1]]);
    });
  }

  sortByOrigin() {
    this.sortBy = 'countryOfOrigin';
    this.changeSort();

    this.petList.sort((a, b) => {
      const values = this.sortDirection === 'asc' ? [a.countryOfOrigin, b.countryOfOrigin] : [b.countryOfOrigin, a.countryOfOrigin];
      return this.petService.countryOptions[values[0]].localeCompare(this.petService.countryOptions[values[1]]);
    });
  }

  getSortIconClass(field: string) {
    if (this.sortDirection === '' || this.sortBy !== field) return 'fa fa-sort';
    return this.sortDirection === 'asc' ? 'fa fa-sort-up' : 'fa fa-sort-down';
  }

}
