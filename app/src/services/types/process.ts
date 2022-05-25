import { IGenericQuerySearch } from './generic';

export interface IProcessForm {
  title: string;
  subtitle: string;
  description: string;
}

export interface IProcessResponse {
  id: number;
  title: string;
  subtitle: string;
  description: string;
}

export interface IProcessPaginatedResponse {
  processes: IProcessResponse[];
  isFirst: boolean;
  isLast: boolean;
  totalPages: number;
  totalElements: number;
}

export interface IProcessByUserIdRequestForm extends IGenericQuerySearch {
  userId: number;
}
