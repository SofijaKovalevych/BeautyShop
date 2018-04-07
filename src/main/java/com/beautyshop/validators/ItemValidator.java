package com.beautyshop.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.beautyshop.entity.Item;
import com.beautyshop.form.ItemForm;
import com.beautyshop.service.ItemService;

import static com.beautyshop.constants.ValidationConstants.*;

public class ItemValidator implements Validator{

    private final ItemService itemService;

    public ItemValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    	ItemForm itemForm = (ItemForm) target;
    	Item itemnFromDbByName = itemService.findByName(itemForm.getName());

        if (errors.getFieldError("name") == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", CANT_BE_EMPTY);
        }

        if (null != itemnFromDbByName) {
            if (itemForm.getId() != null) {
                if (!itemForm.getId().equals(itemnFromDbByName.getId())) {
                    errors.rejectValue("name", "", NAME_ALREADY_EXISTS);
                }
            } else {
                errors.rejectValue("name", "", NAME_ALREADY_EXISTS);
            }
        }

    }
	
}
