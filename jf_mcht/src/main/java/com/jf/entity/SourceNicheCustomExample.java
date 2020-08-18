package com.jf.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class SourceNicheCustomExample extends SourceNicheExample{
           
	@Override
    public SourceNicheCustomCriteria createCriteria() {
		SourceNicheCustomCriteria criteria = new SourceNicheCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
	
	public class SourceNicheCustomCriteria extends SourceNicheExample.Criteria{

		public Criteria andProductNameLike(String name) {
			// TODO Auto-generated method stub
			addCriterion("EXISTS(select id FROM bu_product p WHERE p.id = a.link_id and p.name like '%"+name+"%')");
			return this;
		}

		public Criteria andProductArtNoEqualTo(String artNoString) {
			// TODO Auto-generated method stub
			addCriterion(" a.link_id in(select id FROM bu_product p WHERE p.art_no in("+artNoString+"))");
			return this;
		}

		public Criteria andCodesIn(String productIdString) {
			// TODO Auto-generated method stub
			addCriterion("a.link_id in(select id FROM bu_product p WHERE p.code in("+productIdString+"))");
			return this;
		}

		/**
		 * @MethodName andProductStatusJudge
		 * @Description TODO
		 * @author chengh
		 * @date 2019年7月15日 下午8:11:14
		 */
		public void andProductStatusJudge() {
			// TODO Auto-generated method stub
			addCriterion(" EXISTS(select id FROM bu_product p WHERE p.id = a.link_id and p.status = '1' and p.del_flag='0')");
		}
	}
}