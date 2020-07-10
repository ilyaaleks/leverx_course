import {Link} from './link';

export interface LinkPageDto {
  links: Array<Link>;
  currentPage: number;
  totalPage: number;
}
