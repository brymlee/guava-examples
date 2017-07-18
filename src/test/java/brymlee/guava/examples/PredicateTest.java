package brymlee.guava.examples;

import org.junit.*;
import com.google.common.base.*;
import com.google.common.collect.*;

import static org.junit.Assert.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.base.Predicates.*;
import static java.util.Arrays.*;

public class PredicateTest{

	@Test
	public void filterOutOnesJava8Test(){
		assertEquals(Integer.valueOf(1), asList(1, 2, 3) 
			.stream()
			.filter(integer -> Integer.valueOf(1).equals(integer))
			.findAny()
			.get()); 
	} 

	@Test
	public void filterOutOnesJava7Test(){
		assertEquals(Integer.valueOf(1), from(ImmutableList.<Integer>of(1, 2, 3))
			.filter(equalTo(1))
			.last()
			.get()); 
	}

	@Test
	public void filterOutNotPresentsJava8Test(){
		assertEquals(Integer.valueOf(2), asList(Optional.<Integer>absent(),
						        Optional.fromNullable(2),
						        Optional.<Integer>absent())
			.stream()
			.filter(optionalInteger -> optionalInteger.isPresent())
			.findAny()
			.get()
			.get());
	} 

	@Test
	public void filterOutNotPresentsJava7Test(){
		assertEquals(Integer.valueOf(2), from(ImmutableList.<Optional<Integer>>of(Optional.<Integer>absent(),
											  Optional.fromNullable(2),
											  Optional.<Integer>absent()))
			.filter(new Predicate<Optional<Integer>>(){
				@Override
				public boolean apply(final Optional<Integer> integer){
					return integer.isPresent();
				}
			}).last()
			.get()
			.get());
	}

}
