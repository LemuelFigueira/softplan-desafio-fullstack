import { IProcessResponse } from 'services/types/process';

export interface IProcessItemProps {
  process: IProcessResponse;
  onAction?: () => void;
}
