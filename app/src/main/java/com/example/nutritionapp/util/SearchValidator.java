package com.example.nutritionapp.util;

public class SearchValidator {
    public static final int MAX_QUERY_LENGTH = 60;

    public ValidationResult validate(String query) {
        if (query == null || query.trim().isEmpty()) {
            return ValidationResult.invalid(ValidationError.EMPTY);
        }

        String normalizedQuery = query.trim();
        if (normalizedQuery.length() < 2) {
            return ValidationResult.invalid(ValidationError.TOO_SHORT);
        }

        if (normalizedQuery.length() > MAX_QUERY_LENGTH) {
            return ValidationResult.invalid(ValidationError.TOO_LONG);
        }

        return ValidationResult.valid(normalizedQuery);
    }

    public enum ValidationError {
        EMPTY,
        TOO_SHORT,
        TOO_LONG
    }

    public static class ValidationResult {
        private final boolean valid;
        private final String normalizedQuery;
        private final ValidationError error;

        private ValidationResult(boolean valid, String normalizedQuery, ValidationError error) {
            this.valid = valid;
            this.normalizedQuery = normalizedQuery;
            this.error = error;
        }

        public static ValidationResult valid(String normalizedQuery) {
            return new ValidationResult(true, normalizedQuery, null);
        }

        public static ValidationResult invalid(ValidationError error) {
            return new ValidationResult(false, null, error);
        }

        public boolean isValid() {
            return valid;
        }

        public String getNormalizedQuery() {
            return normalizedQuery;
        }

        public ValidationError getError() {
            return error;
        }
    }
}
