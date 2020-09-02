package algorithm;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class WhichTest {
	@Test
	public void shouldReturnAnEmptyAnswer() {
		Which which = new Which(new ArrayList<>());
		Answer result = which.Find(FT.One);
		assertEquals(new Answer(), result);
	}

	@Test
	public void shouldReturnAnEmptyAnswer_ForOneThing() {
		List<Thing> inputThings = new ArrayList<Thing>();
		Thing thing = new Thing("input",new Date());
		inputThings.add(thing);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.One);
		assertEquals(new Answer(), result);
	}
	
	@Test
	public void shouldReturnAnEmptyAnswer_WhenFindTwo_ForOneThing() {
		List<Thing> inputThings = new ArrayList<Thing>();
		Thing thing = new Thing("input",new Date());
		inputThings.add(thing);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(), result);
	}
	
	@Test
	public void shouldReturnDescendingOrderWhenFindWithTwoThings() {
		List<Thing> inputThings = new ArrayList<Thing>();
		Thing firstThing = new Thing("first input", new Date());
		Thing secondThing = new Thing("second input", new Date());

		inputThings.add(firstThing);
		inputThings.add(secondThing);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(secondThing, firstThing, 0), result);
	}
	
	@Test
	public void shouldReturnAnswerWithDifferentDateWhenFindWithTwoThings() {
		List<Thing> inputThings = new ArrayList<Thing>();
		Thing firstThing = new Thing("first input", new Date());
		Thing secondThing = new Thing("second input", Date.from(LocalDateTime.now().plusMinutes(30).toInstant(ZoneId.systemDefault())));

		inputThings.add(firstThing);
		inputThings.add(secondThing);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(secondThing, firstThing, 0), result);
	}
}
