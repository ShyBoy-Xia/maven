package com.neu.shop.pojo;

public class CategorySec {
    private Integer catesecid;

    private Integer cateid;

    private String catesecname;

    public Integer getCatesecid() {
        return catesecid;
    }

    public void setCatesecid(Integer catesecid) {
        this.catesecid = catesecid;
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public String getCatesecname() {
        return catesecname;
    }

    public void setCatesecname(String catesecname) {
        this.catesecname = catesecname == null ? null : catesecname.trim();
    }

	@Override
	public String toString() {
		return "CategorySec [catesecid=" + catesecid + ", cateid=" + cateid + ", catesecname=" + catesecname + "]";
	}
    
    
}