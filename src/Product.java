import java.io.*;

public class Product implements Serializable, Externalizable {
    private int id;
    private float price;
    private String title;
    private float rating;

    public static final long serialVersionUID = 2;

    public Product(){}

    public Product(int id, float price, String title, float rating) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return id + " " + price + " " + title + " " + rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeFloat(rating);
        out.writeUTF(title);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = in.readInt();
        this.rating = in.readFloat();
        this.title = in.readUTF();
        this.rating = 0f;
    }
}
