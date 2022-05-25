import Login from 'components/Login/index';

import { useTheme } from 'contexts/Theme';

import styles from './Home.module.scss';

export function Homepage() {
  const { theme } = useTheme();

  return (
    <>
      <div className={styles.home} data-theme={theme}>
        <Login />
      </div>
    </>
  );
}
