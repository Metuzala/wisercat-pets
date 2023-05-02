import { Component } from '@angular/core';
import { PetService } from './pets/pet.service';
import { UserService } from './users/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'wisercat-pets-front';

  constructor(private petService: PetService, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.petService.loadOptions();

    // get user with cookie, if success, then go to list, else to backend login
    this.userService.getCurrentUser().subscribe({
      next: () => this.router.navigate(['/pets']),
      error: () => this.router.navigate(['/login'])
    })
  }
}
