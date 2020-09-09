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
				Answer answer1 = createAnswer(i, j);
				answers.add(answer1);
			}
		}
		return answers;
	}

	private Answer createAnswer(int i, int j) {
		
		Thing firstThing = inputThings.get(i);
		Thing secondThing = inputThings.get(j);
		Answer answer1 = createAnswerWithSortedThings(firstThing, secondThing);
		return answer1;
	}

	public Answer createAnswerWithSortedThings(Thing firstThing, Thing secondThing) {
		Answer answer1 = new Answer();
		if (firstThing.date.getTime() < secondThing.date.getTime()) {
			answer1.thing1 = firstThing;
			answer1.thing2 = secondThing;
		} else {
			answer1.thing1 = secondThing;
			answer1.thing2 = firstThing;
		}
		answer1.difference = answer1.thing2.date.getTime() - answer1.thing1.date.getTime();
		return answer1;
	}
}
