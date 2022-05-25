import { Routes, Route } from 'react-router-dom';

import AdminHomepage from 'pages/Admin/Home';
import FinisherHomePage from 'pages/Finisher/Home';
import { Homepage } from 'pages/Home/index';
import SorterHomePage from 'pages/Sorter/Home';

import Layout from 'components/Layout';

import { useAuth } from 'contexts/Auth';

export function MainRoutes() {
  const { isSigned, user } = useAuth();

  function getRoutes() {
    if (!isSigned || !user) return <Route path="/" element={<Homepage />} />;
    const profile = user.profile;
    switch (profile) {
      case 'ADMIN':
        return <Route path="/" element={<AdminHomepage />} />;
      case 'SORTER':
        return <Route path="/" element={<SorterHomePage />} />;
      case 'FINISHER':
        return <Route path="/" element={<FinisherHomePage />} />;
      default:
        return <Route path="/" element={<Homepage />} />;
    }
  }
  return (
    <Layout>
      <Routes>{getRoutes()}</Routes>
    </Layout>
  );
}
