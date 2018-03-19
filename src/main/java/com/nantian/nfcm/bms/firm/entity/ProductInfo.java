package com.nantian.nfcm.bms.firm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_info")
public class ProductInfo implements Serializable {
    private String productNum;
    private String productName;
    private String qsNum;
    private String gbNum;
    private FirmInfo firmInfo;
    private String productType;
    private String productTech;
    private String shelfLife;
    private String quantity;
    private String ingredient;
    private String productionDate;
    private String pictureUrl;
    private String movieUrl;
    
    private String qualityCode;
    private String checkoutResult;
    private String checkoutId;
    private String proEnPname;
    private String proPinTro;
    private String proNote;
    private String commodityCodes;
    private String productImages;
    private String menufactureAddress;
    private String institutionCode;
    private String dutyPerson;
    private String manufacturePhone;
    private String anticounterfeitingTecnology;
    private String fwMsg;
    private String checkOut;
    private String spid;
    private String queryUrl;
    

    public ProductInfo() {
    }

    public ProductInfo(String productNum) {
        this.productNum = productNum;
    }
    @Id
    @Column(name = "product_num")
    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "qs_num")
    public String getQsNum() {
        return qsNum;
    }

    public void setQsNum(String qsNum) {
        this.qsNum = qsNum;
    }

    @Basic
    @Column(name = "gb_num")
    public String getGbNum() {
        return gbNum;
    }

    public void setGbNum(String gbNum) {
        this.gbNum = gbNum;
    }

    @ManyToOne(targetEntity = FirmInfo.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_num")
    public FirmInfo getFirmInfo() {
        return firmInfo;
    }

    public void setFirmInfo(FirmInfo firmInfo) {
        this.firmInfo = firmInfo;
    }

    @Basic
    @Column(name = "product_type")
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Basic
    @Column(name = "product_tech")
    public String getProductTech() {
        return productTech;
    }

    public void setProductTech(String productTech) {
        this.productTech = productTech;
    }

    @Basic
    @Column(name = "shelf_life")
    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Basic
    @Column(name = "quantity")
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "ingredient")
    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Basic
    @Column(name = "production_date")
    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
    
    @Basic
    @Column(name = "picture_url")
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Basic
    @Column(name = "movie_url")
	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}
	
	@Basic
    @Column(name = "quality_code")
	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}
	@Basic
    @Column(name = "checkout_result")
	public String getCheckoutResult() {
		return checkoutResult;
	}

	public void setCheckoutResult(String checkoutResult) {
		this.checkoutResult = checkoutResult;
	}
	@Basic
    @Column(name = "checkout_id")
	public String getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(String checkoutId) {
		this.checkoutId = checkoutId;
	}
	@Basic
    @Column(name = "pro_en_pname")
	public String getProEnPname() {
		return proEnPname;
	}

	public void setProEnPname(String proEnPname) {
		this.proEnPname = proEnPname;
	}
	@Basic
    @Column(name = "pro_pin_tro")
	public String getProPinTro() {
		return proPinTro;
	}

	public void setProPinTro(String proPinTro) {
		this.proPinTro = proPinTro;
	}
	@Basic
    @Column(name = "pro_note")
	public String getProNote() {
		return proNote;
	}

	public void setProNote(String proNote) {
		this.proNote = proNote;
	}
	@Basic
    @Column(name = "commodity_codes")
	public String getCommodityCodes() {
		return commodityCodes;
	}

	public void setCommodityCodes(String commodityCodes) {
		this.commodityCodes = commodityCodes;
	}
	@Basic
    @Column(name = "product_images")
	public String getProductImages() {
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}
	@Basic
    @Column(name = "manufacture_address")
	public String getMenufactureAddress() {
		return menufactureAddress;
	}

	public void setMenufactureAddress(String menufactureAddress) {
		this.menufactureAddress = menufactureAddress;
	}
	@Basic
    @Column(name = "institution_code")
	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	@Basic
    @Column(name = "duty_person")
	public String getDutyPerson() {
		return dutyPerson;
	}

	public void setDutyPerson(String dutyPerson) {
		this.dutyPerson = dutyPerson;
	}
	@Basic
    @Column(name = "manufacture_phone")
	public String getManufacturePhone() {
		return manufacturePhone;
	}

	public void setManufacturePhone(String manufacturePhone) {
		this.manufacturePhone = manufacturePhone;
	}
	@Basic
    @Column(name = "anticounterfeiting_technology")
	public String getAnticounterfeitingTecnology() {
		return anticounterfeitingTecnology;
	}

	public void setAnticounterfeitingTecnology(String anticounterfeitingTecnology) {
		this.anticounterfeitingTecnology = anticounterfeitingTecnology;
	}
	@Basic
    @Column(name = "fw_msg")
	public String getFwMsg() {
		return fwMsg;
	}

	public void setFwMsg(String fwMsg) {
		this.fwMsg = fwMsg;
	}
	@Basic
    @Column(name = "check_out")
	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	@Basic
    @Column(name = "spid")
	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}
	@Basic
    @Column(name = "query_url")
	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
    
    
}
