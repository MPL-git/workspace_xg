package com.jf.entity;

/**
 * @author Pengl
 * @create 2019-10-18 下午 3:29
 */
public class AndroidChannelGroupSetDtlCustomExample extends AndroidChannelGroupSetDtlExample {

    @Override
    public AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria createCriteria() {
        AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria criteria = new AndroidChannelGroupSetDtlCustomExample.AndroidChannelGroupSetDtlCustomCriteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    public class AndroidChannelGroupSetDtlCustomCriteria extends AndroidChannelGroupSetDtlExample.Criteria{

        public void andGroupStatusEqualTo(String groupStatus) {
            addCriterion(" EXISTS(select a.id from bu_android_channel_group a where t.android_channel_group_id = a.id and a.del_flag ='0' and a.status='"+groupStatus+"')");
        }

        public Criteria andAndroidChannelGroupSetStatusEqualTo() {
            addCriterion(" EXISTS(SELECT a.id from bu_android_channel_group_set a where a.del_flag = '0' and a.status = '1' and a.id = t.android_channel_group_set_id )");
            return this;
        }

    }

}
