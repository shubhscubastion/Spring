package com.movieflix.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.entities.Movie;
import com.movieflix.exceptions.FileExistsException;
import com.movieflix.exceptions.MovieNotFoundException;
import com.movieflix.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {

        // Upload the file
        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))) {
            throw new FileExistsException("File already exists! Please enter another name!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);

        // Set the value of field 'poster' as filename
        movieDto.setPoster(uploadedFileName);

        // map dto to Movie Object

        Movie movie = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster());

        // save the movie object -> saved Movie Object

        Movie savedMovie = movieRepository.save(movie);

        // generate the posterURL

        String posterUrl = baseUrl + "/file/" + uploadedFileName;

        // map Movie object to DTO object and return it

        MovieDto response = new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl

        );

        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        // check the data in DB and if exists, fetch the data of given ID
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        // generate posterUrl

        String posterUrl = baseUrl + "/file/" + movie.getPoster();

        // map to MovieDto object and return it

        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl);

        return response;

    }

    @Override
    public List<MovieDto> getAllMovies() {
        // fetch all data from db

        List<Movie> movies = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();

        // iterate through the list, generate posterUrl for each movie obj,
        // and map to MovieDto obj

        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDto movieDto = new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl);

            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

    @Override
    public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException {

        // Check if the movie object exists with given movieId

        Movie mv = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie Not Found!"));

        // If file is null do nothing
        // if file is not null then delete existing file associated with the record, and
        // upload new file

        String fileName = mv.getPoster();
        if (file != null) {
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }

        // set movieDto's poster value, according to step2

        movieDto.setPoster(fileName);
        // map it to Movie object

        Movie movie = new Movie(
                mv.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster());

        // save the movie object -> return saved movie object
        Movie updatedMovie = movieRepository.save(movie);

        // generate posterUrl for it
        String posterUrl = baseUrl + "/file/" + fileName;

        // Map to MovieDto and return it

        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl);

        return response;
    }

    @Override
    public String deleteMovie(Integer movieId) throws IOException {

        // Check if movie object exists
        Movie mv = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        Integer id = mv.getMovieId();
        // delete the file associated with this object
        Files.deleteIfExists(Paths.get(path + File.separator + mv.getPoster()));

        // delete the movie object
        movieRepository.delete(mv);

        return "Movie deleted with id = " + id;
    }

    @Override
    public MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Movie> moviePages = movieRepository.findAll(pageable);
        List<Movie> movies = moviePages.getContent();

        List<MovieDto> movieDtos = new ArrayList<>();

        // iterate through the list, generate posterUrl for each movie obj,
        // and map to MovieDto obj

        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDto movieDto = new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl);

            movieDtos.add(movieDto);
        }
        return new MoviePageResponse(movieDtos, pageNumber, pageSize, moviePages.getTotalElements(),
                moviePages.getTotalPages(), moviePages.isLast());
    }

    @Override
    public MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy,
            String dir) {

        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Movie> moviePages = movieRepository.findAll(pageable);
        List<Movie> movies = moviePages.getContent();

        List<MovieDto> movieDtos = new ArrayList<>();

        // iterate through the list, generate posterUrl for each movie obj,
        // and map to MovieDto obj

        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDto movieDto = new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl);

            movieDtos.add(movieDto);
        }
        return new MoviePageResponse(movieDtos, pageNumber, pageSize, moviePages.getTotalElements(),
                moviePages.getTotalPages(), moviePages.isLast());
    }

}