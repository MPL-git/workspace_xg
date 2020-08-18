package com.jf.common.utils;

/**
 * SentinelWebEnum
 * 
 * @author gonghongqing01
 * @since 2016-11-01
 */
public class SentinelWebEnum {

    public static enum OperateStatus {

        OPERATE_SUCCESS(1, "操作成功"),
        OPERATE_PARAM_ERROR(2, "参数错误"),
        OPERATE_SERVICE_EXCEPTION(3, "服务异常");

        private int status;
        private String description;

        OperateStatus(int status, String description) {
            this.status = status;
            this.description = description;
        }

        public int getStatus() {
            return status;
        }

        public String getDescription() {
            return description;
        }

        public static String getDescription(Integer value) {
            for (OperateStatus ft : OperateStatus.values()) {
                if (value != null && ft.getStatus() == value) {
                    return ft.description;
                }
            }

            return "";
        }
    }

    public static enum DataType {

        HDFS("HDFS", "com.baidu.dass.sentinel.scheduler.jobs.impls.HdfsLatestFileStatusJob"),
        FTP("FTP", "com.baidu.dass.sentinel.scheduler.jobs.impls.FtpLatestFileStatusJob"),
        MYSQL("MYSQL", "com.baidu.dass.sentinel.scheduler.jobs.impls.MysqlLatestStatusJob");

        private String type;
        private String description;

        DataType(String type, String description) {
            this.type = type;
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        int status = SentinelWebEnum.OperateStatus.OPERATE_SUCCESS.getStatus();
        String desc = SentinelWebEnum.OperateStatus.OPERATE_SUCCESS.getDescription();
        System.out.println(status + ":" + desc);
        System.out.println(SentinelWebEnum.OperateStatus.getDescription(1));
    }
}
