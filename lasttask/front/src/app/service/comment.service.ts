import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CommentPageDto} from '../model/comment-page-dto';
import {Comment} from '../model/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) { }
  getCommentsForLink(linkId:number, page:number):Observable<CommentPageDto>
  {
    return this.httpClient.get<CommentPageDto>("/api/comment/"+linkId+"?page="+page+"&size=20&sort=id,DESC");
  }
  saveComment(comment:Comment):Observable<Comment>
  {
    return this.httpClient.post<Comment>("/api/comment", comment);
  }
  updateComment(comment:Comment):Observable<Comment>
  {
    return this.httpClient.put<Comment>("/api/comment", comment);
  }
  deleteComment(commentId:number):Observable<any>
  {
    return this.httpClient.delete("/api/comment/"+commentId);
  }
}
