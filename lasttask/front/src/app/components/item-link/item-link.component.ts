import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../../model/comment';

@Component({
  selector: 'app-item-link',
  templateUrl: './item-link.component.html',
  styleUrls: ['./item-link.component.css']
})
export class ItemLinkComponent implements OnInit {
  @Input()
  id;
  @Input()
  author;
  @Input()
  url;
  @Input()
  name;
@Input()
countOfLikes;
@Input()
countOfDislikes;
@Input()
tags;
private comments:Comment[];

  constructor() { }

  ngOnInit(): void {
  }

}
