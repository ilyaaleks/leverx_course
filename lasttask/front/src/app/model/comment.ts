import {User} from './user';
import {Link} from './link';

export interface Comment {
  id: number;
  author: User;
  text: string;
  date: string;
  link: Link;
}
