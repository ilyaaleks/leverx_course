import {Component, OnInit} from '@angular/core';
import {Link} from '../../model/link';
import {LinkService} from '../../service/link.service';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import {LinkPageDto} from '../../model/link-page-dto';
import {MatSnackBar} from '@angular/material/snack-bar';

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
              private userService: UserService,
              private _snackBar: MatSnackBar
              ) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {

      if (params.get('tag') !== null) {
        this.linkService.getLinksByTag(Number(params.get("tag")), 0).subscribe((linkPages: LinkPageDto) => {
          this.totalItems = linkPages.totalPage * this.itemsPerPage;
          this.page = 1;
          this.links = linkPages.links;
        },error =>
        {
          this.openSnackBar('Something went wrong: ' + error.message, 'Close');
        })
      } else {
        this.userService.activeUser.subscribe((user: User) => {
          this.linkService.getAllLinksForAuthUser(0, user.id).subscribe(
            (linksPage: LinkPageDto) => {
              this.links = linksPage.links;
              this.totalItems = linksPage.totalPage * this.itemsPerPage;
              this.page = linksPage.currentPage + 1;
            },error =>
            {
              this.openSnackBar('Something went wrong: ' + error.message, 'Close');
            }
          );
        });

      }
      this.linkService.nameSubject.subscribe((linkPage:LinkPageDto)=>{
        if(linkPage!==null)
        {
          this.links=linkPage.links;
          this.totalItems=linkPage.totalPage*this.itemsPerPage;
          this.page=linkPage.currentPage+1;
          this.linkService.nameSubject.next(null);
        }
      })

    });
    this.linkService.deletedLinkId.subscribe((linkId:number)=>{
        if(linkId!==null && linkId!==0)
        {
          this.links.forEach( (item, index) => {
            if(item.id === linkId) this.links.splice(index,1);
          });
          this.linkService.deletedLinkId.next(null);
        }
    },
      error =>
      {
        this.openSnackBar('Something went wrong: ' + error.message, 'Close');
      })
  }

  loadPage(page: number) {
    this.activatedRoute.paramMap.subscribe((params) => {
      if (page !== this.previousPage) {
        this.previousPage = page;
        if (params.get('tag') !== null) {
          this.linkService.getLinksByTag(Number(params.get("tag")), page - 1).subscribe((linkPage: LinkPageDto) => {
            this.totalItems = linkPage.totalPage * this.itemsPerPage;
            this.page = 1;
            this.links = linkPage.links;
          },error =>
          {
            this.openSnackBar('Something went wrong: ' + error.message, 'Close');
          })
        } else {
          this.userService.activeUser.subscribe((user: User) => {
            this.linkService.getAllLinksForAuthUser(page - 1, user.id).subscribe(
              (linksPage: LinkPageDto) => {
                this.links = linksPage.links;
                this.totalItems = linksPage.totalPage * this.itemsPerPage;
                this.page = linksPage.currentPage + 1;
              },
              error =>
              {
                this.openSnackBar('Something went wrong: ' + error.message, 'Close');
              }
            );
          });

        }
      }
    });
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
