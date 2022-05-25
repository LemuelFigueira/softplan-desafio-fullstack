import React from 'react';

import { useLogin } from './index.hook';
import styles from './Login.module.scss';
import RegisterForm from './RegisterForm';
import SiginForm from './SigInForm';

const Login: React.FC = () => {
  const { showSignUpForm, goToSignUpForm, goToSignInForm } = useLogin();
  return (
    <div className={styles.container}>
      {showSignUpForm ? (
        <RegisterForm goToSignInForm={goToSignInForm} />
      ) : (
        <SiginForm goToSignUpForm={goToSignUpForm} />
      )}
    </div>
  );
};
export default Login;
