package com.card.cardapi.utils;

import com.card.cardapi.entities.User;
import com.card.cardapi.exceptions.CustomExceptionNotFound;
import com.card.cardapi.security.SecurityUtils;
import com.card.cardapi.services.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AppFunctions {


    private final UserService userService;

    private static final String COLOR_REGEX = "^#([A-Fa-f0-9]{6})$";
    private static final Pattern COLOR_PATTERN = Pattern.compile(COLOR_REGEX);

    public AppFunctions( UserService userService) {
        this.userService = userService;
    }




    //method to validate email
    public Boolean validateEmail(String email){
        try {
            Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.find();
        } catch (Exception ex) {
            return false;
        }
    }


//method to validate if color is validate
    public  boolean isValidColor(String color) {
        if (color == null || color.isEmpty()) {
            return true; //
        }

        return COLOR_PATTERN.matcher(color).matches();
    }

    //check if sort field and sort order is valid
    public  boolean isSortValid(String sortField, String sortOrder) {

        if ((sortField == null || sortField.isEmpty()) && (sortOrder == null || sortOrder.isEmpty())) {
            return false;
        }
        if(!isValidSortField(sortField)){
            throw new CustomExceptionNotFound("Invalid sortField passed");
        };

        return true;

    }


    public Boolean shouldSearch(String searchKey){
        if (null==searchKey || searchKey.isEmpty())
            return false;
        else
            return true;
    }

    public  boolean isValidSortField(String value) {
        try {
            SortField sortField = SortField.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }


}
