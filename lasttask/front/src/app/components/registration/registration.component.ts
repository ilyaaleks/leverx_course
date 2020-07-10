import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {UserService} from '../../service/user.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public registrationForm: FormGroup;
  public status: string;

  constructor(private userService: UserService,
              private router: Router,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.registrationForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.pattern('[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?')]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required])
    }, this.passwordAreEquals())
    ;
  }

  private passwordAreEquals(): ValidatorFn {
    return (group: FormGroup): { [key: string]: any } => {
      if (!(group.dirty || group.touched) || group.get('password').value === group.get('confirmPassword').value) {
        return null;
      }
      return {
        custom: 'Password are not equals'
      };
    };
  }

  public registerUser(): void {
    console.log('Method work');
    let user: User = {
      id: null,
      name: this.registrationForm.controls['firstName'].value,
      lastName: this.registrationForm.controls['lastName'].value,
      username: this.registrationForm.controls['userName'].value,
      photoUrl: null,
      email: this.registrationForm.controls['email'].value,
      links: null,
      comments: null,
      password: this.registrationForm.controls['password'].value
    };
    this.userService.saveUser(user).subscribe(() => {
      this.openSnackBar('Please confirm your email', 'Close');
      this.router.navigate(['/home']);
      }, (error1) => {
        this.status = error1;
        this.openSnackBar('Something went wrong: ' + error1.message, 'Close');
      }
    );


  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
