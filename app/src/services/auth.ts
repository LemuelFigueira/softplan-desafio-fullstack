import api from './api';
import { ILoginForm, ILoginResponse, IRegisterForm } from './types/auth';

export async function login(form: ILoginForm): Promise<ILoginResponse> {
  const { data } = await api.post('/auth', form);

  return data;
}

export async function register(form: IRegisterForm): Promise<void> {
  await api.post('/user/create', form);
}
