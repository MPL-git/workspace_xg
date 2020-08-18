package com.jf.entity;

import com.jf.entity.ProductExample.Criteria;

public class ProductItemCustomExample extends ProductItemExample{
	@Override
    public ProductItemExampleCriteria createCriteria() {
		ProductItemExampleCriteria criteria = new ProductItemExampleCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class ProductItemExampleCriteria extends ProductItemExample.Criteria{
		public Criteria andMchtIdEqualTo(Integer mchtId) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.mcht_id="+mchtId+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andBrandIdEqualTo(Integer brandId) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.brand_id="+brandId+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andAuditStatusEqualTo(String auditStatus) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.audit_status="+auditStatus+" and p.del_flag='0')");
			return this;
		}
		
		
		public Criteria andProductType1(Integer productType1) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.product_type1_id="+productType1+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andProductType2(Integer productType2) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.product_type2_id="+productType2+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andProductType3(Integer productType3) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.product_type_id="+productType3+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andSaleTypeEqualTo(String saleType) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.sale_type="+saleType+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andStatusEqualTo(String status) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.status="+status+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andAuditStatusNotEqualTo(String auditStatusNot) {
			addCriterion(" NOT EXISTS(select p.id from bu_product p where p.id=bpi.product_id and p.audit_status="+auditStatusNot+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andBrandIsEffect() {
			   addCriterion(" EXISTS (select pb.id from bu_mcht_product_brand mpb,bu_product_brand pb,bu_product p where pb.id=mpb.product_brand_id and mpb.del_flag='0' and pb.del_flag='0' and p.del_flag='0' and pb.`status`='1' and mpb.`status`='1' and mpb.audit_status='3' and pb.id=p.brand_id and p.id=bpi.product_id and mpb.mcht_id=t.mcht_id)");
			   return this;
		}
		
		public Criteria andNameLike(String name) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.name like"+name+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andArtNoLike(String artNo) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.art_no like "+artNo+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andCodeEqualTo(String code) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.code="+code+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andRemarksLike(String remarks) {
			addCriterion(" bpi.product_id IN (select p.id from bu_product p where p.remarks="+remarks+" and p.del_flag='0')");
			return this;
		}
		
		public Criteria andSingleProductActivityIdEqualTo(Integer singleProductActivityId) {
			addCriterion(" bpi.product_id IN (select spa.product_id from bu_single_product_activity spa where spa where spa.id="+singleProductActivityId+" and spa.del_flag='0')");
			return this;
		}
	}
	
}