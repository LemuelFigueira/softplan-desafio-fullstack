import { IUser } from 'services/types/auth';

export interface IUserItemProps {
  user: IUser;
  onAction?: () => void;
}
