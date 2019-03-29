package com.rms.marketing.mapper;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.rms.marketing.model.bo.UserInfoBo;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author wk
 * @硬件操作日志表mapper
 */
@Component
public class MongodbDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveImageRecognitFailed(UserInfoBo imageRecognitFailedBo) {
        mongoTemplate.save(imageRecognitFailedBo);
    }

    /**
     * 查询所有需要发送短信的基因检测信息
     */
    public List<UserInfoBo> getGeneSendMsg() {
        Query query = new Query();
        Criteria criteria = Criteria.where("isSend").is(0);
        query.addCriteria(criteria);
        List<UserInfoBo> list = mongoTemplate.find(query, UserInfoBo.class);
        return list;
    }

    /**
     * 更改基因检测短信标志
     */
    public void updateGeneMsgMark(String barcode) {
        Query query = new Query(Criteria.where("barcode").is(barcode));
        Update update = new Update();
        update.set("isSend", 1);
        update.set("sendTime", new Date());
        mongoTemplate.updateFirst(query, update, UserInfoBo.class);
    }

    /**
     * 获取健康终端到心选跳转量
     *
     * @param beginDate
     * @param endDate
     * @param isLogin
     * @return
     */
    public Map<String, Object> queryUrineToXinXuanRecordInfo(Date beginDate, Date endDate, Boolean isLogin) {
        Map<String, Object> mapReport = new HashMap<>();
        List<DBObject> aggregateQuery = new ArrayList<>();
        BasicDBObject groupHeapNum = new BasicDBObject();
        BasicDBObjectBuilder yearBuilder = new BasicDBObjectBuilder();
        BasicDBObjectBuilder monthBuilder = new BasicDBObjectBuilder();
        BasicDBObjectBuilder dayBuilder = new BasicDBObjectBuilder();
        BasicDBObjectBuilder matchBuilder = new BasicDBObjectBuilder();
        if (beginDate != null && endDate != null) {
            BasicDBObjectBuilder publishDateBuilder = new BasicDBObjectBuilder();
            publishDateBuilder.add("$gte", beginDate);
            publishDateBuilder.add("$lt", endDate);
            matchBuilder.add("time", publishDateBuilder.get());
        }

        BasicDBList dateList = new BasicDBList();
        dateList.add("$time");
        dateList.add(28800000);
        DBObject time = new BasicDBObject("$add", dateList);

        yearBuilder.add("$year", time);
        monthBuilder.add("$month", time);
        dayBuilder.add("$dayOfMonth", time);
        groupHeapNum.append("year", yearBuilder.get());
        groupHeapNum.append("month", monthBuilder.get());
        groupHeapNum.append("day", dayBuilder.get());
        groupHeapNum.append("outUserID", "$userInfoVo.outUserID");
        groupHeapNum.append("userID", "$userInfoVo.userID");
        groupHeapNum.append("phone", "$userInfoVo.phone");
        if (isLogin) {
            BasicDBObjectBuilder publishDateBuilder = new BasicDBObjectBuilder();
            publishDateBuilder.add("$ne", null);
            matchBuilder.add("userInfoVo.outUserID", publishDateBuilder.get());
        } else {
            matchBuilder.add("userInfoVo.outUserID", null);
            groupHeapNum.append("_id", "$_id");
        }

        BasicDBObjectBuilder groupBuilder = new BasicDBObjectBuilder();
        groupBuilder.add("_id", groupHeapNum);
        groupBuilder.add("city", new BasicDBObject("$max", "$city"));
        groupBuilder.add("count", new BasicDBObject("$sum", 1));


        aggregateQuery.add(new BasicDBObject("$match", matchBuilder.get()));
        aggregateQuery.add(new BasicDBObject("$group", groupBuilder.get()));
        aggregateQuery.add(new BasicDBObject("$sort", new BasicDBObject("count", 1)));//排序
        BasicDBObjectBuilder countGroupBuilder = new BasicDBObjectBuilder();
        countGroupBuilder.add("_id", "doctorwork");
        countGroupBuilder.add("sum", new BasicDBObject("$sum", 1));
        aggregateQuery.add(new BasicDBObject("$group", countGroupBuilder.get()));

        AggregationOptions aggregationOptions = AggregationOptions.builder().allowDiskUse(true).build();

        DBCollection collection = (DBCollection) mongoTemplate.getDb().getCollection("urineToXinXuanBo");
        Cursor cursor = collection.aggregate(aggregateQuery, aggregationOptions);
        while (cursor.hasNext()) {//查出分组条数
            DBObject next = cursor.next();
            Object _id = next.get("_id");
            mapReport.put("_id", _id);
            Object count = next.get("sum");
            mapReport.put("sum", count);
            System.out.println(_id.toString() + ":" + count.toString());
        }
        return mapReport;
    }

    /**
     * @desc
     */
    public Map<String, Object> queryMachineScanRecordInfo(Date beginDate, Date endDate) {

        Map<String, Object> mapReport = new HashMap<>();
        List<DBObject> aggregateQuery = new ArrayList<>();
        BasicDBObject groupHeapNum = new BasicDBObject();
        BasicDBObjectBuilder yearBuilder = new BasicDBObjectBuilder();
        BasicDBObjectBuilder monthBuilder = new BasicDBObjectBuilder();
        BasicDBObjectBuilder dayBuilder = new BasicDBObjectBuilder();

        BasicDBList dateList = new BasicDBList();
        dateList.add("$addTime");
        dateList.add(28800000);
        DBObject time = new BasicDBObject("$add", dateList);

        yearBuilder.add("$year", time);
        monthBuilder.add("$month", time);
        dayBuilder.add("$dayOfMonth", time);
        groupHeapNum.append("year", yearBuilder.get());
        groupHeapNum.append("month", monthBuilder.get());
        groupHeapNum.append("day", dayBuilder.get());
        groupHeapNum.append("buildingName", "$machineInfoVo.buildingName");
        groupHeapNum.append("machineID", "$machineID");
        groupHeapNum.append("openID", "$openID");
        groupHeapNum.append("phone", "$phone");
        BasicDBObjectBuilder groupBuilder = new BasicDBObjectBuilder();
        groupBuilder.add("_id", groupHeapNum);
        groupBuilder.add("buildingName", new BasicDBObject("$max", "$machineInfoVo.buildingName"));
        groupBuilder.add("count", new BasicDBObject("$sum", 1));
        BasicDBObjectBuilder matchBuilder = new BasicDBObjectBuilder();
        //matchBuilder.add("count", new BasicDBObject("$gt", 1));
        if (beginDate != null && endDate != null) {
            BasicDBObjectBuilder publishDateBuilder = new BasicDBObjectBuilder();
            publishDateBuilder.add("$gte", beginDate);
            publishDateBuilder.add("$lt", endDate);
            matchBuilder.add("addTime", publishDateBuilder.get());
        }
        aggregateQuery.add(new BasicDBObject("$match", matchBuilder.get()));
        aggregateQuery.add(new BasicDBObject("$group", groupBuilder.get()));
        aggregateQuery.add(new BasicDBObject("$sort", new BasicDBObject("count", 1)));//排序
        BasicDBObjectBuilder countGroupBuilder = new BasicDBObjectBuilder();
        countGroupBuilder.add("_id", "doctorwork");
        countGroupBuilder.add("sum", new BasicDBObject("$sum", 1));
        aggregateQuery.add(new BasicDBObject("$group", countGroupBuilder.get()));

        AggregationOptions aggregationOptions = AggregationOptions.builder().allowDiskUse(true).build();

        DBCollection collection = (DBCollection) mongoTemplate.getDb().getCollection("MachineScanRecordInfo");
        Cursor cursor = collection.aggregate(aggregateQuery, aggregationOptions);
        while (cursor.hasNext()) {//查出分组条数
            DBObject next = cursor.next();
            Object _id = next.get("_id");
            mapReport.put("_id", _id);
            Object count = next.get("sum");
            mapReport.put("sum", count);
            System.out.println(_id.toString() + ":" + count.toString());
        }
        return mapReport;
    }


}
