package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Which {

	private final List<Answer> answers;

	public Which(List<Thing> inputThings) {
		this.answers = prepareAnswers(inputThings);
	}

	public Answer Find(Criteria criteria) {
		if (answers.size() < 1) {
			return new Answer();
		}

		return findAnswerFor(criteria, answers);
	}

	public Answer findAnswerFor(Criteria criteria, List<Answer> answers) {
		return criteria.getStrategy().apply(answers);
	}

	public List<Answer> prepareAnswers(List<Thing> inputThings) {
		return Optional.ofNullable(inputThings).map(things -> {
			return IntStream.range(0, things.size()).mapToObj(num -> {
				Thing firstThing = things.get(num);
				List<Thing> subList = things.subList(num+1, things.size());
				return prerareSubAnswers(firstThing, subList);
			}).flatMap(answers -> answers.stream())
					.collect(Collectors.toList());
		}).orElse(new ArrayList<Answer>());

	}

	public List<Answer> prerareSubAnswers(Thing firstThing, List<Thing> subList) {
		List<Answer> subAnswers = subList.stream().map(secondThing -> 
		createAnswerWithSortedThings(firstThing, secondThing))
				.collect(Collectors.toList());
		return subAnswers;
	}

	public Answer createAnswerWithSortedThings(Thing firstThing, Thing secondThing) {

		if (firstThing.getDate().getTime() < secondThing.getDate().getTime()) {
			return createAnswer(firstThing, secondThing);
		} else {
			return createAnswer(secondThing, firstThing);
		}
	}

	private Answer createAnswer(Thing firstThing, Thing secondThing) {
		return new Answer(firstThing, secondThing, finDifference(firstThing, secondThing));
	}

	private long finDifference(Thing firstThing, Thing secondThing) {
		return secondThing.getDate().getTime() - firstThing.getDate().getTime();
	}
}
