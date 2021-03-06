package com.angelatech.yeyelive.db.model;




import com.angelatech.yeyelive.db.DBConfig;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 系统消息数据库模型
 */

@DatabaseTable(tableName = DBConfig.TABLE_SYSTEM_MESSAGE)
public class SystemMessageDBModel {

    @DatabaseField(generatedId=true)
    private long id;//

    @DatabaseField(columnName="type_code")
    public int type_code;
    @DatabaseField(columnName="data")
    public String data;

    @DatabaseField(columnName="content")
    public String content;

    @DatabaseField(columnName="localtime")
    public long localtime;

    @DatabaseField(columnName="datetime")
    public String datetime;

    @DatabaseField(columnName="uid")
    public String uid;//用户id


    @Override
    public String toString() {
        return "SystemMessageDBModel{" +
                "type_code='" + type_code + '\'' +
                ", data='" + data + '\'' +
                ", content='" + content + '\'' +
                ", localtime=" + localtime +
                ", datetime='" + datetime + '\'' +
                ", uid=" + uid +
                '}';
    }
}
