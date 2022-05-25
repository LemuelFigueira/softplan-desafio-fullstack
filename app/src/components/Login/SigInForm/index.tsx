import { useSignInForm } from './index.hook';
import { IProps } from './index.types';

export default (props: IProps) => {
  const { goToSignUpForm } = props;

  const { loginFormik, pageFormik, tooglePassword } = useSignInForm();

  return (
    <div>
      <div className="mb-3">
        <label htmlFor="exampleFormControlInput1" className="form-label">
          Digite um email
        </label>
        <input
          type="email"
          className="form-control"
          id="exampleFormControlInput1"
          placeholder="name@example.com"
          name="email"
          onChange={loginFormik.handleChange}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="exampleFormControlTextarea1" className="form-label">
          Digite uma senha
        </label>
        <div className="d-flex align-items-center gap-2 fs-2">
          <input
            type={pageFormik.values.hidePassword ? 'password' : 'text'}
            className="form-control"
            id="exampleFormControlInput2"
            placeholder="senha"
            name="password"
            onChange={loginFormik.handleChange}
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

      <div className="mb-3 d-inline-flex flex-column gap-1">
        <button onClick={goToSignUpForm} type="button" className="btn btn-outline-warning w-100">
          Registrar <i className="bi-arrow-up-circle" />
        </button>
        <button type="button" className="btn btn-primary w-100" onClick={() => loginFormik.handleSubmit()}>
          Entrar <i className="bi-arrow-right-circle" />
        </button>
      </div>
    </div>
  );
};
