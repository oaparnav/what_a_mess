package algorithm;

import java.util.Date;

public class Thing {
	private String name;
	private Date date;

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public String getC() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Thing other = (Thing) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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

	@Override
	public String toString() {
		return "Thing [name=" + name + ", date=" + date + "]";
	}

}
