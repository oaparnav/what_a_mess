package algorithm;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class WhichTest {
	List<Thing> inputThings = new ArrayList<Thing>();
	Thing firstThing, secondThing;
	
	@Test
	public void shouldReturnAnEmptyAnswer() {
		Which which = new Which(new ArrayList<>());
		Answer result = which.Find(FT.One);
		assertEquals(new Answer(), result);
	}

	@Test
	public void shouldReturnAnEmptyAnswer_ForOneThing() {
		List<Thing> inputThings= getInputThings(1);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.One);
		
		assertEquals(new Answer(), result);
	}

	@Test
	public void shouldReturnAnEmptyAnswer_WhenFindTwo_ForOneThing() {
		List<Thing> inputThings = getInputThings(1);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		
		assertEquals(new Answer(), result);
	}
	
	@Test
	public void shouldReturnDescendingOrderWhenFindWithTwoThings() {
		
		List<Thing> inputThings = getInputThings(1);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(secondThing, firstThing, 0), result);
	}

	@Test
	public void shouldReturnAnswerWithDifferentDateWhenFindWithTwoThings() {
		
		inputThings = getInputThings(2);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(new Thing("name0", createDate(0)), new Thing("name1", createDate(1)), 1000), result);
	}
	
	private List<Thing> getInputThings(int numberOfThings) {
		
		return IntStream.range(0, numberOfThings)
				.mapToObj(num -> new Thing("name"+num, createDate(num)))
				.collect(Collectors.toList());
	}

	private Date createDate(int num) {
		return Date.from(LocalDateTime.of(2020, 9, 04, 12, 00, 00).plusSeconds(num).atZone(ZoneId.systemDefault()).toInstant());
	}
	
}
