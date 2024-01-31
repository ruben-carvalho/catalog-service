package com.ruben.catalogservice.Service.Mappers;

import com.ruben.catalogservice.CrossCutting.Requests.CreateBookRequest;
import com.ruben.catalogservice.Models.*;
import com.ruben.catalogservice.Repository.*;
import com.ruben.catalogservice.Service.DTO.BookDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class IBookMapper {


    public static final IBookMapper INSTANCE = Mappers.getMapper(IBookMapper.class);
    protected IBookRepository bookRepository;
    @Autowired
    protected void setBookRepository(IBookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    protected IAuthorRepository authorRepository;
    @Autowired
    protected void setAuthorRepository(IAuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    protected IAvailabilityRepository availabilityRepository;
    @Autowired
    protected void setAvailabilityRepository(IAvailabilityRepository availabilityRepository){
        this.availabilityRepository = availabilityRepository;
    }

    protected ILanguageRepository languageRepository;
    @Autowired
    protected void setLanguageRepository(ILanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    protected IFormatRepository formatRepository;
    @Autowired
    protected void setFormatRepository(IFormatRepository formatRepository){
        this.formatRepository = formatRepository;
    }

    protected IGenreRepository genreRepository;
    @Autowired
    protected void setGenreRepository(IGenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    protected IPublisherRepository publisherRepository;
    @Autowired
    protected void setPublisherRepository(IPublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    protected ITagRepository tagRepository;
    @Autowired
    protected void setTagRepository(ITagRepository tagRepository){
        this.tagRepository = tagRepository;
    }




//    Book toEntity(BookDTO bookDTO);


    @Mapping(target = "language", source = "language.name")
    @Mapping(target = "format", source = "format.type")
    @Mapping(target = "genre", source = "genre.name")
    @Mapping(target = "publisher", source = "publisher.name")
    @Mapping(target = "availability", source = "availability.status")
    @Mapping(target = "authors", source = "authors", qualifiedByName = "mapAuthorName")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagName")
    public abstract BookDTO toDto(Book book);

    public Set<BookDTO> toDto(Set<Book> books) {
        return books.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Named("mapAuthorName")
    protected String mapAuthorName(Author author) {
        return author != null ? author.getName() : null;
    }

    @Named("mapTagName")
    protected String mapTagName(Tag tag) {
        return tag != null ? tag.getName() : null;
    }

    @Mapping(target = "authors", source = "authors", qualifiedByName = "mapAuthorToEntity")
    @Mapping(target = "format", source = "format", qualifiedByName = "mapFormatToEntity")
    @Mapping(target = "language", source = "language", qualifiedByName = "mapLanguageToEntity")
    @Mapping(target = "availability", source = "availability", qualifiedByName = "mapAvailabilityToEntity")
    @Mapping(target = "genre", source = "genre", qualifiedByName = "mapGenreToEntity")
    @Mapping(target = "publisher", source = "publisher", qualifiedByName = "mapPublisherToEntity")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagsToEntity")
    public abstract Book toEntity(BookDTO dto);

    @Mapping(target = "authors", source = "authors", qualifiedByName = "mapAuthorToEntity")
    @Mapping(target = "format", source = "format", qualifiedByName = "mapFormatToEntity")
    @Mapping(target = "language", source = "language", qualifiedByName = "mapLanguageToEntity")
    @Mapping(target = "availability", source = "availability", qualifiedByName = "mapAvailabilityToEntity")
    @Mapping(target = "genre", source = "genre", qualifiedByName = "mapGenreToEntity")
    @Mapping(target = "publisher", source = "publisher", qualifiedByName = "mapPublisherToEntity")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagsToEntity")
    public abstract Book toEntity(CreateBookRequest dto);


    @Named("mapAuthorToEntity")
    protected Set<Author> mapAuthorToEntity(Set<String> names) {
        return names.stream()
                .map(this.authorRepository::findByName)
                .collect(Collectors.toSet());
    }

    @Named("mapLanguageToEntity")
    protected Language mapLanguageToEntity(String name) {
        return this.languageRepository.findByName(name);
    }

    @Named("mapFormatToEntity")
    protected Format mapFormatToEntity(String name) {
        return this.formatRepository.findByType(name);
    }

    @Named("mapAvailabilityToEntity")
    protected Availability mapAvailabilityToEntity(String status) {
        return this.availabilityRepository.findByStatus(status);
    }

    @Named("mapGenreToEntity")
    protected Genre mapGenreToEntity(String name) {
        return this.genreRepository.findByName(name);
    }

    @Named("mapPublisherToEntity")
    protected Publisher mapPublisherToEntity(String name) {
        return this.publisherRepository.findByName(name);
    }

    @Named("mapTagsToEntity")
    protected Set<Tag> mapTagsToEntity(Set<String> tagNames) {
        return tagNames.stream()
                .map(this.tagRepository::findByName)
                .collect(Collectors.toSet());
    }
}