package algorithm;

import static org.junit.Assert.assertEquals;

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
	public void shouldReturnAnEmptyAnswer_WhenFindOne_ForOneThing() {
		List<Thing> inputThings = new ArrayList<Thing>();
		Thing thing = new Thing("input",new Date());
		inputThings.add(thing);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertEquals(new Answer(), result);
	}
}
