package groupassessment;

public abstract class Person {
    protected String name;
    protected int id;
    protected String email;
    
    public Person(String name, int id) {
        this.name = name;
        this.id = id;
        this.email = "";
    }
    
    public Person(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }
    
    public abstract void displayInfo();
    public abstract String getRole();
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}