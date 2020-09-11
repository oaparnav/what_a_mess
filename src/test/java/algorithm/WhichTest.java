package algorithm;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;

public class WhichTest {
	private static final String NAME = "Thing";
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
		assertThat(result).isEqualTo(new Answer(new Thing(NAME+0, createDate(0)), new Thing(NAME+1, createDate(1)), 1000));
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
		assertThat(which.prepareAnswers()).isEqualTo(prepareExpectedAnswers(1,1));
	}
	
	@Test
	public void returnEmptyAnswersForThreeInputThings() {
		inputThings =getInputThings(3);
		Which which = new Which(inputThings);
		List<Answer> expectedAnswers = prepareExpectedAnswers(3,1);
		expectedAnswers.forEach(ans-> System.out.println(ans.thing1 + " : " + ans.thing2 + " : " + ans.difference));
		assertThat(expectedAnswers.size()).isEqualTo(3);

	}
	
	@Test
	public void returnAnswerWithSortedThingsForOrderOne() {
		Thing firstThing = new Thing("firstThing", createDate(0));
		Thing secondThing = new Thing("secondThing", createDate(5));
		
		Which which = new Which(null);
		
		Answer answer = which.createAnswerWithSortedThings(firstThing, secondThing);
		assertThat(answer).isEqualTo(new Answer(firstThing, secondThing, 5000));
	}
	
	@Test
	public void returnAnswerWithSortedThingsForOrderTwo() {
		Thing firstThing = new Thing("firstThing", createDate(5));
		Thing secondThing = new Thing("secondThing", createDate(0));
		
		Which which = new Which(null);
		
		Answer answer = which.createAnswerWithSortedThings(firstThing, secondThing);
		assertThat(answer).isEqualTo(new Answer(secondThing, firstThing, 5000));
	}
	
	@Test
	public void returnAnswerWithSortedThingsForSameOrder() {
		Thing firstThing = new Thing("firstThing", createDate(0));
		Thing secondThing = new Thing("secondThing", createDate(0));
		
		Which which = new Which(null);
		
		Answer answer = which.createAnswerWithSortedThings(firstThing, secondThing);
		assertThat(answer).isEqualTo(new Answer(secondThing, firstThing, 0));
	}
	
	@Test
	public void returnSubAnswerWithEmptyInputList() {
		Thing firstThing = new Thing("firstThing", createDate(5));
		
		Which which = new Which(null);
		List<Answer> subAnswers = which.prerareSubAnswers(firstThing, new ArrayList<>());
		assertThat(subAnswers).isEmpty();
	}
	
	@Test
	public void returnSubAnswerWithSortedThingsForOrderTwo() {
		Thing firstThing = new Thing("firstThing", createDate(0));
		Thing secondThing = new Thing("secondThing", createDate(5));
		List<Thing> subList = Arrays.asList(secondThing);
		Which which = new Which(null);
		List<Answer> subAnswers = which.prerareSubAnswers(firstThing, subList);
		assertThat(subAnswers.size()).isEqualTo(subList.size());
		assertThat(subAnswers.get(0)).isEqualTo(new Answer(firstThing, secondThing, 5000));
	}
	
	@Test
	public void returnSubAnswerWithSortedThingsForOrderThree() {
		Thing firstThing = new Thing("firstThing", createDate(0));
		Thing secondThing = new Thing("secondThing", createDate(5));
		Thing thirdThing = new Thing("thirdThing", createDate(-3));
		List<Thing> subList = Arrays.asList(secondThing, thirdThing);
		
		Which which = new Which(null);
		List<Answer> subAnswers = which.prerareSubAnswers(firstThing, subList);
		
		assertThat(subAnswers.size()).isEqualTo(subList.size());
		assertThat(subAnswers.get(0)).isEqualTo(new Answer(firstThing, secondThing, 5000));
		assertThat(subAnswers.get(1)).isEqualTo(new Answer(thirdThing, firstThing, 3000));
	}
	
	private List<Thing> getInputThings(int numberOfThings) {
		
		return IntStream.range(1, numberOfThings + 1)
				.mapToObj(num -> new Thing(NAME+num, createDate(num)))
				.collect(Collectors.toList());
	}

	private Date createDate(int num) {
		return Date.from(LocalDateTime.of(2020, 9, 04, 12, 00, 00).plusSeconds(num).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	private List<Answer> prepareExpectedAnswers(int numberOfThings,  int seconds) {
		return IntStream.range(0, numberOfThings)
				.mapToObj(num-> new Answer(new Thing(NAME+num, createDate(num)), new Thing(NAME+(num+1), createDate(num+1)), 1000 * seconds))
				.collect(Collectors.toList());
	}
}
