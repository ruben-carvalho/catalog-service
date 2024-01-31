package com.ruben.catalogservice.Service.Validators;


import br.com.fluentvalidator.AbstractValidator;
import com.ruben.catalogservice.CrossCutting.Requests.CreateBookRequest;
import com.ruben.catalogservice.Repository.*;
import com.ruben.catalogservice.Service.Mappers.IBookMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

import static br.com.fluentvalidator.predicate.CollectionPredicate.empty;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class CreateBookValidator extends AbstractValidator<CreateBookRequest> {

    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final IAvailabilityRepository availabilityRepository;
    private final ILanguageRepository languageRepository;
    private final IFormatRepository formatRepository;
    private final IGenreRepository genreRepository;
    private final IPublisherRepository publisherRepository;
    private final ITagRepository tagRepository;

    public CreateBookValidator(
            IBookRepository bookRepository,
            IAuthorRepository authorRepository,
            IAvailabilityRepository availabilityRepository,
            ILanguageRepository languageRepository,
            IFormatRepository formatRepository,
            IGenreRepository genreRepository,
            IPublisherRepository publisherRepository,
            ITagRepository tagRepository,
            IBookMapper bookMapper) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.availabilityRepository = availabilityRepository;
        this.languageRepository = languageRepository;
        this.formatRepository = formatRepository;
        this.publisherRepository = publisherRepository;
        this.tagRepository = tagRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void rules() {
        setPropertyOnContext("book");

        ruleFor(CreateBookRequest::getTitle)
                .must(not(nullValue()))
                    .withMessage("Book must have a title")
                    .withFieldName("title")
                .must(not(this::bookExistsByTitle))
                    .withMessage("A book with this title already exists")
                    .withFieldName("title")
                .critical();

        ruleFor(CreateBookRequest::getOriginalTitle)
                .must(not(nullValue()))
                    .withMessage("Book must have an original title")
                    .withFieldName("originalTitle")
                .must(not(this::bookExistsByOriginalTitle))
                    .withMessage("A book with this original title already exists")
                    .withFieldName("originalTitle")
                .critical();

        ruleFor(CreateBookRequest::getIsbn)
                .must(not(nullValue()))
                    .withMessage("Book must have an isbn")
                    .withFieldName("isbn")
                .must(not(this::bookExistsByIsbn))
                    .withMessage("A book with this isbn already exists")
                    .withFieldName("isbn")
                .critical();

        ruleFor(CreateBookRequest::getAuthors)
                .must(not(nullValue()))
                    .withMessage("Book's author cannot be null")
                    .withFieldName("authors")
                .must(not(empty()))
                    .when(not(nullValue()))
                    .withMessage("Book must have at least one author")
                    .withFieldName("authors")
                .must(this::authorsExist)
                    .withMessage("One of the author's does not exist, please insert valid authors")
                    .withFieldName("authors")
                .critical();

        ruleFor(CreateBookRequest::getAvailability)
                .must(not(nullValue()))
                    .withMessage("Book must have an availability status")
                    .withFieldName("availability")
                .must(this::availabilityExists)
                    .withMessage("This book availability status is invalid, please insert a valid one")
                    .withFieldName("availability")
                .critical();

        ruleFor(CreateBookRequest::getFormat)
                .must(not(nullValue()))
                    .withMessage("Book must have a format type")
                    .withFieldName("Format")
                .must(this::formatExists)
                    .withMessage("This book format is invalid, please insert a valid one")
                    .withFieldName("format")
                .critical();

        ruleFor(CreateBookRequest::getGenre)
                .must(not(nullValue()))
                    .withMessage("Book must have a genre")
                    .withFieldName("genre")
                .must(this::genreExists)
                    .withMessage("This book genre is invalid, please insert a valid one")
                    .withFieldName("genre")
                .critical();

        ruleFor(CreateBookRequest::getLanguage)
                .must(not(nullValue()))
                    .withMessage("Book must have a language")
                    .withFieldName("language")
                .must(this::languageExists)
                    .withMessage("This book language is invalid, please insert a valid one")
                    .withFieldName("language")
                .critical();

        ruleFor(CreateBookRequest::getPublisher)
                .must(not(nullValue()))
                    .withMessage("Book must have a publisher")
                    .withFieldName("publisher")
                .must(this::publisherExists)
                    .withMessage("There is an invalid author, please insert only valid ones")
                    .withFieldName("publisher")
                .critical();

        ruleFor(CreateBookRequest::getTags)
                .must(not(nullValue()))
                    .withMessage("Book's tags cannot be null")
                    .withFieldName("tags")
                .must(not(empty()))
                    .when(not(nullValue()))
                    .withMessage("Book must have at least one tag")
                .withFieldName("tags")
                    .must(this::tagsExist)
                    .withMessage("There is an invalid tag, please insert only valid ones")
                   .withFieldName("tags")
                .critical();

    }

    private boolean bookExistsByTitle(final String title) {
        return this.bookRepository.findByTitle(title) != null;
    }

    private boolean bookExistsByOriginalTitle(final String originalTitle) {
        return this.bookRepository.findByOriginalTitle(originalTitle) != null;
    }

    private boolean bookExistsByIsbn(final String isbn) {
        return this.bookRepository.findByIsbn(isbn) != null;
    }

    private boolean authorsExist(final Set<String> authors) {
        return authors
                .stream()
                .allMatch(x->
                        this.authorRepository.findByName(x) != null);
    }

    private boolean availabilityExists(final String availability) {
        return this.availabilityRepository.findByStatus(availability) != null;
    }

    private boolean formatExists(final String formatType) {
        return this.formatRepository.findByType(formatType) != null;
    }

    private boolean genreExists(final String genre) {
        return this.genreRepository.findByName(genre) != null;
    }

    private boolean languageExists(final String language) {
        return this.languageRepository.findByName(language) != null;
    }

    private boolean publisherExists(final String publisher) {
        return this.publisherRepository.findByName(publisher) != null;
    }

    private boolean tagsExist(final Set<String> tags) {
        return tags
                .stream()
                .allMatch(x->
                        this.tagRepository.findByName(x) != null);
    }
}