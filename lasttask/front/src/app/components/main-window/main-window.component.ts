import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-main-window',
  templateUrl: './main-window.component.html',
  styleUrls: ['./main-window.component.css']
})
export class MainWindowComponent implements OnInit {

  public authForm: FormGroup;


  constructor(private userService: UserService,
              private router: Router,
              private authService: AuthService) {
  }


  ngOnInit() {
    this.authForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }


  public authUser() {
    this.authService.login(this.authForm.controls['username'].value, this.authForm.controls['password'].value);

    // user;
    // if(user==null)
    // {
    //   this.router.navigate(['/home']);
    // }
    // else
    // {
    //   this.router.navigate(['/story']);
    // }


  }


}
