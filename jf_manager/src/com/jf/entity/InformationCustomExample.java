package com.jf.entity;



public class InformationCustomExample extends InformationExample{
	
	@Override
    public InformationCustomCriteria createCriteria() {
		InformationCustomCriteria criteria = new InformationCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class InformationCustomCriteria extends InformationExample.Criteria{
		public Criteria andCatalogAll(Integer catalogId) {
			addCriterion( "FIND_IN_SET(catalog_id,FUN_GET_CATALOG_All_CHILD("+catalogId+"))" );
			return this;
		}
		
		//搜索功能
	    public Criteria andTitleECLike(String value) {
	    	String search = " (title like CONCAT('%', '"+value+"', '%')" + "OR" + " title like CONCAT('%', '"+value+"', '%')" + "OR" + " title like CONCAT('%', '"+value+"', '%'))";
	    	addCriterion(search);
	        return this;
        }
	   /* public Criteria andInfoTypeLikeTo(String infoTypeDsc) {
	    	if (infoTypeDsc.equals("2")) {
	    		addCriterion("(t.info_type = '2' or t.info_type = '2,3' or t.info_type = '2,4' or t.info_type = '2,5' or t.info_type = '2,3,4'  or t.info_type = '2,3,5' or t.info_type = '2,4,3' or t.info_type = '2,4,5')");
			}else if(infoTypeDsc.equals("3")){
				addCriterion("(t.info_type = '3' or t.info_type = '3,2' or t.info_type = '3,4' or t.info_type = '3,5' or t.info_type = '3,2,4'  or t.info_type = '3,2,5' or t.info_type = '3,4,2' or t.info_type = '3,5,2')");
			}else if(infoTypeDsc.equals("4")){
				addCriterion("(t.info_type = '4' or t.info_type = '4,2' or t.info_type = '4,3' or t.info_type = '4,5' or t.info_type = '4,2,3'  or t.info_type = '4,2,5' or t.info_type = '4,3,2' or t.info_type = '4,3,5')");
			}else if(infoTypeDsc.equals("5")){
				addCriterion("(t.info_type = '5' or t.info_type = '5,2' or t.info_type = '5,3' or t.info_type = '5,4' or t.info_type = '5,2,3'  or t.info_type = '5,2,4' or t.info_type = '5,3,2' or t.info_type = '5,4,2')");
			}
	        return this;
	    }*/
	    //筛选功能
	    public Criteria andInfoTypeLikeTo(String infoTypeDsc) {
	    	String infoTypedsc = " (info_type like CONCAT('%', '"+infoTypeDsc+"', '%')" + "OR" + " info_type like CONCAT('%', '"+infoTypeDsc+"', '%')" + "OR" + " info_type like CONCAT('%', '"+infoTypeDsc+"', '%'))";
	    	addCriterion(infoTypedsc);
	        return this;
        }
	}
}