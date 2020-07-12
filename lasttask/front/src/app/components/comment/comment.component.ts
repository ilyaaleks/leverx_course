import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../../model/comment';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  @Input()
  comment:Comment;
  constructor() { }

  ngOnInit(): void {
  }
  getUrl() {
    if (this.comment != null) {
      return 'http://localhost:8080/lasttask_war_exploded/api/image/' + this.comment.author.photoUrl+"/";
    }
  }
}
