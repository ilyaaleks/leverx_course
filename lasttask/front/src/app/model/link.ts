import {Status} from './status.enum';
import {User} from './user';
import {Tag} from './tag';

export interface Link {
  id: number;
  name: string;
  url: string;
  status: Status;
  countOfLikes: number;
  countOfDislikes: number;
  dateOfCreation: string;
  dateOfChange: string;
  user: User;
  tags: Array<Tag>;
  comments: Array<Comment>;
}
