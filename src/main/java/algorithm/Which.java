package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Which {
	private final List<Thing> inputThings;

	public Which(List<Thing> inputThings) {
		this.inputThings = inputThings;
	}

	public Answer Find(Criteria criteria) {
		List<Answer> answers = prepareAnswers();

		if (answers.size() < 1) {
			return new Answer();
		}

		return findAnswerFor(criteria, answers);
	}

	public Answer findAnswerFor(Criteria criteria, List<Answer> answers) {
		return criteria.getStrategy().apply(answers);
	}

	public List<Answer> prepareAnswers() {
		List<Answer> answers = new ArrayList<>();

		for (int i = 0; i < inputThings.size() - 1; i++) {
			Thing firstThing = inputThings.get(i);
			List<Thing> subList = inputThings.subList(i+1, inputThings.size());
			List<Answer> subAnswers = prerareSubAnswers(firstThing, subList);
			answers.addAll(subAnswers);
		}
		return answers;
	}

	public List<Answer> prerareSubAnswers(Thing firstThing, List<Thing> subList) {
		List<Answer> subAnswers = subList.stream().map(secondThing -> 
		createAnswerWithSortedThings(firstThing, secondThing))
				.collect(Collectors.toList());
		return subAnswers;
	}

	public Answer createAnswerWithSortedThings(Thing firstThing, Thing secondThing) {
		Answer answer = new Answer();
		if (firstThing.date.getTime() < secondThing.date.getTime()) {
			answer.thing1 = firstThing;
			answer.thing2 = secondThing;
		} else {
			answer.thing1 = secondThing;
			answer.thing2 = firstThing;
		}
		answer.difference = answer.thing2.date.getTime() - answer.thing1.date.getTime();
		return answer;
	}
}
