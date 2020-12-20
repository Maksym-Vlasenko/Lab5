package maksim.model;

public class MobilePhone extends Phone {
    public MobilePhone(String model, String number, String color, int callsDuration) {
        super(model, number, color, callsDuration);
    }

    @Override
    public void call() {
        System.out.println("Call with mobile phone");
    }
}
