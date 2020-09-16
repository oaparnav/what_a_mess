package algorithm;

import java.util.List;

public class MaxCriteria implements AnswerStrategy {

	@Override
	public Answer apply(List<Answer> answers) {
		Answer finalAnswer = answers.get(0);
		for (Answer answer : answers) {
			if (answer.difference > finalAnswer.difference) {
				return answer;
			}
		}
		return finalAnswer;
	}

}
