package algorithm;

import java.util.List;
import java.util.function.BiPredicate;

public class MaxCriteria implements AnswerStrategy {

	@Override
	public Answer apply(List<Answer> answers) {
		BiPredicate<Long, Long> evaluate = (val1, val2) -> val1 > val2;
		return new AnswerFinder().find.apply(answers, evaluate);
	}

}
