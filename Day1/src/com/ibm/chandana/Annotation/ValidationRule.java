package com.ibm.chandana.Annotation;

interface ValidationRule<T> {
	boolean test(T value);

	default ValidationRule<T> and(ValidationRule<T> other) {
		return new ValidationRule<T>() {
			@Override
			public boolean test(T value) {
				return this.test(value) && other.test(value);
			}
		};
	}

	static ValidationRule<String> notBlank() {
		return new ValidationRule<String>() {

			@Override
			public boolean test(String value) {
				return value.isEmpty();
			}
		};

	}

	static ValidationRule<String> minLength(int n) {
		return new ValidationRule<String>() {

			@Override
			public boolean test(String value) {
				return value.length() > 2;
			}

		};

	}

	static ValidationRule<String> matches(String regex) {
		return new ValidationRule<String>() {

			@Override
			public boolean test(String value) {

				return value.contains(regex);
			}

		};

	}

}
