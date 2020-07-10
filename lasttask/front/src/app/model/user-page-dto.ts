import {User} from './user';

export interface UserPageDto {
  users: Array<User>;
  currentPage: number;
  totalPage: number;
}
