package day8.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ValidParentheses {

	private final Map<Character, Character> standard;

	public ValidParentheses() {
		standard = new HashMap<>();
		standard.put('(', ')');
		standard.put('{', '}');
		standard.put('[', ']');
	}

	public boolean isValid(String s) {
		Deque<Character> stack = new ArrayDeque<>();

		for (char c : s.toCharArray()) {
			if (standard.containsKey(c)) {
				stack.push(standard.get(c));
			} else {
				if (stack.isEmpty() || stack.pop() != c)
					return false;
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {
		ValidParentheses v = new ValidParentheses();
		System.out.println(v.isValid("()"));
		System.out.println(v.isValid("()[]{}"));
		System.out.println(v.isValid("(]"));
		System.out.println(v.isValid("([])"));
		System.out.println(v.isValid("([)]"));
	}

}
