import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user';
import {Link} from '../../model/link';
import {LinkService} from '../../service/link.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {UserService} from '../../service/user.service';
import {MatDialog} from '@angular/material/dialog';
import {LinkPageDto} from '../../model/link-page-dto';
import {FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-about-user',
  templateUrl: './about-user.component.html',
  styleUrls: ['./about-user.component.css']
})
export class AboutUserComponent implements OnInit {
  private currentUser: User;
  itemsPerPage: number = 20;
  totalItems: any;
  page: any;
  previousPage: any;
  private links: Link[];
  private pageOfCurrentUser: boolean;
  private requestUserId: number;
  private currentUserPage: User;
  private isFormVisible: boolean = false;
  public registrationForm: FormGroup;

  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private linkService: LinkService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.activeUser.subscribe((currentUser: User) => {
          this.currentUser = currentUser;
          this.requestUserId = Number(params.get('id'));
          this.userService.getUserById(this.requestUserId).subscribe((user: User) => {
            this.pageOfCurrentUser = currentUser.id === user.id;
            this.currentUserPage = user;
            if (!this.pageOfCurrentUser) {

              this.linkService.getAllLinksForAuthUser(0, this.requestUserId).subscribe((linkPage: LinkPageDto) => {
                this.totalItems = linkPage.totalPage * this.itemsPerPage;
                this.page = 1;
                this.links = linkPage.links;
              });
            } else {
              this.linkService.getAllUserLinks(0).subscribe((linkPage: LinkPageDto) => {
                this.totalItems = linkPage.totalPage * this.itemsPerPage;
                this.page = 1;
                this.links = linkPage.links;
              });
            }
          });
        });
      });

    this.linkService.deletedLinkId.subscribe((id: number) => {
      if (id !== null && id !== 0) {
        if (this.links.splice(this.links.findIndex(item => item.id === id), 1).length === 1) {
          this.linkService.deletedLinkId.next(null);
        }
      }
    });
    this.linkService.updatedLink.subscribe((link: Link) => {
      if (link !== null) {
        this.links.unshift(link);
        this.links.splice(this.links.findIndex(item => item.id === link.id), 1);
        this.linkService.updatedLink.next(null);
      }
    });
    this.linkService.newLink.subscribe((link: Link) => {
      if (link != null) {
        if (this.links.length === 20 && this.page === 1 && this.pageOfCurrentUser) {
          this.links.unshift(link);
          this.links.splice(19, 1);

          this.linkService.newLink.next(null);

        } else if (this.page === 1 && this.pageOfCurrentUser) {
          this.links.unshift(link);
          this.linkService.newLink.next(null);
        } else {
          this.linkService.newLink.next(null);
        }
      }
    });
    this.registrationForm = new FormGroup({
      userName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.pattern('[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?')]),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required])
    }, this.passwordAreEquals());
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

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      if (!this.pageOfCurrentUser) {
        this.linkService.getAllLinksForAuthUser(0, this.requestUserId).subscribe((linkPage: LinkPageDto) => {
          this.totalItems = linkPage.totalPage * this.itemsPerPage;
          this.page = linkPage.currentPage + 1;
          this.links = linkPage.links;
        });
      } else {
        this.linkService.getAllUserLinks(0).subscribe((linkPage: LinkPageDto) => {
          this.totalItems = linkPage.totalPage * this.itemsPerPage;
          this.page = linkPage.currentPage + 1;
          this.links = linkPage.links;
        });
      }
    }
  }

  getUrl() {
    if (this.currentUserPage != null) {
      return 'http://localhost:8080/api/image/' + this.currentUserPage.photoUrl;
    }
  }

  editProfile() {
    this.isFormVisible = true;
  }

  public registerUser(): void {
    let user: User = {
      id: this.currentUserPage.id,
      name: this.registrationForm.controls['firstName'].value,
      lastName: this.registrationForm.controls['lastName'].value,
      username: this.registrationForm.controls['userName'].value,
      photoUrl: null,
      email:null,
      links: null,
      comments: null,
      password: this.registrationForm.controls['password'].value
    };
    this.userService.updateCurrentUser(user);
    this.isFormVisible = false;
    this.userService.activeUser.subscribe((user:User)=>{
      this.currentUserPage=user;
      this.openSnackBar("User successfully updated","Close");
    })
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
