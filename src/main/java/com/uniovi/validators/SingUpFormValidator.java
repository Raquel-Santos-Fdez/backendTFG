package com.uniovi.validators;

import com.uniovi.entities.Employee;
import com.uniovi.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SingUpFormValidator implements Validator {

    @Autowired
    private EmployeesService employeesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee=(Employee) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if (employee.getUsername().length() < 5 || employee.getUsername().length() > 24) {
            errors.rejectValue("username", "Error.signup.dni.length");
        }
        if (employeesService.getEmployeeByUsername(employee.getUsername()) != null) {
            errors.rejectValue("username", "Error.signup.dni.duplicate");
        }
        if (employee.getName().length() < 5 || employee.getName().length() > 24) {
            errors.rejectValue("name", "Error.signup.name.length");
        }
        if (employee.getSurname().length() < 5 || employee.getSurname().length() > 24) {
            errors.rejectValue("surname", "Error.signup.lastName.length");
        }
        if (employee.getPassword().length() < 5 || employee.getPassword().length() > 24) {
            errors.rejectValue("password", "Error.signup.password.length");
        }
        if (!employee.getPasswordConfirm().equals(employee.getPassword())) {
            errors.rejectValue("passwordConfirm",
                    "Error.signup.passwordConfirm.coincidence");
        }


    }
}
