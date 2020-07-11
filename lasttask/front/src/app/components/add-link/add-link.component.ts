import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../service/user.service';
import {LinkService} from '../../service/link.service';
import {Router} from '@angular/router';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Link} from '../../model/link';
import {User} from '../../model/user';
import {Status} from '../../model/status.enum';
import {Tag} from '../../model/tag';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-link',
  templateUrl: './add-link.component.html',
  styleUrls: ['./add-link.component.css']
})
export class AddLinkComponent implements OnInit {
  public postForm: FormGroup;
  public status: any;

  constructor(private userService: UserService,
              private linkService: LinkService,
              private router: Router,
              public dialogRef: MatDialogRef<AddLinkComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private _snackBar: MatSnackBar
  ) {
  }

  ngOnInit() {
    this.postForm = new FormGroup({
      link: new FormControl('', [Validators.required]),
      tags: new FormControl('', [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      status: new FormControl(null, [Validators.required]),
    })
    ;
    if (this.data !== null) {
      let link: Link = this.data.link;
      this.postForm.controls['link'].setValue(link.url);
      this.postForm.controls['name'].setValue(link.name);
      let resultStr = '';
      for (let i of link.tags) {
        resultStr += i.name + ' ';
      }
      this.postForm.controls['tags'].setValue(resultStr);
    }
  }

  public addPost(): void {
    let post: Link = {
      id: null,
      name: this.postForm.controls['name'].value,
      url: this.postForm.controls['link'].value
    };
    let status: Status;
    switch (this.postForm.controls['status'].value) {
      case 'public': {
        status = Status.PUBLIC;
        break;
      }
      case 'protected': {
        status = Status.PROTECTED;
        break;
      }
      case 'private': {
        status = Status.PRIVATE;
        break;
      }
      default: {
        status = Status.PRIVATE;
        break;
      }
    }
    post.status = status;
    let tagString: string = this.postForm.controls['tags'].value;
    let tags: Array<Tag> = new Array<Tag>();
    for (let i of tagString.split(' ')) {
      let currentTag: Tag = {
        name: i,
      };
      tags.push(currentTag);
    }
    post.tags = tags;
    this.linkService.saveLink(post).subscribe(
      (link: Link) => {
        this.linkService.newLink.next(link);
      }, error => {
        this.openSnackBar(error.message, 'Close');
      }
    );

  }

  public update() {
    this.userService.activeUser.subscribe((user: User) => {
      let post: Link = {
        id: this.data.link.id,
        name: this.postForm.controls['name'].value,
        url: this.postForm.controls['link'].value,
        user: user,
      };
      let tagString: string = this.postForm.controls['tags'].value;
      let tags: Array<Tag> = new Array<Tag>();
      for (let i of tagString.split(' ')) {
        let currentTag: Tag = {
          name: i,
        };
        tags.push(currentTag);
      }
      post.tags = tags;
      this.linkService.updateLink(post).subscribe((link: Link) => {
        this.linkService.updatedLink.next(link);
      }, error => {
        this.openSnackBar(error.message, 'Close');
      });
    });


  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
