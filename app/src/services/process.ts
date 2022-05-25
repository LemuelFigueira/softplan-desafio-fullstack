import api from './api';
import { IGenericQuerySearch } from './types/generic';
import {
  IProcessByUserIdRequestForm,
  IProcessForm,
  IProcessPaginatedResponse,
  IProcessResponse,
} from './types/process';

export async function getProcessById(id: number): Promise<IProcessResponse> {
  return await api.get(`/process/${id}`);
}

export async function getAllProcesses(): Promise<IProcessResponse[]> {
  return await api.get('/process');
}

export async function getAllProcessesPaginated({
  page = 0,
  size = 0,
  query = '',
}: IGenericQuerySearch): Promise<IProcessPaginatedResponse> {
  const urlParams = new URLSearchParams('');

  urlParams.set('page', page.toString());
  urlParams.set('size', size.toString());
  urlParams.set('query', query);

  const { data } = await api.get<IProcessPaginatedResponse>('/process/search?' + urlParams.toString());

  return data;
}

export async function getAllProcessByUserId({
  userId = 0,
  page = 0,
  size = 0,
  query = '',
}: IProcessByUserIdRequestForm) {
  const urlParams = new URLSearchParams('');

  urlParams.set('userId', userId.toString());
  urlParams.set('page', page.toString());
  urlParams.set('size', size.toString());
  urlParams.set('query', query);

  const { data } = await api.get<IProcessPaginatedResponse>(`/process-user/search?` + urlParams.toString());

  return data;
}

export async function deleteProcessById(id: number) {
  return await api.delete(`/process/${id}`);
}

export async function createProcess(data: IProcessForm) {
  return await api.post('/process', data);
}

export async function updateProcessById(id: number, data: IProcessForm) {
  return await api.put(`/process/${id}`, data);
}
