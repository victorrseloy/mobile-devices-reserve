import React from 'react';
import MoviesBannerContainer from 'components/Presentational/MoviesBannerContainer';
import './style.scss';

const MovieSearchView = () => {
  return (
    <div className="container">
      <h1> Movies search </h1>
      <input type="text" placeholder="Search..." />
      <MoviesBannerContainer />
    </div>
  );
};

export default MovieSearchView;
