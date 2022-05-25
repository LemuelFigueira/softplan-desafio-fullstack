import { ConfirmModal } from 'components/Modal';

import useUserItem from './index.hook';
import { IUserItemProps } from './index.types';
import './index.scss';

export default function UserItem(props: IUserItemProps) {
  const removerUsuarioModalTarget = `remover-usuario-${props.user.id}`;
  const atualizarUsuarioModalTarget = `atualizar-usuario-${props.user.id}`;

  const { user, onAction } = props;

  const { handleDeleteUserById, handleUpdateUserById, updateUserFormik, pageFormik, tooglePassword } =
    useUserItem(user);

  return (
    <>
      <ConfirmModal
        title="Remover usuário"
        dataTarget={removerUsuarioModalTarget}
        onConfirm={() => handleDeleteUserById(user.id, onAction)}
      >
        <div id="name" className="d-flex flex-column align-items-start justify-content-center">
          <span className="fs-6 fw-bold">Nome</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.name}</span>
        </div>
        <div id="email" className="d-flex flex-column align-items-start justify-content-center">
          <span className="fs-6 fw-bold">Email</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.email}</span>
        </div>
        <div id="profile" className="d-flex flex-column align-items-start justify-content-center">
          <span className="fs-6 fw-bold">Profile</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.profile}</span>
        </div>
      </ConfirmModal>

      <ConfirmModal
        title="Atualizar usuário"
        dataTarget={atualizarUsuarioModalTarget}
        onConfirm={() => handleUpdateUserById(user.id, onAction)}
      >
        <div className="mb-3">
          <label htmlFor="nameInput" className="form-label">
            Digite o nome
          </label>
          <input
            type="text"
            className="form-control"
            id="nameInput"
            placeholder="name@example.com"
            name="name"
            value={updateUserFormik.values.name}
            onChange={updateUserFormik.handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="emailInput" className="form-label">
            Digite o email
          </label>
          <input
            type="email"
            className="form-control"
            id="emailInput"
            placeholder="name@example.com"
            name="email"
            value={updateUserFormik.values.email}
            onChange={updateUserFormik.handleChange}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="passwordInput" className="form-label">
            Digite a senha
          </label>
          <div className="d-flex align-items-center gap-2 fs-2">
            <input
              type={pageFormik.values.hidePassword == true ? 'password' : 'text'}
              className="form-control"
              id="passwordInput"
              placeholder="senha"
              name="password"
              value={updateUserFormik.values.password}
              onChange={updateUserFormik.handleChange}
            />
            <div className="w-auto" onClick={() => tooglePassword()}>
              {pageFormik.values.hidePassword == true ? (
                <i className="bi bi-eye-slash" style={{ cursor: 'pointer' }} id="togglePassword"></i>
              ) : (
                <i className="bi bi-eye" style={{ cursor: 'pointer' }} id="togglePassword"></i>
              )}
            </div>
          </div>
        </div>
      </ConfirmModal>
      <div className="container-user px-2 py-1 shadow">
        <div
          id="id"
          className="d-flex flex align-items-center justify-content-start justify-content-md-start w-100 gap-1"
        >
          <span className="fs-6 fw-bold">#</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.id}</span>
        </div>
        <div id="name" className="d-flex flex-column align-items-start justify-content-center w-100">
          <span className="fs-6 fw-bold">Nome</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.name}</span>
        </div>
        <div id="email" className="d-flex flex-column align-items-start justify-content-center w-100">
          <span className="fs-6 fw-bold">Email</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.email}</span>
        </div>
        <div id="profile" className="d-flex flex-column align-items-start justify-content-center w-100">
          <span className="fs-6 fw-bold">Profile</span>
          <span className="fs-6 fw-light d-block text-truncate">{user.profile}</span>
        </div>
        <div id="action-i" className="d-flex align-items-center justify-content-end gap-3 w-100">
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
