package day8.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringfinal {
	public int longestSubStringLength(String s) {
		Set<Character> set = new HashSet<>();
		int left = 0;
		int maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			while (set.contains(s.charAt(i))) {
				set.remove(s.charAt(left));
				left++;
			}
			set.add(s.charAt(i));

			maxLength = Math.max(maxLength, i - left + 1);
		}
		return maxLength;
	}

	public static void main(String args[]) {
		LongestSubStringfinal l = new LongestSubStringfinal();
		System.out.println("abcabcbb → " + l.longestSubStringLength("abcabcbb") + " (expected 3)");
		System.out.println("bbbbb    → " + l.longestSubStringLength("bbbbb") + " (expected 1)");
		System.out.println("pwwkew   → " + l.longestSubStringLength("pwwkew") + " (expected 3)");
		System.out.println("' '      → " + l.longestSubStringLength(" ") + " (expected 1)");
	}

}
