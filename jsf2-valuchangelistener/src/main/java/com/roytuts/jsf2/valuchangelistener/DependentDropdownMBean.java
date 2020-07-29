package com.roytuts.jsf2.valuchangelistener;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

@ViewScoped
@ManagedBean
public class DependentDropdownMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer category;
	private Integer subCategory;
	private Integer noOfTutorials;
	private List<Category> categories;
	private List<Category> subCategories;
	private QueryHelper queryHelper;

	public DependentDropdownMBean() {
		queryHelper = new QueryHelper();
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getNoOfTutorials() {
		populateNoOfTutorials();
		return noOfTutorials;
	}

	public void setNoOfTutorials(Integer noOfTutorials) {
		this.noOfTutorials = noOfTutorials;
	}

	public List<Category> getCategories() {
		if (categories == null) {
			populateCategories();
		}
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getSubCategories() {
		populateSubCategories();
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}

	private void populateCategories() {
		categories = queryHelper.getAllCategories();
	}

	private void populateSubCategories() {
		if (category != null) {
			subCategories = queryHelper.getAllSubCategories(category);
		}
	}

	private void populateNoOfTutorials() {
		if (subCategory != null) {
			noOfTutorials = queryHelper.getNoOfTutorials(subCategory);
		}
	}

	// when category selection gets changed
	public void onCategorySelect(ValueChangeEvent vce) {
		System.out.println("onCategorySelect => vce.getNewValue().toString(): " + vce.getNewValue().toString());
		Integer newCat = Integer.valueOf(vce.getNewValue().toString());
		if (newCat != category) {
			setCategory(newCat);
		}
	}

	// when sub-category selection gets changed
	public void onSubcategorySelect(ValueChangeEvent vce) {
		System.out.println("onSubcategorySelect => vce.getNewValue().toString(): " + vce.getNewValue().toString());
		Integer newSubcat = Integer.valueOf(vce.getNewValue().toString());
		if (newSubcat != subCategory) {
			setSubCategory(newSubcat);
		}
	}

}
