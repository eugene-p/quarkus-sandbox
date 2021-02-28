package org.yp.models;

public class Event {
    public Integer id;
    public String name;
    public String type;

    private static Integer generator = 1;
    
    public Event() {}
    public Event(String name, String type) {
        this.id = generator++;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + this.id + "] name: " + this.name + " type: " + this.type;
    }
}
