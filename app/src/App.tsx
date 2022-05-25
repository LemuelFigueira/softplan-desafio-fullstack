import { BrowserRouter } from 'react-router-dom';

import { AuthProvider } from 'contexts/Auth';
import { ThemeProvider } from 'contexts/Theme';
import { ToastProvider } from 'contexts/Toast';

import { MainRoutes } from './routes';

import 'styles/global.scss';

export function App() {
  return (
    <ToastProvider>
      <AuthProvider>
        <ThemeProvider>
          <BrowserRouter>
            <MainRoutes />
          </BrowserRouter>
        </ThemeProvider>
      </AuthProvider>
    </ToastProvider>
  );
}
