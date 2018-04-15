package com.weisi.veems.models.elasticsearch;
import org.springframework.data.elasticsearch.annotations.Document;


import java.io.Serializable;
import java.util.Date;

/**
 * @author luomouren
 *         测点cv值-与数据库mongodb表mpcv对应
 */
@Document(indexName="mpcv",type="mpcv")
public class Mpcv implements Serializable {

    public static String INDEX_NAME ="mpcv";

    public static String TYPE ="mpcv";

    private static final long serialVersionUID = 1L;
    private String id;
    private String cvId;
    private String mpId;
    private Double value;
    // 示数对应时间
    private Date dataTime;
    // 数据采集时间
    private Date samTime;

    public Mpcv() {
        super();
    }

    public Mpcv(String _id, String cvId, String mpId, Double value, Date dataTime, Date samTime) {
        super();
        this.id = id;
        this.cvId = cvId;
        this.mpId = mpId;
        this.value = value;
        this.dataTime = dataTime;
        this.samTime = samTime;
    }

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Date getSamTime() {
        return samTime;
    }

    public void setSamTime(Date samTime) {
        this.samTime = samTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
