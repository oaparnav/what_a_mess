package algorithm;

import java.util.Date;

public class Thing {
	public String name;
	public Date date;

	public String getC() {
		return name;
	}

	public void setC(String c) {
		this.name = c;
	}

	public Date getS() {
		return date;
	}

	public void setS(Date anotherS) {
		this.date = anotherS;
	}

	public Thing(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}

}
