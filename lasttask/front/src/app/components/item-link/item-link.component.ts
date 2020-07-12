import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../../model/comment';
import {Link} from '../../model/link';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {UserService} from '../../service/user.service';
import {CommentService} from '../../service/comment.service';
import {LikeDislikeService} from '../../service/like-dislike.service';
import {LinkService} from '../../service/link.service';
import {AddLinkComponent} from '../add-link/add-link.component';
import {User} from '../../model/user';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CommentPageDto} from '../../model/comment-page-dto';

@Component({
  selector: 'app-item-link',
  templateUrl: './item-link.component.html',
  styleUrls: ['./item-link.component.css']
})
export class ItemLinkComponent implements OnInit {
  @Input()
  link: Link;
  public comments:Comment[];
  public activeUser: boolean;
  public countOfLikes: number;
  public countOfDislike: number;
  public commentForm: FormGroup;
  itemsPerPage: number = 20;
  totalItems: any;
  page: any;
  previousPage: any;

  constructor(public dialog: MatDialog,
              private userService: UserService,
              private commentService: CommentService,
              private likeService: LikeDislikeService,
              private linkService: LinkService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.userService.activeUser.subscribe((user: User) => {
      if (this.link.user.id === user.id) {
        this.activeUser = true;
      } else {
        this.activeUser = false;
      }
    });
    this.commentForm = new FormGroup({
      comment: new FormControl('', [Validators.required])
    });
    this.commentService.getCommentsForLink(this.link.id, 0).subscribe((commentPage: CommentPageDto) => {
      this.totalItems = commentPage.totalPage*this.itemsPerPage;
      this.page = 1;
      this.comments = commentPage.comments;
    });
  }

  public deleteLink() {
    this.linkService.delete(this.link.id);
  }

  public updateLink() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '50%';
    dialogConfig.data = this.link;
    this.dialog.open(AddLinkComponent, dialogConfig);
  }

  authorId(): String {
    return 'user/' + this.link.user.id;
  }

  setLike(): void {

  }

  setDislike(): void {

  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.commentService.getCommentsForLink(this.link.id,page - 1).subscribe((commentPage: CommentPageDto) => {
        this.totalItems = commentPage.totalPage*this.itemsPerPage;
        this.page = commentPage.currentPage + 1;
        this.comments = commentPage.comments;
      });
    }
  }

  addComment() {
    this.userService.activeUser.subscribe((user:User)=>{
      let comment:Comment={
        id:null,
        author:this.link.user,
        text:this.commentForm.controls['comment'].value,
        date:null,
        link:this.link
      }
      this.commentService.saveComment(comment).subscribe((comment: Comment) => {
        this.commentForm.controls['comment'].reset("")
        if (this.comments.length === 20 && this.page === 1 && comment.link.id === this.link.id) {
          this.comments.unshift(comment);
          this.comments.splice(4, 1);
          this.totalItems++;
        } else if (this.page !== 1) {
          this.totalItems++;
        } else {
          this.totalItems++;
          this.comments.unshift(comment);
        }

      },error => {
        this.openSnackBar(error.message,"Close");
      })
    })
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
