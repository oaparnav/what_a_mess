package algorithm;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class AnswerFinder {

	public BiFunction<List<Answer>, BiPredicate<Long, Long>, Answer> find = (answers, evaluate) -> {
		Answer finalAnswer = answers.get(0);
		for (Answer answer : answers) {
			if (evaluate.test(answer.difference, finalAnswer.difference)) {
				finalAnswer = answer;
			}
		}
		return finalAnswer;
	};

}
