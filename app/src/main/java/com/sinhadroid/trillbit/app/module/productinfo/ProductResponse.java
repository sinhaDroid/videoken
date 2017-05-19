package com.sinhadroid.trillbit.app.module.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ProductResponse{

	@JsonProperty("Coupon")
	private String coupon;

	@JsonProperty("Added")
	private String added;

	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("ProductImage")
	private String productImage;

	@JsonProperty("DiscountInfo")
	private String discountInfo;

	@JsonProperty("UsedToday")
	private String usedToday;

	public void setCoupon(String coupon){
		this.coupon = coupon;
	}

	public String getCoupon(){
		return coupon;
	}

	public void setAdded(String added){
		this.added = added;
	}

	public String getAdded(){
		return added;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
	}

	public void setDiscountInfo(String discountInfo){
		this.discountInfo = discountInfo;
	}

	public String getDiscountInfo(){
		return discountInfo;
	}

	public void setUsedToday(String usedToday){
		this.usedToday = usedToday;
	}

	public String getUsedToday(){
		return usedToday;
	}

	@Override
 	public String toString(){
		return 
			"ProductResponse{" + 
			"coupon = '" + coupon + '\'' + 
			",added = '" + added + '\'' + 
			",productName = '" + productName + '\'' + 
			",productImage = '" + productImage + '\'' + 
			",discountInfo = '" + discountInfo + '\'' + 
			",usedToday = '" + usedToday + '\'' + 
			"}";
		}
}