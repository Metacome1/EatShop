package barBossHouse;

final public class Customer {
    private String firstName;
    private String secondName;
    private int age;
    private Address address;
    public final static Customer MATURE_UNKNOWN_CUSTOMER = new Customer(21);
    public final static Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(14);

    public Customer() {
        this.firstName = "";
        this.secondName = "";
        this.age = -1;
        this.address = Address.EMPTY_ADDRESS;
    }

    public Customer(int age) {
        this.firstName = "";
        this.secondName = "";
        this.age = age;
        this.address = Address.EMPTY_ADDRESS;
    }

    public Customer(String firstName, String secondName, int age, Address address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + (firstName.isEmpty() ? "" :firstName + " ") + (secondName.isEmpty() ? "":secondName) + ", " + (age == -1 ? "": age + " ") + address.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Customer customer = (Customer) obj;

        return (firstName.equals(customer.getFirstName()) & secondName.equals(customer.getSecondName()) & age == customer.getAge() & address.equals(customer.getAddress()));
    }

    @Override
    public int hashCode(){
        return firstName.hashCode()
                ^ secondName.hashCode()
                ^age
                ^address.hashCode();
    }
}
