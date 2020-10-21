package com.example.myproject1.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class bookCategory {
    public String matheloai;
    public String tentheloai;
    public String mota;
    public String vitri;

    public bookCategory() {
    }

    public bookCategory(String matheloai, String tentheloai, String mota, String vitri) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.mota = mota;
        this.vitri = vitri;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("matheloai", matheloai);
        result.put("tentheloai", tentheloai);
        result.put("mota", mota);
        result.put("vitri", vitri);

        return result;
    }
}
