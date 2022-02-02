package model;

final class  Chief extends Employee {
    private Employee[] subordinate;
    private int amountSubordinate;

    public int getAmountSubordinate() {
        return amountSubordinate;
    }

    public void setAmountSubordinate(int amountSubordinate) {
        this.amountSubordinate = amountSubordinate;
    }

    public Employee[] getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(Employee[] subordinate) {
        this.subordinate = subordinate;
    }
    public Chief(String name,int age, int weight, int height, String position, Employee[] subordinate, int amountSubordinate){
        super(name, age, weight, height, position);
        this.amountSubordinate=amountSubordinate;
        this.subordinate = subordinate;
    }
}
