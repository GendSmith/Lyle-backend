

public class User {
    int id;
    String username;
    String password;
    String profilePic;
    String job;
    int age;
    boolean gender;

    public User(){

    }

    public  User(String username, String password){
        this.username = username;
        this.password = password;
    }


    public  User(String username, String password, String profilePic, String job, int age, boolean gender){
        this.username = username;
        this.password = password;
        this.profilePic = profilePic;
        this.job = job;
        this.age = age;
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
