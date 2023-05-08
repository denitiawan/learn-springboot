package com.deni.app.module.user.service;

import com.deni.app.common.constants.Messages;
import com.deni.app.common.controller.ResponseHandler;
import com.deni.app.common.validator.Validator;
import com.deni.app.module.user.collection.User;
import com.deni.app.module.user.repo.UserRepo;
import com.deni.app.module.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserValidator userValidator;


    public UserServiceImpl(UserValidator userValidator, UserRepo userRepo) {
        this.userValidator = userValidator;
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity save(User request) {
        Validator validator = userValidator.requestValidator(request);
        if (validator.isSuccess()) {
            validator = userValidator.duplicateValidator(request.getUsername());
            if (validator.isSuccess()) {

                User save = userRepo.save(request);

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
    public ResponseEntity update(Integer id, User request) {
        Validator validator = userValidator.requestValidatorForUpdate(request);
        if (validator.isSuccess()) {
            Optional<User> optional = userRepo.findById(id);
            if (optional.isPresent()) {

                User update = (User) optional.get();
                update.setUsername(request.getUsername());
                update.setRoles(request.getRoles());
                update.setBlocked(request.getBlocked());
                update.setActive(request.getActive());
                update = userRepo.save(update);


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
        List<User> list = userRepo.findAll();
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
        Optional<User> optional = userRepo.findById(id);
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
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            userRepo.delete(optional.get());


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
        userRepo.deleteAll();


        return ResponseHandler.createHttpResponse(
                Messages.MSG_DELETE_SUCCESS,
                "",
                HttpStatus.OK);
    }
}
