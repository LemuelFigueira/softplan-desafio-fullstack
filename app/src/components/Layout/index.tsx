import { useAuth } from 'contexts/Auth';
import { useTheme } from 'contexts/Theme';

export default function Layout({ children }: any) {
  const { user, isAdmin, isSorter, isFinisher, logOut } = useAuth();
  const { theme } = useTheme();
  return (
    <div className="" style={{ color: theme }}>
      {user && (
        <div
          className={`d-flex justify-content-end align-items-center w-100 bg 
                        ${isAdmin && 'bg-danger'} ${isSorter && 'bg-info'} ${isFinisher && 'bg-secondary'}
                    gap-4 p-2 mb-2 shadow`}
        >
          <span className="fs-4 fw-bolder text-white">{user.name}</span>
          <button type="button" className="btn btn-sm p-1" onClick={logOut}>
            <i className="bi-x-circle-fill fs-4" />
          </button>
        </div>
      )}
      {children}
    </div>
  );
}
