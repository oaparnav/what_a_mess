package algorithm;

public enum Criteria {
	
	MIN(new MinCriteria()), MAX(new MaxCriteria());

	private AnswerStrategy strategy;

	Criteria(AnswerStrategy strategy) {
		this.strategy = strategy;
	}
	
	public AnswerStrategy getStrategy() {
		return strategy;
	}
}