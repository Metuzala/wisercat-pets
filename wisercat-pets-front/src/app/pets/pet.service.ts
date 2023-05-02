import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pet } from './pet';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  optionsUrl = 'http://localhost:8080/options';
  petsUrl = 'http://localhost:8080/api/pets';

  countryOptions: any = {};
  petTypes: any = {};
  furColors: any = {};

  constructor(private http: HttpClient) {
  }

  loadOptions() {
    this.http.get<any>(this.optionsUrl).subscribe((options: any) => {
      this.countryOptions = options.countries;
      this.petTypes = options.types;
      this.furColors = options.furColors;
    });
  }

  getPets() {
    return this.http.get<Pet[]>(this.petsUrl, { withCredentials: true });
  }

  getPet(id: Number) {
    return this.http.get<Pet>(`${this.petsUrl}/${id}`, { withCredentials: true });
  }

  savePet(pet: Pet) {
    return this.http.post<Pet>(`${this.petsUrl}`, pet, { withCredentials: true });
  }

  deletePet(id: number) {
    return this.http.delete(`${this.petsUrl}/${id}`, { withCredentials: true });
  }
}
