import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../users/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    // login is handled by backend
    if (this.router.url === '/logout') {
      window.location.href = this.userService.logoutUrl;
    } else {
      window.location.href = this.userService.loginUrl;
    }
  }
}
