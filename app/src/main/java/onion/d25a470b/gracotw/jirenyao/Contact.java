package onion.d25a470b.gracotw.jirenyao;

public class Contact {
    private String number;
    private android.graphics.drawable.Drawable icon;

    public Contact(String number, android.graphics.drawable.Drawable icon) {
        this.number = number;
        this.icon = icon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public android.graphics.drawable.Drawable getIcon() {
        return icon;
    }

    public void setIcon(android.graphics.drawable.Drawable icon) {
        this.icon = icon;
    }
}

