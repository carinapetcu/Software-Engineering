package iss.core.domain.validation;

import iss.core.domain.BaseEntity;
import iss.core.exceptions.ValidationException;

public interface Validator <T extends BaseEntity<?>> {
    void validate(T entity) throws ValidationException;
}
