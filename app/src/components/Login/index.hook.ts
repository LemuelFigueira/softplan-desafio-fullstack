import { useFormik } from 'formik';

export function useLogin() {
  const formik = useFormik({
    initialValues: {
      showSignUpForm: false,
    },
    onSubmit: () => {},
  });

  const goToSignUpForm = () => {
    formik.setFieldValue('showSignUpForm', true);
  };

  const goToSignInForm = () => {
    formik.setFieldValue('showSignUpForm', false);
  };

  return {
    showSignUpForm: formik.values.showSignUpForm,
    goToSignUpForm,
    goToSignInForm,
  };
}
