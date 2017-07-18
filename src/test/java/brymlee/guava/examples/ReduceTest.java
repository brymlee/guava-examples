package brymlee.guava.examples;

import org.junit.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import java.util.List;
import java.util.Iterator;

import static org.junit.Assert.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.base.Predicates.*;
import static java.util.Arrays.*;

public class ReduceTest{

	@Test
	public void sumAllReductionJava8Test(){
		final List<Integer> sumAllTheseIntegers = asList(1, 2, 3, 4, 5);
		final java.util.Optional<Integer> sumOfAllTheseIntegers = sumAllTheseIntegers
			.stream()
			.reduce((i, j) -> i + j);
		assertTrue(sumOfAllTheseIntegers.isPresent());
		assertEquals(Integer.valueOf(15), sumOfAllTheseIntegers.get());
	}

	@Test
	public void sumAllReductionJava7Test(){
		final List<Integer> sumAllTheseIntegers = ImmutableList.<Integer>of(1, 2, 3, 4, 5);
		final Optional<Integer> sumOfAllTheseIntegers = reduceSum(from(sumAllTheseIntegers)); 
		assertTrue(sumOfAllTheseIntegers.isPresent());
		assertEquals(Integer.valueOf(15), sumOfAllTheseIntegers.get());
	}

	private static Optional<Integer> reduceSum(final FluentIterable<Integer> integers){
		final Iterator<Integer> iterator = integers
			.toList()
			.iterator();
		return reduceSum(iterator, 0);
	}

	private static Optional<Integer> reduceSum(final Iterator<Integer> iterator
						  ,final Integer runningTotal){
		Preconditions.checkArgument(Predicates.notNull().apply(runningTotal));
		if(!iterator.hasNext()){
			return Optional.fromNullable(runningTotal);
		}
		final Optional<Integer> nextInteger = Optional.fromNullable(iterator.next());
		if(!nextInteger.isPresent()){
			return reduceSum(iterator, runningTotal);
		}
		return reduceSum(iterator, nextInteger.get() + runningTotal);
	}

	private static final ImmutableMap<String, String> EXAMPLE_MAP = ImmutableMap.<String, String>of("greeting", "hello");
}
