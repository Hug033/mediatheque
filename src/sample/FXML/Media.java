package sample.FXML;

public class Media {

    private byte[] photo;
    private String title;
    private String description;
    private int nbRate;
    private int totalRate;
    private int rate;

    public Media (byte[] photo, String title, String description, int nbRate, int totalRate) {
        this.photo = photo;
        this.title = title;
        this.description = description;
        this.nbRate = nbRate;
        this.totalRate = totalRate;
        this.rate = (int)(totalRate / nbRate);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNbRate() {
        return nbRate;
    }

    public int getRate() {
        return rate;
    }

    public byte[] getPhoto() {
        return photo;
    }

}
