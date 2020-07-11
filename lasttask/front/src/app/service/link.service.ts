import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LinkPageDto} from '../model/link-page-dto';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {Link} from '../model/link';

@Injectable({
  providedIn: 'root'
})
export class LinkService {
  private _newLink: Subject<Link> = new ReplaySubject(1);
  private _updatedLink:Subject<Link> = new ReplaySubject(1);
  private _deletedLinkId:Subject<number>=new ReplaySubject(1);
  constructor(private httpClient: HttpClient) {
  }

  getAllPublicLinks(page: number): Observable<LinkPageDto> {
    return this.httpClient.get<LinkPageDto>('/api/link/all?page=' + page + '&size=20&sort=id,DESC');
  }

  getAllLinksForAuthUser(page: number, userId: number): Observable<LinkPageDto> {
    return this.httpClient.get<LinkPageDto>('/api/link/protected/'+userId+'?page=' + page + '&size=20&sort=id,DESC');
  }

  getAllUserLinks(page: number): Observable<LinkPageDto> {
    return this.httpClient.get<LinkPageDto>('/api/link?page=' + page + '&size=20&sort=id,DESC');
  }

  updateLink(link: Link): Observable<Link> {
    return this.httpClient.put<Link>('/api/link', link);
  }

  delete(linkId: number): void {
    this.httpClient.delete('/api/link/' + linkId).subscribe(()=>{
      this.deletedLinkId.next(linkId);
    });
  }

  saveLink(link: Link): Observable<Link> {
    return this.httpClient.post<Link>('/api/link', link);
  }

  get newLink(): Subject<Link> {
    return this._newLink;
  }

  set newLink(value: Subject<Link>) {
    this._newLink = value;
  }

  get updatedLink(): Subject<Link> {
    return this._updatedLink;
  }

  set updatedLink(value: Subject<Link>) {
    this._updatedLink = value;
  }

  get deletedLinkId(): Subject<number> {
    return this._deletedLinkId;
  }

  set deletedLinkId(value: Subject<number>) {
    this._deletedLinkId = value;
  }
}
