package brymlee.guava.examples;

import org.junit.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import java.util.stream.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.base.Predicates.*;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class CompositeTest{

	@Test
	public void isPalindromesAllJava8Test(){
		final List<String> areThesePalindromes = asList("pop", "deed", "anna");
		final List<String> actualPalindromes = areThesePalindromes	
			.stream()
			.filter((WhereStringIsPalindrome) () -> areThesePalindromes)		
			.collect(toList());
		assertEquals("pop", actualPalindromes.get(0));
		assertEquals("deed", actualPalindromes.get(1));
		assertEquals("anna", actualPalindromes.get(2));
	}

	@Test
	public void isPalindromesAllJava7Test(){
		final List<String> areThesePalindromes = ImmutableList.<String>of("pop", "deed", "anna");
		final List<String> actualPalindromes = from(areThesePalindromes)
			.filter(whereStringIsPalindrome(areThesePalindromes))
			.toList();
		assertEquals("pop", actualPalindromes.get(0));
		assertEquals("deed", actualPalindromes.get(1));
		assertEquals("anna", actualPalindromes.get(2));
	}

	@Test 
	public void isPalindromeSomeJava8Test(){
		final List<String> areThesePalindromes = asList("pop", "game", "anna");
		final List<String> actualPalindromes = areThesePalindromes
			.stream()
			.filter((WhereStringIsPalindrome) () -> areThesePalindromes)
			.collect(toList());
		assertEquals("pop", actualPalindromes.get(0));
		assertEquals("anna", actualPalindromes.get(1));
	}

	@Test
	public void isPalindromeSomeJava7Test(){
		final List<String> areThesePalindromes = ImmutableList.<String>of("pop", "game", "anna");
		final List<String> actualPalindromes = from(areThesePalindromes)
			.filter(whereStringIsPalindrome(areThesePalindromes))
			.toList();
		assertEquals("pop", actualPalindromes.get(0));
		assertEquals("anna", actualPalindromes.get(1));
	}

	@FunctionalInterface
	private interface WhereStringIsPalindrome extends java.util.function.Predicate<String>{
		public List<String> areThesePalindromes();

		public default boolean test(final String stringA){
			return areThesePalindromes()
				.stream()
				.filter(stringB -> stringA.equals(stringB))
				.map(stringB -> {
					final String reversedStringB = new StringBuilder(stringB)
						.reverse()
						.toString();
					return stringA.equals(reversedStringB);
				}).reduce((i, j) -> i == null ? false : i)
				.get();
		}
	}

	private static Predicate<String> whereStringIsPalindrome(final String stringA){
		return new Predicate<String>(){
			@Override
			public boolean apply(final String stringB){
				final String reversedStringB = new StringBuilder(stringB)
					.reverse()
					.toString();
				return stringA.equals(reversedStringB);
			}
		};
	}

	private static Predicate<String> whereStringIsPalindrome(final List<String> stringBs){
		return new Predicate<String>(){
			@Override
			public boolean apply(final String stringA){
				return from(stringBs)
					.filter(equalTo(stringA))
					.anyMatch(whereStringIsPalindrome(stringA));
			}
		};
	}
}
