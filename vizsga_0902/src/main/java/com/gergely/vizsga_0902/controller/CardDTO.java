package com.gergely.vizsga_0902.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CardDTO {

    Long id;

    @NotBlank
    @Size(max = 20, message = "{validation.name.size.too_long}")
    String name;

    String type;

    @Min(value = 0, message = "percent must be greater than 0")
    @Max(value = 100, message = "percent must be less than 100")
    int percent;

    @Min(value = 0, message = "purchasePrice must be greater than 0")
    int purchasePrice;

    @Min(value = 0, message = "purchaseYear must be greater than 0")
    int purchaseYear;

    @Min(value = 0, message = "issueYear must be greater than 0")
    int issueYear;

    @Min(value = 0, message = "issuePrice must be greater than 0")
    int issuePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public int getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(int issuePrice) {
        this.issuePrice = issuePrice;
    }
}
