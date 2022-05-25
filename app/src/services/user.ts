import api from './api';
import { IRegisterForm } from './types/auth';
import { IGenericQuerySearch } from './types/generic';
import { IFindUserByAnyNameOrProfileOrEmailResponse } from './types/user';

export async function findUserByAnyNameOrProfileOrEmail({
  page = 0,
  size = 0,
  query = '',
}: IGenericQuerySearch): Promise<IFindUserByAnyNameOrProfileOrEmailResponse> {
  const urlParams = new URLSearchParams('');

  urlParams.set('page', page.toString());
  urlParams.set('size', size.toString());
  urlParams.set('query', query);

  const { data } = await api.get<IFindUserByAnyNameOrProfileOrEmailResponse>('/user/search?' + urlParams.toString());

  return data;
}

export async function deleteUserById(id: number) {
  await api.delete(`/user/${id}`);
}

export async function updateUserById(id: number, form: IRegisterForm) {
  await api.put(`/user/${id}`, form);
}
