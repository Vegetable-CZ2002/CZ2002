package beans;


/**
 * Represents the staff in the restaurant
 *
 * @author Ruan Donglin
 */
public class Staff {
    private String name;
    private long id;
    private jobTitle job;
    private gender gender;

    public enum jobTitle {GENERAL_MANAGER, ASSISTANT_MANAGER, SERVER, CASHIER}

    public enum gender {MALE, FEMALE}

    public Staff(long id, String name, jobTitle job, gender gender) {
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

    public long getId() {
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

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", job=" + job +
                ", gender=" + gender +
                '}';
    }
}
