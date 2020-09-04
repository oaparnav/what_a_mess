package algorithm;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		Thing thing = new Thing("input",new Date());
		inputThings.add(thing);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.One);
		assertEquals(new Answer(), result);
	}
	
	@Test
	public void shouldReturnAnEmptyAnswer_WhenFindTwo_ForOneThing() {
		Thing thing = new Thing("input",new Date());
		inputThings.add(thing);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(), result);
	}
	
	@Test
	public void shouldReturnDescendingOrderWhenFindWithTwoThings() {
		
		input();
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(secondThing, firstThing, 0), result);
	}

	private void input() {
		firstThing = new Thing("first input", new Date());
		secondThing = new Thing("second input", new Date());

		inputThings.add(firstThing);
		inputThings.add(secondThing);
	}
	
	@Test
	public void shouldReturnAnswerWithDifferentDateWhenFindWithTwoThings() {
		Thing firstThing = new Thing("first input", new Date());
		Thing secondThing = new Thing("second input", Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant()));

		inputThings.add(firstThing);
		inputThings.add(secondThing);
		
		long differences = secondThing.date.getTime() - firstThing.date.getTime();
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(firstThing, secondThing, differences), result);
	}
}
