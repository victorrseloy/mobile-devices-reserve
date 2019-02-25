import React from 'react';
import './style.scss';
import MovieBanner from 'components/Presentational/MovieBanner';

const MoviesBannerContainer = () => {
  return (
    <div className="movies-banner-container">
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
      <MovieBanner />
    </div>
  );
};

export default MoviesBannerContainer;
