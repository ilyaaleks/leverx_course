import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LikeDislikeService {

  constructor(private httpClient: HttpClient) { }

  setLike(linkId: number): Observable<any> {
    return this.httpClient.get('api/rating/like/'+linkId);
  }
  setDislike(linkId:number):Observable<any>{
    return this.httpClient.get("api/rating/dislike/"+linkId);
  }
}
