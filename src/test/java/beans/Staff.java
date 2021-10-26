package beans;


public class Staff {
    private String name; 
    private int id;
    private jobTitle job;
    private gender gender;
    private enum jobTitle {SENIOR_MANAGER, RESTAURANT_MANAGER, WAITRESS, WAITER;}
    private enum gender {MALE, FEMALE;}

    public Staff(String name, int id, jobTitle job, gender gender) {
        this.name = name;
        this.id = id;
        this.job = job;
        this.gender = gender;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public jobTitle getJob() {
        return this.job;
    }

    public void setJob(jobTitle job) {
        this.job = job;
    }

    public gender getGender() {
        return this.gender;
    }

    public void setGender(gender gender) {
        this.gender = gender;
    }
}
