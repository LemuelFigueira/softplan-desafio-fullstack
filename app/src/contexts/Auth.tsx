import { createContext, useContext, useState } from 'react';

import api from 'services/api';
import { login } from 'services/auth';
import { ILoginForm, IUser } from 'services/types/auth';

import { useToast } from './Toast';

type IAuthContext = {
  user: IUser | null;
  isSigned: boolean;
  isAdmin: boolean;
  isSorter: boolean;
  isFinisher: boolean;
  // eslint-disable-next-line no-unused-vars
  doLogin: (form: ILoginForm) => void;
  logOut: () => void;
};

const Auth = createContext({} as IAuthContext);

export function AuthProvider({ children }: any) {
  const { toast } = useToast();
  const [user, setUser] = useState<IUser | null>(() => {
    const storagedUser = sessionStorage.getItem('auth-user');
    const storagedToken = sessionStorage.getItem('auth-token');

    if (!storagedUser) {
      return null;
    }

    const parsedStoragedUser: IUser = JSON.parse(storagedUser);

    api.defaults.headers.common['Authorization'] = `Bearer ${storagedToken}`;

    return parsedStoragedUser;
  });

  async function doLogin(form: ILoginForm) {
    try {
      const response = await login(form);

      setUser(response.user);

      toast.success(`Seja bem vindo *${response.user.name}*`);

      sessionStorage.setItem('auth-user', JSON.stringify(response.user));
      sessionStorage.setItem('auth-token', response.token);

      api.defaults.headers.common['Authorization'] = `Bearer ${response.token}`;

      return response;
    } catch (error) {
      if (error instanceof Error) toast.error('Login ou senha inválidos');
    }
  }

  function getUserRole() {
    if (!user) return null;

    return user.profile;
  }

  function logOut() {
    setUser(null);
    sessionStorage.removeItem('auth-user');
    sessionStorage.removeItem('auth-token');
  }

  return (
    <Auth.Provider
      value={{
        user,
        isSigned: Boolean(user),
        isAdmin: getUserRole() === 'ADMIN',
        isSorter: getUserRole() === 'SORTER',
        isFinisher: getUserRole() === 'FINISHER',
        // eslint-disable-next-line no-unused-vars
        doLogin,
        logOut,
      }}
    >
      {children}
    </Auth.Provider>
  );
}

export function useAuth() {
  const context = useContext(Auth);
  if (!context) {
    throw new Error('useTheme must be used within a ThemeProvider');
  }
  return context;
}
