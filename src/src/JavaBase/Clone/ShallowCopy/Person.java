package JavaBase.Clone.ShallowCopy;

public class Person implements Cloneable {
    private Address address;

    @Override
    protected Person clone(){
        try {
            return (Person)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }

    public Address getAddress() {
        return address;
    }

    public Person(Address address) {
        this.address = address;
    }
}
