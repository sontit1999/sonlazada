package unity;

public class Truyen {
    String tieude;
    String link;

    public Truyen(String tieude, String link) {
        this.tieude = tieude;
        this.link = link;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
