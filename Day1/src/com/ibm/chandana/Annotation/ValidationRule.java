package com.ibm.chandana.Annotation;

import java.util.ArrayList;
import java.util.List;

interface ValidationRule<T> {

	boolean test(T value);
	default List<String> messages(T value) {
		return List.of();
	}

	default ValidationRule<T> and(ValidationRule<T> other) {
		return new ValidationRule<T>() {
			public boolean test(T value) {
				return ValidationRule.this.test(value) & other.test(value);
			}
			public List<String> messages(T value) {
				List<String> combined = new ArrayList<>();
				if (!ValidationRule.this.test(value))
					combined.addAll(ValidationRule.this.messages(value));
				if (!other.test(value))
					combined.addAll(other.messages(value));
				return combined;
			}
		};
	}

	static ValidationRule<String> notBlank() {
		return new ValidationRule<String>() {
			public boolean test(String value) {
				return value != null && !value.trim().isEmpty();
			}
			public List<String> messages(String value) {
				return List.of("must not be blank");
			}
		};
	}

	static ValidationRule<String> minLength(int n) {
		return new ValidationRule<String>() {
			public boolean test(String value) {
				return value != null && value.length() >= n;
			}
			public List<String> messages(String value) {
				return List.of("length must be at least " + n);
			}
		};
	}

	static ValidationRule<String> matches(String regex) {
		return new ValidationRule<String>() {
			public boolean test(String value) {
				return value != null && value.matches(regex);
			}
			public List<String> messages(String value) {
				return List.of("must match pattern: " + regex);
			}
		};
	}

}
