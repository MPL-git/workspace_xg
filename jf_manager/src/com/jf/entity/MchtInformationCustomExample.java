package com.jf.entity;

public class MchtInformationCustomExample extends  MchtInformationExample {
    @Override
    public MchtInformationCustomCriteria createCriteria() {
        MchtInformationCustomCriteria criteria = new MchtInformationCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }


    public class MchtInformationCustomCriteria extends MchtInformationExample.Criteria{
        //商家编号精准查询
        public Criteria andMchtCodeEqualTo(String mchtCode) {
            addCriterion("EXISTS(select mi.id from bu_mcht_info mi where mi.id=bmi.mcht_id and mi.mcht_code='"+mchtCode+"')");
            return this;
        }
        //公司名称模糊查询
        public Criteria andCompanyNameLikeTo(String companyName) {
            addCriterion(" EXISTS(select mi.id from bu_mcht_info mi where mi.del_flag = '0' and mi.id = bmi.mcht_id and mi.company_name  like  CONCAT('%','"+companyName+"','%'))");
            return this;
        }

    }


}
