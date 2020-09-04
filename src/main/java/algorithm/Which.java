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
				Answer answer1 = new Answer();
				if ((inputThings.get(i)).date.getTime() < (inputThings.get(j)).date.getTime()) {
					answer1.thing1 = inputThings.get(i);
					answer1.thing2 = inputThings.get(j);
				} else {
					answer1.thing1 = inputThings.get(j);
					answer1.thing2 = inputThings.get(i);
				}
				answer1.difference = answer1.thing2.date.getTime() - answer1.thing1.date.getTime();
				answers.add(answer1);
			}
		}
		return answers;
	}
}
