/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

package com.example.myproject1.Model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Book implements Serializable {
    public String masach;
    public String tentheloai;
    public String tieude;
    public String tacgia;
    public String nxb;
    public String giabia;
    public String soluong;
    public String asm;

    public Book(String masach, String tentheloai, String tieude, String tacgia, String nxb, String giabia, String soluong, String asm) {
        this.masach = masach;
        this.tentheloai = tentheloai;
        this.tieude = tieude;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.giabia = giabia;
        this.soluong = soluong;
        this.asm = asm;
    }

    public Book() {
    }

    public Book(String masach, String tentheloai, String tieude, String tacgia, String nxb, String giabia, String soluong) {
        this.masach = masach;
        this.tentheloai = tentheloai;
        this.tieude = tieude;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.giabia = giabia;
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("masach", masach);
        result.put("tentheloai", tentheloai);
        result.put("tieude", tieude);
        result.put("tacgia", tacgia);
        result.put("nxb", nxb);
        result.put("giabia", giabia);
        result.put("soluong", soluong);
        result.put("asm", asm);

        return result;
    }
}
