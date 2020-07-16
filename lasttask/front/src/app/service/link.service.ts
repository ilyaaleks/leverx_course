import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LinkPageDto} from '../model/link-page-dto';
import {BehaviorSubject, Observable, ReplaySubject, Subject} from 'rxjs';
import {Link} from '../model/link';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class LinkService {
  private _newLink: Subject<Link> = new ReplaySubject(1);
  private _updatedLink:Subject<Link> = new ReplaySubject(1);
  private _deletedLinkId:Subject<number>=new ReplaySubject(1);
  private _nameSubject:Subject<LinkPageDto> = new BehaviorSubject(null);
  constructor(private httpClient: HttpClient) {
  }

  getAllPublicLinks(page: number): Observable<LinkPageDto> {
    return this.httpClient.get<LinkPageDto>('/api/link/all?page=' + page + '&size=20&sort=id,DESC');
  }
  getAllPublicUserLinks(page:number, user:User):Observable<LinkPageDto>{
    return this.httpClient.get<LinkPageDto>('/api/link/public/'+user.id+'?page=' + page + '&size=20&sort=id,DESC')
  }
  getAllLinksForAuthUser(page: number, userId: number): Observable<LinkPageDto> {
    return this.httpClient.get<LinkPageDto>('/api/link/protected/'+userId+'?page=' + page + '&size=20&sort=id,DESC');
  }

  getAllUserLinks(page: number,user:User): Observable<LinkPageDto> {
    return this.httpClient.get<LinkPageDto>('/api/link/'+user.username+'?page=' + page + '&size=20&sort=id,DESC');
  }

  updateLink(link: Link): Observable<Link> {
    return this.httpClient.put<Link>('/api/link', link);
  }

  delete(linkId: number): void {
    this.httpClient.delete('/api/link/' + linkId).subscribe(()=>{
      this.deletedLinkId.next(linkId);
    });
  }

  getLinkByName(name:string):Observable<LinkPageDto>{
    return this.httpClient.get<LinkPageDto>('/api/link/name/'+name);
  }

  getLinksByTag(tagId:number, page:number):Observable<LinkPageDto>{
    return this.httpClient.get<LinkPageDto>('/api/link/tag/'+tagId+'?page=' + page + '&size=20&sort=id,DESC');
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

  get nameSubject(): Subject<LinkPageDto> {
    return this._nameSubject;
  }

  set nameSubject(value: Subject<LinkPageDto>) {
    this._nameSubject = value;
  }
}
