package com.company;

/**
 * Created by andreibudin on 17.07.2018.
 */
public class PropertiesMethods {
    private String name;
    private long min;
    private long max;
    private long avg;
    private long count;
    //private int max_id;

    public PropertiesMethods() {
    }

    public PropertiesMethods(String name, long min, long max, long avg, long count) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.count = count;
        //this.max_id = max_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMin() {
        return  min;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMax() {
        return max;
    }

    public void setAvg(long avg) {
        this.avg = avg;
    }

    public long getAvg() {
        return avg;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    /*public void setMax_id(int max_id) {
        this.max_id = max_id;
    }

    public int getMax_id() { return max_id; }*/

    @Override
    public String toString() {
        return "OperationsImpl: " + name + " min " + min + ", max " + max + ", avg " + avg +", count " + count;
    }
}
