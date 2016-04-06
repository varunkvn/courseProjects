import java.io.*;

public class Object implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id_;
private String topic_;
private String name_;
private int noofitems_;
private double cost_;
public Object() {
}
public Object(int id, String name, int noofitems,double cost,String topic) {
id_ = id;
topic_ = topic;
name_ = name;
cost_=cost;
noofitems_=noofitems;
}

public int getId() {
return id_;
}

public void setId(int id) {
id_ = id;
}

public int getNoofitems() {
return noofitems_;
}

public void setNoofitems(int noofitems) {
	noofitems_ = noofitems_;
}


public double getCost() {
return cost_;
}

public void setCost(double cost) {
	cost_ =cost;
}

public String getTopic() {
return topic_;
}

public void setTopic(String topic) {
topic_ = topic;
}

public String getName() {
return name_;
}

public void setName(String name) {
name_ = name;
}

public String toString() {
return id_ + " - " + name_+" - "+noofitems_+" - "+cost_+" - "+topic_;
}
}