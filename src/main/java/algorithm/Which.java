package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Which {
	private final List<Thing> inputThings;

	public Which(List<Thing> inputThings) {
		this.inputThings = inputThings;
	}

	public Answer Find(FT ft) {
		List<Answer> answers = prepareAnswers();

		if (answers.size() < 1) {
			return new Answer();
		}

		Answer answer = answers.get(0);
		for (Answer result : answers) {
			switch (ft) {
				case One :
					if (result.difference < answer.difference) {
						answer = result;
					}
					break;

				case Two :
					if (result.difference > answer.difference) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}

	public List<Answer> prepareAnswers() {
		List<Answer> answers = new ArrayList<>();

		for (int i = 0; i < inputThings.size() - 1; i++) {
			for (int j = i + 1; j < inputThings.size(); j++) {
				Answer answer = createAnswerWithSortedThings(inputThings.get(i), inputThings.get(j));
				answers.add(answer);
			}
		}
		return answers;
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
