package Exception;

public class IsRightAge extends Exception{
    private int age;

    /*public void setAge(final int age) {
        this.age = age;
    }*/

    public int getAge() {
        return this.age;
    }
    public IsRightAge(String message, int age){
        super(message);
        this.age=age;
    }
}
