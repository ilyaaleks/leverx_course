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
import {PhotoPath} from '../../model/photo-path';

@Component({
  selector: 'app-about-user',
  templateUrl: './about-user.component.html',
  styleUrls: ['./about-user.component.css']
})
export class AboutUserComponent implements OnInit {
  public currentUser: User;
  public photoForm: FormGroup;
  itemsPerPage: number = 20;
  totalItems: any;
  page: any;
  previousPage: any;
  public links: Link[];
  public pageOfCurrentUser: boolean;
  public requestUserId: number;
  public currentUserPage: User;
  public isFormVisible: boolean = false;
  public registrationForm: FormGroup;
  fileToUpload: File = null;
  public onFileChange(files: FileList)
  {
    this.fileToUpload = files.item(0);
  }
  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private linkService: LinkService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.photoForm = new FormGroup({
      file: new FormControl(null, [Validators.required])
    })
    this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        this.userService.activeUser.subscribe((currentUser: User) => {
          this.currentUser = currentUser;
          this.requestUserId = Number(params.get('id'));
          this.userService.getUserById(this.requestUserId).subscribe((user: User) => {
            this.pageOfCurrentUser = currentUser.id === user.id;
            this.currentUserPage = user;
            if (!this.pageOfCurrentUser) {

              this.linkService.getAllPublicUserLinks(0, this.currentUserPage).subscribe((linkPage: LinkPageDto) => {
                this.totalItems = linkPage.totalPage * this.itemsPerPage;
                this.page = 1;
                this.links = linkPage.links;
              });
            } else {
              this.linkService.getAllUserLinks(0,this.currentUserPage).subscribe((linkPage: LinkPageDto) => {
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
        this.linkService.getAllPublicUserLinks(page-1, this.currentUserPage).subscribe((linkPage: LinkPageDto) => {
          this.totalItems = linkPage.totalPage * this.itemsPerPage;
          this.page = linkPage.currentPage + 1;
          this.links = linkPage.links;
        });
      } else {
        this.linkService.getAllUserLinks(page-1,this.currentUserPage).subscribe((linkPage: LinkPageDto) => {
          this.totalItems = linkPage.totalPage * this.itemsPerPage;
          this.page = linkPage.currentPage + 1;
          this.links = linkPage.links;
        });
      }
    }
  }

  getUrl() {
    if (this.currentUserPage != null) {
      return 'http://localhost:8080/lasttask_war_exploded/api/image/' + this.currentUserPage.photoUrl+"/";
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
      username: this.currentUserPage.username,
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
  updatePhoto()
  {
    this.userService.updatePhoto(this.fileToUpload, this.currentUserPage.username).subscribe((photoPath:PhotoPath)=>{
      this.currentUserPage.photoUrl=photoPath.photoPath;
    });
  }
}
