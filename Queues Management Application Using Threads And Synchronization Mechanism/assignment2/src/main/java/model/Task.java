package model;

public class Task implements Comparable<Task> {
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime)
    {
        this.arrivalTime = arrivalTime;
        this.ID = ID;
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getId() { return ID;
    }

    @Override
    public int compareTo(Task o) {
        if (this.arrivalTime < o.getArrivalTime())
            return 1;
        return 0;
    }
}

