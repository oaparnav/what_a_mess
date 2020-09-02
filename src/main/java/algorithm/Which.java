package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Which {
	private final List _p;

	public Which(List p) {
		_p = p;
	}

	public F Find(FT ft) {
		List<F> tr = new ArrayList<F>();

		for (int i = 0; i < _p.size() - 1; i++) {
			for (int j = i + 1; j < _p.size(); j++) {
				F r = new F();
				if (((Thing) _p.get(i)).s.getTime() < ((Thing) _p.get(j)).s.getTime()) {
					r.P1 = (Thing) _p.get(i);
					r.P2 = (Thing) _p.get(j);
				} else {
					r.P1 = (Thing) _p.get(j);
					r.P2 = (Thing) _p.get(i);
				}
				r.D = r.P2.s.getTime() - r.P1.s.getTime();
				tr.add(r);
			}
		}

		if (tr.size() < 1) {
			return new F();
		}

		F answer = tr.get(0);
		for (F result : tr) {
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
