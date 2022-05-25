export interface ILoginForm {
  email: string;
  password: string;
}

export interface IUser {
  id: number;
  name: string;
  email: string;
  profile: 'ADMIN' | 'SORTER' | 'FINISHER';
}

export interface ILoginResponse {
  type: string;
  token: string;
  user: IUser;
}

export interface IRegisterForm {
  name: string;
  email: string;
  password: string;
  profile: 'ADMIN' | 'FINISHER' | 'SORTER';
}
