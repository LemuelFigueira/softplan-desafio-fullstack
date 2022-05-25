import { useFormik } from 'formik';

import { register } from 'services/auth';
import { IRegisterForm } from 'services/types/auth';

export function useRegisterForm() {
  const pageFormik = useFormik({
    initialValues: {
      hidePassword: true,
    },
    onSubmit: () => {},
  });

  const registerFormik = useFormik<IRegisterForm>({
    initialValues: {
      name: '',
      email: '',
      password: '',
      profile: 'SORTER',
    },
    onSubmit: async (values) => {
      await register(values);

      window.location.reload();
    },
  });

  const tooglePassword = () => {
    pageFormik.setFieldValue('hidePassword', !pageFormik.values.hidePassword);
  };

  return { registerFormik, pageFormik, tooglePassword };
}
