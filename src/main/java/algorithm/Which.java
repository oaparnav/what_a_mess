package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Which {
	private final List<Thing> inputThings;

	public Which(List<Thing> inputThings) {
		this.inputThings = inputThings;
	}

	public Answer Find(FT ft) {
		List<Answer> tr = new ArrayList<Answer>();

		for (int i = 0; i < inputThings.size() - 1; i++) {
			for (int j = i + 1; j < inputThings.size(); j++) {
				Answer answer = new Answer();
				if ((inputThings.get(i)).s.getTime() < (inputThings.get(j)).s.getTime()) {
					answer.P1 = inputThings.get(i);
					answer.P2 = inputThings.get(j);
				} else {
					answer.P1 = inputThings.get(j);
					answer.P2 = inputThings.get(i);
				}
				answer.D = answer.P2.s.getTime() - answer.P1.s.getTime();
				tr.add(answer);
			}
		}

		if (tr.size() < 1) {
			return new Answer();
		}

		Answer answer = tr.get(0);
		for (Answer result : tr) {
			switch (ft) {
				case One :
					if (result.D < answer.D) {
						answer = result;
					}
					break;

				case Two :
					if (result.D > answer.D) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
