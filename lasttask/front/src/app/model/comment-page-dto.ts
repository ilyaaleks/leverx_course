import {Comment} from './comment';

export interface CommentPageDto {
  comments: Array<Comment>;
  currentPage: number;
  totalPage: number;
}
