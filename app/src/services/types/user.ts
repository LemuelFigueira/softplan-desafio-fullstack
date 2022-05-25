import { IUser } from './auth';

export interface IFindUserByAnyNameOrProfileOrEmailForm {
  page?: number;
  size?: number;
  query?: string;
}

export interface IFindUserByAnyNameOrProfileOrEmailResponse {
  isFirst: boolean;
  isLast: boolean;
  totalPages: number;
  users: IUser[];
  totalElements: number;
}
