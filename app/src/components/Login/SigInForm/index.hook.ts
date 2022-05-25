import { useFormik } from 'formik';

import { useAuth } from 'contexts/Auth';

export function useSignInForm() {
  const { doLogin } = useAuth();

  const pageFormik = useFormik({
    initialValues: {
      hidePassword: true,
    },
    onSubmit: () => {},
  });

  const loginFormik = useFormik({
    initialValues: {
      email: '',
      password: '',
    },
    onSubmit: (values) => {
      doLogin(values);
    },
  });

  const tooglePassword = () => {
    pageFormik.setFieldValue('hidePassword', !pageFormik.values.hidePassword);
  };

  return { loginFormik, pageFormik, tooglePassword };
}
