import {Component, OnInit} from '@angular/core';
import {Link} from '../../model/link';
import {LinkService} from '../../service/link.service';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import {LinkPageDto} from '../../model/link-page-dto';

@Component({
  selector: 'app-link-page',
  templateUrl: './link-page.component.html',
  styleUrls: ['./link-page.component.css']
})
export class LinkPageComponent implements OnInit {
  itemsPerPage: number = 20;
  totalItems: any;
  page: any;
  previousPage: any;
  public links: Link[];

  constructor(private linkService: LinkService,
              private activatedRoute: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {

      if (params.get('tag') !== null) {
        // this.linkService.getPostsByTag(params.get("tag"), page - 1).subscribe((postPages: PostPageDto) => {
        //   this.totalItems = postPages.totalPage * this.itemsPerPage;
        //   this.page = 1;
        //   this.posts = postPages.posts;
        // })
      } else {
        this.userService.activeUser.subscribe((user: User) => {
          this.linkService.getAllLinksForAuthUser(0, user.id).subscribe(
            (linksPage: LinkPageDto) => {
              this.links = linksPage.links;
              this.totalItems = linksPage.totalPage * this.itemsPerPage;
              this.page = linksPage.currentPage + 1;
            }
          );
        });

      }

    });
  }

  loadPage(page: number) {
    this.activatedRoute.paramMap.subscribe((params) => {
      if (page !== this.previousPage) {
        this.previousPage = page;
        if (params.get('tag') !== null) {
          // this.linkService.getPostsByTag(params.get("tag"), page - 1).subscribe((postPages: PostPageDto) => {
          //   this.totalItems = postPages.totalPage * this.itemsPerPage;
          //   this.page = 1;
          //   this.posts = postPages.posts;
          // })
        } else {
          this.userService.activeUser.subscribe((user: User) => {
            this.linkService.getAllLinksForAuthUser(page - 1, user.id).subscribe(
              (linksPage: LinkPageDto) => {
                this.links = linksPage.links;
                this.totalItems = linksPage.totalPage * this.itemsPerPage;
                this.page = linksPage.currentPage + 1;
              }
            );
          });

        }
      }
    });
  }
}
