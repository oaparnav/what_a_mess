package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Which {
	private final List<Thing> inputThings;

	public Which(List<Thing> inputThings) {
		this.inputThings = inputThings;
	}

	public Answer Find(FT ft) {
		List<Answer> answers = new ArrayList<>();

		perpareListOfAnswers(answers);

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

	public void perpareListOfAnswers(List<Answer> tr) {
		for (int i = 0; i < inputThings.size() - 1; i++) {
			for (int j = i + 1; j < inputThings.size(); j++) {
				Answer answer = new Answer();
				if ((inputThings.get(i)).s.getTime() < (inputThings.get(j)).s.getTime()) {
					answer.thing1 = inputThings.get(i);
					answer.thing2 = inputThings.get(j);
				} else {
					answer.thing1 = inputThings.get(j);
					answer.thing2 = inputThings.get(i);
				}
				answer.difference = answer.thing2.s.getTime() - answer.thing1.s.getTime();
				tr.add(answer);
			}
		}
	}
}
