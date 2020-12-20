package maksim.model;

public class LandlinePhone extends Phone {
    public LandlinePhone(String model, String number, String color, int callsDuration) {
        super(model, number, color, callsDuration);
    }

    @Override
    public void call() {
        System.out.println("Call with landline phone");
    }
}
