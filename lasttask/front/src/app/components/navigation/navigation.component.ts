import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import {Router} from '@angular/router';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {AddLinkComponent} from '../add-link/add-link.component';
import {AuthService} from '../../service/auth.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LinkService} from '../../service/link.service';
import {LinkPageDto} from '../../model/link-page-dto';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  public searchForm: FormGroup;

  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private router: Router,
    private authService: AuthService,
    private linkService: LinkService,
    private _snackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      name: new FormControl('', Validators.required),
    });
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '50%';
    this.dialog.open(AddLinkComponent, dialogConfig);

  }

  openAboutUser() {
    this.userService.activeUser.subscribe((user: User) => {
      this.router.navigate(['/user/' + user.id]);
    });
  }

  signout() {
    this.authService.logout();
  }

  findByName() {
    this.linkService.getLinkByName(this.searchForm.controls['name'].value).subscribe((linkPage: LinkPageDto) => {
      this.linkService.nameSubject.next(linkPage);
    }, error => {
      this.openSnackBar('Something went wrong: ' + error.message, 'Close');
    });
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
