package algorithm;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class WhichTest {
	private static final String NAME = "name";
	List<Thing> inputThings = new ArrayList<Thing>();
	Thing firstThing, secondThing;
	
	@Test
	public void shouldReturnAnEmptyAnswer() {
		Which which = new Which(new ArrayList<>());
		Answer result = which.Find(FT.One);
		assertThat(result).isEqualTo(new Answer());
	}

	@Test
	public void shouldReturnAnEmptyAnswer_ForOneThing() {
		List<Thing> inputThings= getInputThings(1);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.One);
		
		assertThat(result).isEqualTo(new Answer());
	}

	@Test
	public void shouldReturnAnEmptyAnswer_WhenFindTwo_ForOneThing() {
		List<Thing> inputThings = getInputThings(1);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		
		assertThat(result).isEqualTo(new Answer());
	}
	
	@Test
	public void shouldReturnDescendingOrderWhenFindWithTwoThings() {
		List<Thing> inputThings = getInputThings(1);
		
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertThat(result).isEqualTo(new Answer(secondThing, firstThing, 0));
	}

	@Test
	public void shouldReturnAnswerWithDifferentDateWhenFindWithTwoThings() {
		
		inputThings = getInputThings(2);
		Which which = new Which(inputThings);
		Answer result = which.Find(FT.Two);
		assertThat(result).isEqualTo(new Answer(new Thing(NAME, createDate(0)), new Thing(NAME, createDate(1)), 1000));
	}
	
	@Test
	public void returnEmptyAnswersForEmptyInputThings() {
		inputThings =getInputThings(0);
		Which which = new Which(inputThings);
		assertThat(which.prepareAnswers()).isEqualTo(new ArrayList<>());
	}
	
	@Test
	public void returnEmptyAnswersForOneInputThing() {
		inputThings =getInputThings(1);
		Which which = new Which(inputThings);
		assertThat(which.prepareAnswers()).isEqualTo(new ArrayList<>());
	}
	
	@Test
	public void returnEmptyAnswersForTwoInputThings() {
		inputThings =getInputThings(2);
		Which which = new Which(inputThings);
		assertThat(which.prepareAnswers()).isEqualTo(prepareExpectedAnswers(1));
	}
	
	@Test
	public void returnEmptyAnswersForThreeInputThings() {
		inputThings =getInputThings(3);
		Which which = new Which(inputThings);
		assertThat(which.prepareAnswers()).isEqualTo(prepareExpectedAnswers(3));
	}
	
	@Test
	public void returnAnswerWithSortedThings() {
		Thing firstThing = new Thing("firstThing", createDate(0));
		Thing secondThing = new Thing("secondThing", createDate(5));
		
		Which which = new Which(null);
		
		Answer answer = which.createAnswerWithSortedThings(firstThing, secondThing);
		assertThat(answer).isEqualTo(new Answer(firstThing, secondThing, 5000));
	}
	
	private List<Thing> getInputThings(int numberOfThings) {
		
		return IntStream.range(0, numberOfThings)
				.mapToObj(num -> new Thing(NAME, createDate(num)))
				.collect(Collectors.toList());
	}

	private Date createDate(int num) {
		return Date.from(LocalDateTime.of(2020, 9, 04, 12, 00, 00).plusSeconds(num).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	private List<Answer> prepareExpectedAnswers(int numberOfThings) {
		return IntStream.range(0, numberOfThings)
				.mapToObj(num-> new Answer(new Thing(NAME, createDate(num)), new Thing(NAME, createDate(num+1)), 1000))
				.collect(Collectors.toList());
	}
}
