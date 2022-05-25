import { useRef } from 'react';

import useInfiniteScroll from 'hooks/useInfiniteScroll';

import useAdminHome from './index.hook';
import UserItem from './UserItem';

import './index.scss';

export default function Home() {
  const { data, isLoading, pageFormik, handleLoadUsers, setCurrentPage } = useAdminHome();
  const loaderRef = useRef(null);

  useInfiniteScroll(loaderRef, () => setCurrentPage((old) => old + 1));

  return (
    <div className="home gap-2 px-2 pt-4">
      <div className="d-flex w-100 gap-1">
        <input
          className="form-control"
          type="text"
          placeholder="Pesquisar"
          name="query"
          onChange={pageFormik.handleChange}
          onKeyDown={(e) => {
            e.key === 'Enter' && handleLoadUsers();
          }}
        />
        <button onClick={() => handleLoadUsers()} className="btn btn-primary">
          Pesquisar
        </button>
      </div>

      <div
        id="lista"
        className="d-flex flex-column overflow-y-scroll min-h-100 align-items-center align-self-sm-start align-self-xs-center w-100 gap-2 min-vh-100"
      >
        {!isLoading && data?.users.map((user) => <UserItem user={user} onAction={handleLoadUsers} />)}
        {data?.isLast && data?.users.length > 0 && <i ref={loaderRef} className="bi-arrow-up fs-5 fw-bolder"></i>}
        {isLoading && (
          <div className="spinner-grow text-info" role="status">
            <span className="sr-only">.</span>
          </div>
        )}
        {data?.isLast && data?.users.length === 0 && (
          <span ref={loaderRef}>
            <i className="bi-x-lg" />
          </span>
        )}
        {!data?.isLast && !isLoading && (
          <span ref={loaderRef}>
            <i className="bi-arrow-down"></i>
          </span>
        )}
      </div>
    </div>
  );
}
