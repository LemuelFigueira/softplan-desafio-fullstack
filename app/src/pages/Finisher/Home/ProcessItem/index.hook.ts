import { useFormik } from 'formik';

import { deleteProcessById, updateProcessById } from 'services/process';
import { IProcessForm, IProcessResponse } from 'services/types/process';

export default function useItem(props: IProcessResponse) {
  const updateProcessFormik = useFormik<IProcessForm>({
    initialValues: {
      description: props.description,
      subtitle: props.subtitle,
      title: props.title,
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

  async function handleDeleteProcessById(id: number, cb?: () => void) {
    await deleteProcessById(id);
    if (cb) cb();
  }

  async function handleUpdateProcessById(id: number, cb?: () => void) {
    await updateProcessById(id, updateProcessFormik.values);

    if (cb) cb();
  }

  return {
    updateProcessFormik,
    tooglePassword,
    handleDeleteProcessById,
    handleUpdateProcessById,
    pageFormik,
  };
}
