import { IProps } from './index.types';

export function ConfirmModal(props: IProps) {
  const { dataTarget = 'exampleModal', title = 'Remover usuário', onConfirm, children } = props;
  return (
    <>
      <div className="modal fade" id={dataTarget} tabIndex={-1} aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div className="modal-dialog modal-dialog-centered modal-dialog-centered-scrollable">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                {title}
              </h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">{children}</div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                Cancelar
              </button>
              <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={onConfirm}>
                Confirmar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
