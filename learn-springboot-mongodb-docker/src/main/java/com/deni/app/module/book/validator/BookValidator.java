package com.deni.app.module.book.validator;

import com.deni.app.common.constants.Messages;
import com.deni.app.common.validator.Validator;
import com.deni.app.module.book.collection.Book;
import com.deni.app.module.book.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookValidator {

    @Autowired
    BookRepo repo;

    public Validator duplicateValidator(String uniqueKey1, String uniqueKey2) {
        Book entity = repo.getBooksByNameAndAuthor(uniqueKey1,uniqueKey2);
        if (entity != null) {
            return new Validator().no(String.format(Messages.MSG_DATA_ALREADY_EXIST, entity.getName()));
        } else {
            return new Validator().yes(Messages.MSG_DATA_IS_AVAILABLE);
        }
    }

    public Validator requestValidator(Book dto) {
        List<String> message = new ArrayList<>();

        if (dto.getName() == null) {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "name"));
        } else {
            if (dto.getName().isEmpty()) {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "name"));
            }
        }


        if (dto.getAuthor() == null) {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "author"));
        } else {
            if (dto.getAuthor().isEmpty()) {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "author"));
            }
        }


        if (message.isEmpty()) {
            return new Validator().yes();
        } else {
            String result = "";
            for (String str : message) {
                if (result.isEmpty()) {
                    result = str;
                } else {
                    result += ", " + str;
                }
            }
            return new Validator().no(result);
        }
    }


    public Validator requestValidatorForUpdate(Book dto) {
        List<String> message = new ArrayList<>();

        if (dto.getName() == null) {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "name"));
        } else {
            if (dto.getName().isEmpty()) {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "name"));
            }
        }

        if (dto.getAuthor() == null) {
            message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_NULL, "author"));
        } else {
            if (dto.getAuthor().isEmpty()) {
                message.add(String.format(Messages.MSG_FIELD_CANNOT_BE_EMPTY, "author"));
            }
        }





        if (message.isEmpty()) {
            return new Validator().yes();
        } else {
            String result = "";
            for (String str : message) {
                if (result.isEmpty()) {
                    result = str;
                } else {
                    result += ", " + str;
                }
            }
            return new Validator().no(result);
        }
    }

}
