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
    public int getId() { return id; }
    public String getEmail() { return email; }
}