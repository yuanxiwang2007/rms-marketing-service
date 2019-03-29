package com.rms.marketing.common.repository;

import com.rms.marketing.common.entity.AdPlayRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdPlayRecordRepository extends ElasticsearchRepository<AdPlayRecord, String> {
}
