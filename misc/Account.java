package misc;

public abstract class Account {
    private String name;
    private String password;
    private String email;

    public Account(String name, String password, String email) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public abstract void showRole();


    @Override
    public String toString() {
        return getName() + " (" + this.getClass().getSimpleName() + ")";
    }
}
