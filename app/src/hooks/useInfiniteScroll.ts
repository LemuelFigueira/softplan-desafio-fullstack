import { MutableRefObject, useEffect } from 'react';

export default function useInfiniteScroll(loaderRef: MutableRefObject<any>, callback: () => void) {
  useEffect(() => {
    const options = {
      root: null,
      rootMargin: '20px',
      threshold: 1.0,
    };

    const observer = new IntersectionObserver((entities) => {
      const target = entities[0];

      if (target.isIntersecting) {
        callback();
      }
    }, options);

    if (loaderRef.current) {
      observer.observe(loaderRef.current);
    }
  }, []);
}
