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
		List<Thing> inputThings = getInputThings(1);

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
		assertThat(result)
				.isEqualTo(new Answer(new Thing(NAME + 1, createDate(1)), new Thing(NAME + 2, createDate(2)), 1000));
	}

	@Test
	public void returnEmptyAnswersForEmptyInputThings() {
		inputThings = getInputThings(0);
		Which which = new Which(inputThings);
		assertThat(which.prepareAnswers()).isEqualTo(new ArrayList<>());
	}

	@Test
	public void returnEmptyAnswersForOneInputThing() {
		inputThings = getInputThings(1);
		Which which = new Which(inputThings);
		assertThat(which.prepareAnswers()).isEqualTo(new ArrayList<>());
	}

	@Test
	public void returnOneAnswersForTwoInputThings() {
		inputThings = getInputThings(2);
		Which which = new Which(inputThings);
		List<Answer> prepareExpectedAnswers = prepareExpectedAnswers(2, 1);
		
		assertThat(which.prepareAnswers().get(0).thing1).isEqualTo(prepareExpectedAnswers.get(1).thing1);
		assertThat(which.prepareAnswers().get(0).thing2).isEqualTo(prepareExpectedAnswers.get(1).thing2);
		assertThat(which.prepareAnswers().get(0).difference).isEqualTo(prepareExpectedAnswers.get(1).difference);
	}

	@Test
	public void returnThreeAnswersForThreeInputThings() {
		inputThings = getInputThings(3);
		Which which = new Which(inputThings);

		List<Answer> answers = which.prepareAnswers();

		assertThat(answers.size()).isEqualTo(3);
		
	}

	@Test
	public void returnSixAnswersForFourInputThings() {
		inputThings = getInputThings(4);
		Which which = new Which(inputThings);

		List<Answer> answers = which.prepareAnswers();

		assertThat(answers.size()).isEqualTo(6);
	}
	
	@Test
	public void returnTenAnswersForFiveInputThings() {
		inputThings = getInputThings(5);
		Which which = new Which(inputThings);

		List<Answer> answers = which.prepareAnswers();

		assertThat(answers.size()).isEqualTo(10);
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
	
	@Test
	public void findLeastAnswerForSingleAnswer() throws Exception {
		Which which = new Which(null);
		Answer answer = which.findAnswerFor(FT.One, prepareExpectedAnswers(1,1));
		assertThat(answer).isEqualTo(prepareExpectedAnswers(1, 1).get(0));
	}
	
	@Test
	public void findHighestAnswerForSingleAnswer() throws Exception {
		Which which = new Which(null);
		Answer answer = which.findAnswerFor(FT.Two, prepareExpectedAnswers(1,1));
		assertThat(answer).isEqualTo(prepareExpectedAnswers(1, 1).get(0));
	}
	
	@Test
	public void findLeastAnswerForTwoAnswers() throws Exception {
		Which which = new Which(null);
		Answer answer = which.findAnswerFor(FT.One, prepareExpectedAnswers(2,1));
		assertThat(answer).isEqualTo(prepareExpectedAnswers(2, 1).get(0));
	}
	
	@Test
	public void findLeastAnswerForMulitpleAnswers() throws Exception {
		Which which = new Which(null);
		Answer answer = which.findAnswerFor(FT.One, prepareExpectedAnswers());
		assertThat(answer).isEqualTo(new Answer(new Thing("thing4", createDate(4)), new Thing("thing5", createDate(5)), 1));
	}
	
	@Test
	public void findHighestAnswerForMulitpleAnswers() throws Exception {
		Which which = new Which(null);
		Answer answer = which.findAnswerFor(FT.Two, prepareExpectedAnswers());
		assertThat(answer).isEqualTo(new Answer(new Thing("thing1", createDate(0)), new Thing("thing2", createDate(3)), 3));
	}
	
	

	private List<Answer> prepareExpectedAnswers() {
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(new Thing("thing1", createDate(0)), new Thing("thing2", createDate(3)), 3));
		answers.add(new Answer(new Thing("thing4", createDate(4)), new Thing("thing5", createDate(5)), 1));
		answers.add(new Answer(new Thing("thing3", createDate(3)), new Thing("thing5", createDate(4)), 2));
		answers.add(new Answer(new Thing("thing5", createDate(5)), new Thing("thing6", createDate(6)), 1));
		return answers;
	}

	private List<Thing> getInputThings(int numberOfThings) {

		return IntStream.range(1, numberOfThings + 1).mapToObj(num -> new Thing(NAME + num, createDate(num)))
				.collect(Collectors.toList());
	}

	private Date createDate(int num) {
		return Date.from(
				LocalDateTime.of(2020, 9, 04, 12, 00, 00).plusSeconds(num).atZone(ZoneId.systemDefault()).toInstant());
	}

	private List<Answer> prepareExpectedAnswers(int numberOfThings, int seconds) {
		return IntStream.range(0, numberOfThings).mapToObj(num -> new Answer(new Thing(NAME + num, createDate(num)),
				new Thing(NAME + (num + 1), createDate(num + 1)), 1000 * seconds)).collect(Collectors.toList());
	}
}
