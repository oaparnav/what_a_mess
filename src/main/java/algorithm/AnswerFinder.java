package algorithm;

import java.util.List;
import java.util.function.BiFunction;

public class AnswerFinder {

	public BiFunction<List<Answer>, Boolean, Answer> finder = (answers, canApply) -> {
		Answer finalAnswer = answers.get(0);
		for (Answer answer : answers) {
			if (canApply) {
				finalAnswer = answer;
			}
		}
		return finalAnswer;
	};

}
