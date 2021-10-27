package beans;


import java.util.Objects;

public class Staff {
    private String name; 
    private long id;
    private jobTitle job;
    private gender gender;

    enum jobTitle {SENIOR_MANAGER, RESTAURANT_MANAGER, WAITRESS, WAITER;}
    enum gender {MALE, FEMALE;}


    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", job=" + job +
                ", gender=" + gender +
                '}';
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return id == staff.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
