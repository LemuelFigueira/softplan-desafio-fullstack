import { useFormik } from 'formik';

import { IRegisterForm, IUser } from 'services/types/auth';
import { deleteUserById, updateUserById } from 'services/user';

export default function useUserItem(props: IUser) {
  const updateUserFormik = useFormik<IRegisterForm>({
    initialValues: {
      email: props.email,
      name: props.name,
      password: '',
      profile: props.profile,
    },
    onSubmit: () => {},
  });

  const pageFormik = useFormik({
    initialValues: {
      hidePassword: true,
    },
    onSubmit: () => {},
  });

  const tooglePassword = () => {
    pageFormik.setFieldValue('hidePassword', !pageFormik.values.hidePassword);
  };

  async function handleDeleteUserById(id: number, cb?: () => void) {
    await deleteUserById(id);
    if (cb) cb();
  }

  async function handleUpdateUserById(id: number, cb?: () => void) {
    await updateUserById(id, updateUserFormik.values);

    if (cb) cb();
  }

  return {
    updateUserFormik,
    tooglePassword,
    handleDeleteUserById,
    handleUpdateUserById,
    pageFormik,
  };
}
