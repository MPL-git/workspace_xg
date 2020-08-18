package com.jf.common.utils.comment;

/**
 * @author Pengl
 * @create 2020-07-24 上午 10:44
 */
public enum ProductCommentEnum {

	ONE_STAR(1, "该用户觉得商品不符合预期"),
	TWO_STAR(2, "该用户觉得商品一般"),
	THREE_STAR(3, "该用户觉得商品较好"),
	FOUR_STAR(4, "该用户觉得商品很好"),
	FIVE_STAR(5, "该用户觉得商品很好，给出了5星好评");

	private Integer star;
	private String desc;

	public static String getCommentDesc(Integer star) {
		String desc = "该用户觉得商品不符合预期";
		if(star != null) {
			ProductCommentEnum[] productCommentEnumArray = ProductCommentEnum.values();
			for(ProductCommentEnum element : productCommentEnumArray) {
				if(element.getStar() == star ) {
					desc = element.getDesc();
				}
			}
		}
		return desc;
	}

	ProductCommentEnum(Integer star, String desc) {
		this.star = star;
		this.desc = desc;
	}

	public Integer getStar() {
		return star;
	}

	public String getDesc() {
		return desc;
	}
}
