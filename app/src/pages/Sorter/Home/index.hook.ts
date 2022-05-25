import { useEffect, useState } from 'react';

import { useFormik } from 'formik';

import { getAllProcessesPaginated } from 'services/process';
import { IGenericQuerySearch } from 'services/types/generic';
import { IProcessPaginatedResponse } from 'services/types/process';

export default function useSorterHome() {
  const [isLoading, setIsLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(-1);
  const [data, setData] = useState<IProcessPaginatedResponse | null>();

  const pageFormik = useFormik({
    initialValues: {
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
      const response = await getAllProcessesPaginated(values);

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

  async function handleLoadProcesses(form: IGenericQuerySearch) {
    if (isLoading) {
      return;
    }
    setIsLoading(true);
    form.page = 0;
    const response = await getAllProcessesPaginated(form);
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
