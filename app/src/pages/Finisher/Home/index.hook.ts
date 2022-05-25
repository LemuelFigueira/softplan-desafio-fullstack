import { useEffect, useState } from 'react';

import { useFormik } from 'formik';

import { useAuth } from 'contexts/Auth';

import { getAllProcessByUserId } from 'services/process';
import { IProcessByUserIdRequestForm, IProcessPaginatedResponse } from 'services/types/process';

export default function useFinisherHome() {
  const [isLoading, setIsLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(-1);
  const [data, setData] = useState<IProcessPaginatedResponse | null>();

  const { user } = useAuth();

  useEffect(() => {
    if (user) {
      pageFormik.setFieldValue('userId', user.id);
    }
  }, [user]);

  const pageFormik = useFormik<IProcessByUserIdRequestForm>({
    initialValues: {
      userId: 0,
      query: '',
      page: 0,
      size: 20,
    },
    onSubmit: () => {},
  });

  useEffect(() => {
    const handleResquest = async () => {
      const values = pageFormik.values;
      const _currentPage = currentPage < 0 ? 0 : currentPage;
      values.page = _currentPage;
      const response = await getAllProcessByUserId(values);

      if (!response.processes.length) {
        return;
      }

      const processes = data?.processes || [];
      const newProcesses = processes.concat(response.processes);

      response.processes = newProcesses;

      setData(response);
    };
    handleResquest();
  }, [currentPage]);

  async function handleLoadProcesses(form: IProcessByUserIdRequestForm) {
    if (isLoading) {
      return;
    }
    setIsLoading(true);
    form.page = 0;
    const response = await getAllProcessByUserId(form);
    setData(response);

    setIsLoading(false);
    return response;
  }

  return {
    pageFormik,
    data,
    handleLoadProcesses: () => handleLoadProcesses(pageFormik.values),
    setCurrentPage,
    isLoading,
  };
}
