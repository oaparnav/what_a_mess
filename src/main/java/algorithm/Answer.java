package algorithm;

public class Answer {

	private Thing thing1;
	private Thing thing2;
	private long difference;

	public Answer(Thing thing1, Thing thing2, long difference) {
		super();
		this.thing1 = thing1;
		this.thing2 = thing2;
		this.difference = difference;
	}
	
	public Answer() {
	}
	
	public Thing getThing1() {
		return thing1;
	}

	public Thing getThing2() {
		return thing2;
	}

	public long getDifference() {
		return difference;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (difference ^ (difference >>> 32));
		result = prime * result + ((thing1 == null) ? 0 : thing1.hashCode());
		result = prime * result + ((thing2 == null) ? 0 : thing2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (difference != other.difference)
			return false;
		if (thing1 == null) {
			if (other.thing1 != null)
				return false;
		} else if (!thing1.equals(other.thing1))
			return false;
		if (thing2 == null) {
			if (other.thing2 != null)
				return false;
		} else if (!thing2.equals(other.thing2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [thing1=" + thing1 + ", thing2=" + thing2 + ", difference=" + difference + "]";
	}

}
