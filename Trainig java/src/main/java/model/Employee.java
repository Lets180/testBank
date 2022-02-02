package model;

class Employee extends Person {
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee(String name,int age, int weight, int height, String position){
        super(name, age, weight, height);
        this.position = position;
    }
    public Employee(){

    }
}
