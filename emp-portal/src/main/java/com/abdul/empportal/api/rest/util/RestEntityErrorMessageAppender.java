package com.abdul.empportal.api.rest.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.abdul.empportal.api.rest.domain.RestEntity;

@Component
public class RestEntityErrorMessageAppender {
	
	/**
	 * Appends error messages to the rest entity.
	 *
	 * @param entity
	 *            - Rest entity with binding errors
	 * @param binding
	 *            - Spring's binding object
	 * @param <T>
	 *            - Rest entity type
	 * @return ResponseEntity with passed entity with error messages appended and error code
	 */
	public <T extends RestEntity> ResponseEntity<T> append(T entity, BindingResult binding) {
		final String errorMessage = createErrorMessage(binding.getFieldErrors(), binding.getGlobalErrors());
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		entity.setErrorMessage(errorMessage);
		return new ResponseEntity<>(entity, status);
	}
	
	private String createErrorMessage(List<FieldError> fieldErrors, List<ObjectError> globalErrors) {
		String fieldErrorMessages = fieldErrors.stream()
				.map(this::createErrorMessageForField)
				.collect(Collectors.joining("; "));
		String globalErrorMessages = globalErrors.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("; "));
		return Stream.of(fieldErrorMessages, globalErrorMessages)
				.collect(Collectors.joining("; "));
	}

	private String createErrorMessageForField(FieldError error) {
		return String.format("%s: %s", error.getField(), error.getDefaultMessage());
	}

}
