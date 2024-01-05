package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    //private Task currentTask;
    private int id;
    private AtomicInteger totalTime;

    public Server(BlockingQueue<Task> tasks, AtomicInteger waitingPeriod, int id){
        this.tasks = tasks;
        this.waitingPeriod = waitingPeriod;
        this.id = id;

        this.totalTime = new AtomicInteger();
        this.totalTime.set(0);
    }

    public void addTask(Task newTask){
        tasks.offer(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run(){
        while(true){
            if (tasks.size() > 0)
            {
                try {
                    Task currentTask = tasks.peek();
                    //this.currentTask = currentTask;
                    this.sleep(1000 * currentTask.getServiceTime());
                    waitingPeriod.addAndGet(-currentTask.getServiceTime());
                    tasks.poll();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public int getQueueLenght()
    {
        return tasks.size();
    }

    public BlockingQueue<Task> getTasks() {

        return tasks;
    }

    public int getWaitingPeriod() {

        return waitingPeriod.get();
    }

    public void assignTask(Task task) {
        waitingPeriod.addAndGet(task.getServiceTime());
        totalTime.addAndGet(task.getServiceTime());
        this.tasks.add(task);
    }

    public AtomicInteger getTotalTime() {
        return totalTime;
    }

    public BlockingQueue<Task> getTask(){
        return tasks;
    }


//    public boolean isBusy() {
//        return currentTask != null;
//    }

    public int getServerId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
