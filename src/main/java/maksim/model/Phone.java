package maksim.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MobilePhone.class, name = "mobilePhone"),
        @JsonSubTypes.Type(value = LandlinePhone.class, name = "landlinePhone")
})
public abstract class Phone {
    private final StringProperty model;
    private final StringProperty number;
    private final StringProperty color;
    private final IntegerProperty callsDuration;

    public Phone(String model, String number, String color, int callsDuration) {
        this.model = new SimpleStringProperty(model);
        this.number = new SimpleStringProperty(number);
        this.color = new SimpleStringProperty(color);
        this.callsDuration = new SimpleIntegerProperty(callsDuration);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public int getCallsDuration() {
        return callsDuration.get();
    }

    public IntegerProperty callsDurationProperty() {
        return callsDuration;
    }

    public void setCallsDuration(int callsDuration) {
        this.callsDuration.set(callsDuration);
    }

    public abstract void call();
}



/*
Phone[] phones = new Phone[] {
        new MobilePhone("iPhone 10", "0733332211", "green", 20),
        new MobilePhone("Xiaomi Redmi 5", "0985544666", "red", 48),
        new MobilePhone("Huawei Honor 10 Lite", "0960099888", "black", 25),
        new LandlinePhone("IBM", "11-29-01", "red", 44),
};

savePhonesAsJson("phones.json", phones);

* */