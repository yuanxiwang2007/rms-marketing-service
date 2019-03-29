package com.rms.marketing.common.repository;

import com.rms.marketing.common.entity.AdInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdInfoRepository extends ElasticsearchRepository<AdInfo, String> {
}
