package maksim.model;

public class MobilePhone extends Phone {

    public MobilePhone() {
        super("","","", "",-1);
    }

    public MobilePhone(String model, String number, String color, int callsDuration) {
        super(model, number, color, "MOBILE", callsDuration);
    }

    @Override
    public void call() {
        System.out.println("Call with mobile phone");
    }
}
