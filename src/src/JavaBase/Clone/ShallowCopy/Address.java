package JavaBase.Clone.ShallowCopy;

public class Address implements Cloneable {
    private String name;
    @Override
    public Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }

    public Address(String name) {
        this.name = name;
    }
}
