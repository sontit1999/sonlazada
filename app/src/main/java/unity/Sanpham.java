package unity;

import java.io.Serializable;

public class Sanpham implements Serializable {
    String masp;
    String tensp;
    int giasp;
    String linkanhsp;
    int soluong;
    String mota;

    public Sanpham() {
    }

    public Sanpham(String masp, String tensp, int giasp, String linkanhsp, int soluong, String mota) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.linkanhsp = linkanhsp;
        this.soluong = soluong;
        this.mota = mota;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getLinkanhsp() {
        return linkanhsp;
    }

    public void setLinkanhsp(String linkanhsp) {
        this.linkanhsp = linkanhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
