package entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyLocalTime implements Comparable<MyLocalTime>{
	private LocalTime time;

	public MyLocalTime(LocalTime time) {
		super();
		this.time = time;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public int compareTo(MyLocalTime o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.getTime());
	}
	
	@Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof MyLocalTime)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        MyLocalTime c = (MyLocalTime) o;
         
        // Compare the data members and return accordingly
        return 0 == c.getTime().compareTo(time); 
    }
	
	@Override
    public String toString() {
		// create formatter Object
        DateTimeFormatter formatter
            = DateTimeFormatter.ISO_TIME;
        // apply format
        return this.time.format(formatter);
    }

}
