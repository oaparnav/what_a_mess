package algorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class WhichTest {
	@Test
	public void emptyList() {
		Which which = new Which(new ArrayList<>());
		F result = which.Find(FT.One);
		assertEquals(null, result);
	}
}
