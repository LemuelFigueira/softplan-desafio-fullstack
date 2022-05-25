import { useEffect, useState } from 'react';

import { useFormik } from 'formik';

import { IGenericQuerySearch } from 'services/types/generic';
import { IFindUserByAnyNameOrProfileOrEmailResponse } from 'services/types/user';
import { findUserByAnyNameOrProfileOrEmail } from 'services/user';

export default function useAdminHome() {
  const [isLoading, setIsLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(-1);
  const [data, setData] = useState<IFindUserByAnyNameOrProfileOrEmailResponse | null>();

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
      const response = await findUserByAnyNameOrProfileOrEmail(values);

      if (!response.users.length) {
        return;
      }

      const users = data?.users || [];
      const newUsers = users.concat(response.users);

      response.users = newUsers;

      setData(response);
    };
    handleResquest();
  }, [currentPage]);

  async function handleLoadUsers(form: IGenericQuerySearch) {
    if (isLoading) {
      return;
    }
    setIsLoading(true);
    form.page = 0;
    const response = await findUserByAnyNameOrProfileOrEmail(form);
    setData(response);

    setIsLoading(false);
    return response;
  }

  return {
    pageFormik,
    data,
    handleLoadUsers: () => handleLoadUsers(pageFormik.values),
    setCurrentPage,
    isLoading,
  };
}
