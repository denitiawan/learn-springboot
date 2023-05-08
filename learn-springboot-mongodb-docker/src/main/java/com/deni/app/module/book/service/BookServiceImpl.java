package com.deni.app.module.book.service;

import com.deni.app.common.constants.Messages;
import com.deni.app.common.controller.ResponseHandler;
import com.deni.app.common.validator.Validator;
import com.deni.app.module.book.collection.Book;
import com.deni.app.module.book.repo.BookRepo;
import com.deni.app.module.book.validator.BookValidator;
import com.deni.app.module.user.collection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookValidator bookValidator;


    public BookServiceImpl(BookValidator bookValidator, BookRepo bookRepo) {
        this.bookValidator = bookValidator;
        this.bookRepo = bookRepo;
    }

    @Override
    public ResponseEntity add(Book request) {
        Validator validator = bookValidator.requestValidator(request);
        if (validator.isSuccess()) {
            validator = bookValidator.duplicateValidator(request.getName(),request.getAuthor());
            if (validator.isSuccess()) {

                Book save = bookRepo.save(request);

                return ResponseHandler.createHttpResponse(
                        Messages.MSG_SAVE_SUCCESS,
                        request,
                        HttpStatus.OK);

            } else {
                return ResponseHandler.createHttpResponse(
                        validator.getMessage(),
                        request,
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return ResponseHandler.createHttpResponse(
                    validator.getMessage(),
                    request,
                    HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity update(Integer id, Book request) {
        Validator validator = bookValidator.requestValidatorForUpdate(request);
        if (validator.isSuccess()) {
            Optional<Book> optional = bookRepo.findById(id);
            if (optional.isPresent()) {

                Book update = (Book) optional.get();
                update.setAuthor(request.getAuthor());
                update.setName(request.getName());
                update.setCost(request.getCost());
                update.setUpdateDate(request.getUpdateDate());
                update.setUpdateBy(request.getUpdateBy());
                update = bookRepo.save(update);


                return ResponseHandler.createHttpResponse(
                        Messages.MSG_UPDATE_SUCCESS,
                        update,
                        HttpStatus.OK);

            } else {
                return ResponseHandler.createHttpResponse(
                        Messages.MSG_DATA_NOT_FOUND,
                        request,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseHandler.createHttpResponse(
                    validator.getMessage(),
                    request,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getAll() {
        List<Book> list = bookRepo.findAll();
        if (!list.isEmpty()) {


            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    list,
                    HttpStatus.OK);
        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    "",
                    HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity findById(Integer id) {
        Optional<Book> optional = bookRepo.findById(id);
        if (optional.isPresent()) {


            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    optional.get(),
                    HttpStatus.OK);

        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    "",
                    HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity delete(Integer id) {
        Optional<Book> optional = bookRepo.findById(id);
        if (optional.isPresent()) {
            bookRepo.delete(optional.get());


            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DELETE_SUCCESS,
                    optional.get(),
                    HttpStatus.OK);

        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    "",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteAll() {
        bookRepo.deleteAll();

        return ResponseHandler.createHttpResponse(
                Messages.MSG_DELETE_SUCCESS,
                "",
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity findByRegexAuthor(String author) {
        List<Book> list = bookRepo.getBooksByAuthorRegEx(author);
        if (!list.isEmpty()) {


            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    list,
                    HttpStatus.OK);
        } else {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_NOT_FOUND,
                    "",
                    HttpStatus.NOT_FOUND);
        }
    }
}
