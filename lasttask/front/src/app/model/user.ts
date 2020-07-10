import {Link} from './link';

export interface User {
  id: number;
  lastName: string;
  name: string;
  username: string;
  email: string;
  photoUrl: string;
  password?:string;
  links: Array<Link>;
  comments: Array<Comment>;
}
