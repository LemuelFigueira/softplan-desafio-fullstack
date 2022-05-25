import { ConfirmModal } from 'components/Modal';

import useUserItem from './index.hook';
import { IProcessItemProps } from './index.types';
import './index.scss';

export default function ProcessItem(props: IProcessItemProps) {
  const removerUsuarioModalTarget = `remover-usuario-${props.process.id}`;
  const atualizarUsuarioModalTarget = `atualizar-usuario-${props.process.id}`;

  const { process, onAction } = props;

  const { handleDeleteProcessById, handleUpdateProcessById, updateProcessFormik } = useUserItem(process);

  return (
    <>
      <ConfirmModal
        title="Remover processo"
        dataTarget={removerUsuarioModalTarget}
        onConfirm={() => handleDeleteProcessById(process.id, onAction)}
      >
        <div id="title" className="d-flex flex-column align-items-start justify-content-center">
          <span className="fs-6 fw-bold">Titulo</span>
          <span className="fs-6 fw-light d-block text-truncate">{process.title}</span>
        </div>
        <div id="subtitle" className="d-flex flex-column align-items-start justify-content-center">
          <span className="fs-6 fw-bold">Sub-título</span>
          <span className="fs-6 fw-light d-block text-truncate">{process.subtitle}</span>
        </div>
        <div id="description" className="d-flex flex-column align-items-start justify-content-center">
          <span className="fs-6 fw-bold">Descrição</span>
          <span className="fs-6 fw-light d-block text-truncate">{process.description}</span>
        </div>
      </ConfirmModal>

      <ConfirmModal
        title="Atualizar Processo"
        dataTarget={atualizarUsuarioModalTarget}
        onConfirm={() => handleUpdateProcessById(process.id, onAction)}
      >
        <div className="mb-3">
          <label htmlFor="nameInput" className="form-label">
            Digite o título
          </label>
          <input
            type="text"
            className="form-control"
            id="nameInput"
            placeholder="name@example.com"
            name="title"
            value={updateProcessFormik.values.title}
            onChange={updateProcessFormik.handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="emailInput" className="form-label">
            Digite o sub-título
          </label>
          <input
            type="email"
            className="form-control"
            id="emailInput"
            placeholder="name@example.com"
            name="subtitle"
            value={updateProcessFormik.values.subtitle}
            onChange={updateProcessFormik.handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="descriptionInput" className="form-label">
            Digite a descrição
          </label>
          <div className="d-flex align-items-center gap-2 fs-2">
            <textarea
              disabled
              className="form-control"
              id="descriptionInput"
              placeholder="Descrição"
              name="description"
              value={updateProcessFormik.values.description}
              onChange={updateProcessFormik.handleChange}
            />
          </div>
        </div>
      </ConfirmModal>
      <div className="container-sorter px-2 py-1 shadow">
        <div
          id="id"
          className="d-flex flex align-items-center justify-content-start justify-content-md-start w-100 gap-1"
        >
          <span className="fs-6 fw-bold">#</span>
          <span className="fs-6 fw-light d-block text-truncate">{process.id}</span>
        </div>
        <div id="title" className="d-flex flex-column align-items-start justify-content-center w-100">
          <span className="fs-6 fw-bold">Título</span>
          <span className="fs-6 fw-light text-justify">{process.title}</span>
        </div>
        <div id="subtitle" className="d-flex flex-column align-items-start justify-content-center w-100">
          <span className="fs-6 fw-bold">Sub-Título</span>
          <span className="fs-6 fw-light text-justify">{process.subtitle}</span>
        </div>
        <div id="description" className="d-flex flex-column align-items-start justify-content-center w-100">
          <span className="fs-6 fw-bold">Descrição</span>
          <p className="fs-6 fw-light text-justify">{process.description}</p>
        </div>
        {/* <div id="action" className="d-xs-none d-md-flex">
          <button className="btn btn-primary btn-sm h-10 rounded-2 w-2">
            <span>Ações</span>
          </button>
        </div> */}
        <div id="action" className="d-flex align-items-center justify-content-end gap-3 gap-1-md w-100">
          <i
            className="bi-trash-fill fs-4 text-danger"
            data-bs-toggle="modal"
            data-bs-target={'#' + removerUsuarioModalTarget}
          />
          <i
            className="bi-pencil-fill fs-4 text-secondary"
            data-bs-toggle="modal"
            data-bs-target={'#' + atualizarUsuarioModalTarget}
          />
        </div>
      </div>
    </>
  );
}
