package com.rms.marketing.common.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.AliasBuilder;
import org.springframework.data.elasticsearch.core.query.AliasQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreateIndexJob {

    private static final Logger logger = LoggerFactory.getLogger(CreateIndexJob.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ElasticSearchIndexConfig elasticSearchIndexConfig;

    @Scheduled(cron="0 0 0 1 * ?")
    public void autoCreateIndexWithAlias() {

        try {
            String indexAdInfoNow = elasticSearchIndexConfig.getAdInfoIndex();
            if (!elasticsearchTemplate.indexExists(indexAdInfoNow)) {
                elasticsearchTemplate.createIndex(indexAdInfoNow);
            }
            String indexAdPlayRecordNow = elasticSearchIndexConfig.getAdPlayRecordIndex();
            if (!elasticsearchTemplate.indexExists(indexAdPlayRecordNow)) {
                elasticsearchTemplate.createIndex(indexAdPlayRecordNow);
            }

            AliasQuery aliasAdInfoQuery = new AliasBuilder()
                    .withIndexName(indexAdInfoNow)
                    .withAliasName(ElasticSearchIndexConfig.adInfoIndexName).build();
            elasticsearchTemplate.addAlias(aliasAdInfoQuery);
            AliasQuery aliasAdPlayRecordQuery = new AliasBuilder()
                    .withIndexName(indexAdPlayRecordNow)
                    .withAliasName(ElasticSearchIndexConfig.adPlayRecordIndexName).build();
            elasticsearchTemplate.addAlias(aliasAdPlayRecordQuery);

            // 创建下一次的
            String indexAdInfoNext = elasticSearchIndexConfig.nextAdInfoIndex(1);
            if (!elasticsearchTemplate.indexExists(indexAdInfoNext)) {
                elasticsearchTemplate.createIndex(indexAdInfoNext);
                AliasQuery nettAliasAdInfoQuery = new AliasBuilder()
                        .withIndexName(indexAdInfoNext)
                        .withAliasName(ElasticSearchIndexConfig.adInfoIndexName).build();

                elasticsearchTemplate.addAlias(nettAliasAdInfoQuery);
            }
            String indexAdPlayRecordNext = elasticSearchIndexConfig.nextAdPlayRecordIndex(1);
            if (!elasticsearchTemplate.indexExists(indexAdPlayRecordNext)) {
                elasticsearchTemplate.createIndex(indexAdPlayRecordNext);
                AliasQuery nettAliasAdPlayRecordQuery = new AliasBuilder()
                        .withIndexName(indexAdPlayRecordNext)
                        .withAliasName(ElasticSearchIndexConfig.adPlayRecordIndexName).build();

                elasticsearchTemplate.addAlias(nettAliasAdPlayRecordQuery);
            }
        } catch (Exception e) {
            logger.error("创建es索引出现异常！", e);
        }
    }

    @PostConstruct
    public void init() {
        autoCreateIndexWithAlias();
    }
}
