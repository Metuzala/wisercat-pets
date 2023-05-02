import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PetsTableComponent } from './pets-table/pets-table.component';
import { PetFormComponent } from './pet-form/pet-form.component';
import { LoginFormComponent } from './login-form/login-form.component';

const routes: Routes = [
  {
    path: 'pets',
    component: PetsTableComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'logout',
    component: LoginFormComponent
  },
  {
    path: 'pet-form/:id',
    component: PetFormComponent
  },
  {
    path: 'pet-form',
    component: PetFormComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
