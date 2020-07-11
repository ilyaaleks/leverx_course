import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import {Router} from '@angular/router';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {AddLinkComponent} from '../add-link/add-link.component';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(
    public dialog: MatDialog,
    private userService: UserService,
    private router: Router,
    private authService:AuthService) {
  }

  ngOnInit(): void {

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
  signout()
  {
    this.authService.logout();
  }

}
