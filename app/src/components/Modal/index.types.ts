export interface IProps {
  dataTarget: string;
  title?: string;
  onConfirm?: () => void;
  children?: React.ReactNode;
}
