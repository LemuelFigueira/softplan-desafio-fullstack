import { useRegisterForm } from './index.hook';
import { IProps } from './index.types';

export default (props: IProps) => {
  const { goToSignInForm } = props;

  const { registerFormik, pageFormik, tooglePassword } = useRegisterForm();

  return (
    <div>
      <div className="d-flex">
        <i
          onClick={goToSignInForm}
          className="bi-arrow-left"
          style={{ fontSize: '2rem', color: 'cornflowerblue', cursor: 'pointer' }}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="nameInput" className="form-label">
          Digite seu nome
        </label>
        <input
          type="text"
          className="form-control"
          id="nameInput"
          placeholder="name@example.com"
          name="name"
          onChange={registerFormik.handleChange}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="emailInput" className="form-label">
          Digite um email
        </label>
        <input
          type="email"
          className="form-control"
          id="emailInput"
          placeholder="name@example.com"
          name="email"
          onChange={registerFormik.handleChange}
        />
      </div>
      <div className="mb-3">
        <select
          className="form-select"
          aria-label="Default select example"
          name="profile"
          onChange={(e) => registerFormik.setFieldValue('profile', e.target.value)}
        >
          <option>Selecione um perfil</option>
          <option value={'ADMIN'} onBlur={() => registerFormik.setFieldTouched('profile', true)}>
            ADMIN
          </option>
          <option value={'SORTER'} onBlur={() => registerFormik.setFieldTouched('profile', true)}>
            SORTER
          </option>
          <option value={'FINISHER'} onBlur={() => registerFormik.setFieldTouched('profile', true)}>
            FINISHER
          </option>
        </select>
      </div>
      <div className="mb-3">
        <label htmlFor="passwordInput" className="form-label">
          Digite uma senha
        </label>
        <div className="d-flex align-items-center gap-2 fs-2">
          <input
            type={pageFormik.values.hidePassword == true ? 'password' : 'text'}
            className="form-control"
            id="passwordInput"
            placeholder="senha"
            name="password"
            onChange={registerFormik.handleChange}
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
      <div className="mb-3">
        <button className="btn btn-primary w-100" onClick={() => registerFormik.handleSubmit()}>
          Registrar <i className="bi-arrow-up-circle" />
        </button>
      </div>
    </div>
  );
};
