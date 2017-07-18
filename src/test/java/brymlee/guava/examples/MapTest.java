package brymlee.guava.examples;

import org.junit.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import java.util.stream.*;
import java.util.List;

import static org.junit.Assert.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.base.Predicates.*;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

public class MapTest{

	@Test
	public void mapStringToIntegerJava8Test(){
		final List<Integer> integers = asList("1", "2", "3")
			.stream()
			.map(string -> Integer.parseInt(string))
			.collect(toList());
		assertEquals(Integer.valueOf(1), integers.get(0));
		assertEquals(Integer.valueOf(2), integers.get(1));
		assertEquals(Integer.valueOf(3), integers.get(2));
	}

	@Test
	public void mapStringToIntegerJava7Test(){
		final List<Integer> integers = from(ImmutableList.<String>of("1", "2", "3"))
			.transform(new Function<String, Integer>(){
				@Override
				public Integer apply(final String string){
					return Integer.parseInt(string);
				}
			}).toList();
		assertEquals(Integer.valueOf(1), integers.get(0));
		assertEquals(Integer.valueOf(2), integers.get(1));
		assertEquals(Integer.valueOf(3), integers.get(2));
	}
}
