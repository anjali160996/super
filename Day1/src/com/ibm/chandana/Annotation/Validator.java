package com.ibm.chandana.Annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Validator {
	private final Map<Class<?>, List<Field>> fieldCache = new ConcurrentHashMap<>();

	public List<String> validate(Object obj) {
		if (obj == null) {
			List<String> list = List.of("Object is null");
			return list;
		}
		List<Field> fs = fieldCache.computeIfAbsent(obj.getClass(), Validator::getDeclaredFieldsAnnotation);
		List<String> validationErrors = new ArrayList<>(fs.size());
		for (Field f : fs) {
			Object obj1;
			try {
				obj1 = f.get(obj);
				if (obj1 == null) {
					validationErrors.add(f.getName() + " should not be null");
				}
			} catch (IllegalAccessException e) {
				throw new IllegalStateException("Cannot access fields" + f.getName(), e);
			}
		}
		return validationErrors;
	}

	private static List<Field> getDeclaredFieldsAnnotation(Class<?> class1) {
		Field[] f = class1.getDeclaredFields();
		List<Field> fields = new ArrayList<>();
		for (Field f1 : f) {
			if (f1.getAnnotation(NotNull.class) != null) {
				f1.setAccessible(true);
				fields.add(f1);
			}
		}
		return fields;
	}
}
