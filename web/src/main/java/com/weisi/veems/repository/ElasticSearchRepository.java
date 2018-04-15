//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.weisi.veems.repository;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.Bulk;
import io.searchbox.core.Delete;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Suggest;
import io.searchbox.core.SuggestResult;
import io.searchbox.core.Update;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.SuggestResult.Suggestion;
import io.searchbox.indices.ClearCache;
import io.searchbox.indices.CloseIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.Flush;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.Optimize;
import io.searchbox.indices.DeleteIndex.Builder;
import io.searchbox.params.SearchType;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ElasticSearchRepository {
    protected static final Log log = LogFactory.getLog(ElasticSearchRepository.class.getName());
    @Autowired
    private JestClient client;

    public ElasticSearchRepository() {
    }
    /**
     * 删除索引
     * @param type ：当前删除document名称
     * @return
     */
    public JestResult deleteIndex(String type) {
        DeleteIndex deleteIndex = (new Builder(type)).build();
        JestResult result = null;

        try {
            result = this.client.execute(deleteIndex);
            log.info("deleteIndex == " + result.getJsonString());
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return result;
    }
    /**
     *   清除缓存
     */
    public JestResult clearCache() {
        ClearCache closeIndex = (new io.searchbox.indices.ClearCache.Builder()).build();
        JestResult result = null;

        try {
            result = this.client.execute(closeIndex);
            log.info("clearCache == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     * 关闭索引
     * @param type ：文档表示的对象类别
     * @return
     */
    public JestResult closeIndex(String type) {
        CloseIndex closeIndex = (new io.searchbox.indices.CloseIndex.Builder(type)).build();
        JestResult result = null;

        try {
            result = this.client.execute(closeIndex);
            log.info("closeIndex == " + result.getJsonString());
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return result;
    }

    /**
     *优化索引
     */
    public JestResult optimizeIndex() {
        Optimize optimize = (new io.searchbox.indices.Optimize.Builder()).build();
        JestResult result = null;

        try {
            result = this.client.execute(optimize);
            log.info("optimizeIndex == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     *刷新索引
     */
    public JestResult flushIndex() {
        Flush flush = (new io.searchbox.indices.Flush.Builder()).build();
        JestResult result = null;

        try {
            result = this.client.execute(flush);
            log.info("flushIndex == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     *判断索引是否存在
     */
    public JestResult indicesExists() {
        IndicesExists indicesExists = (new io.searchbox.indices.IndicesExists.Builder("article")).build();
        JestResult result = null;
        try {
            result = this.client.execute(indicesExists);
            log.info("indicesExists == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     *查看节点信息
     */
    public JestResult nodesInfo() {
        NodesInfo nodesInfo = (new io.searchbox.cluster.NodesInfo.Builder()).build();
        JestResult result = null;

        try {
            result = this.client.execute(nodesInfo);
            log.info("nodesInfo == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     * 查看集群健康信息
     * @return
     */
    public JestResult health() {
        Health health = (new io.searchbox.cluster.Health.Builder()).build();
        JestResult result = null;

        try {
            result = this.client.execute(health);
            log.info("health == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     * 节点状态
     * @return
     */
    public JestResult nodesStats() {
        NodesStats nodesStats = (new io.searchbox.cluster.NodesStats.Builder()).build();
        JestResult result = null;

        try {
            result = this.client.execute(nodesStats);
            log.info("nodesStats == " + result.getJsonString());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     * 更新Document
     * @param index ：文档在哪存放
     * @param type ： 文档表示的对象类别
     * @param id ：文档唯一标识
     */
    public void updateDocument(Object o, String index, String type, String id) {
        String jsonString = JSON.toJSONString(o);
        Update update = ((io.searchbox.core.Update.Builder)((io.searchbox.core.Update.Builder)((io.searchbox.core.Update.Builder)(new io.searchbox.core.Update.Builder(jsonString)).index(index)).type(type)).id(id)).build();
        JestResult result = null;

        try {
            result = this.client.execute(update);
            log.info("updateDocument == " + result.getJsonString());
        } catch (IOException var9) {
            var9.printStackTrace();
        }

    }

    /**
     * 删除Document
     * @param index ：文档在哪存放
     * @param type ： 文档表示的对象类别
     * @param id ：文档唯一标识
     * @return
     */
    public JestResult deleteDocument(String index, String type, String id) {
        Delete delete = ((io.searchbox.core.Delete.Builder)((io.searchbox.core.Delete.Builder)(new io.searchbox.core.Delete.Builder(id)).index(index)).type(type)).build();
        JestResult result = null;

        try {
            result = this.client.execute(delete);
            log.info("deleteDocument == " + result.getJsonString());
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return result;
    }

    /**
     * 根据条件删除
     * @param index
     * @param type
     * @param params
     */
    public JestResult deleteDocumentByQuery(String index, String type, String params) {
        DeleteByQuery db = ((io.searchbox.core.DeleteByQuery.Builder)((io.searchbox.core.DeleteByQuery.Builder)(new io.searchbox.core.DeleteByQuery.Builder(params)).addIndex(index)).addType(type)).build();
        JestResult result = null;

        try {
            result = this.client.execute(db);
            log.info("deleteDocument == " + result.getJsonString());
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return result;
    }

    /**
     * 获取Document
     * @param o ：返回对象
     * @param index ：文档在哪存放
     * @param type ： 文档表示的对象类别
     * @param id ：文档唯一标识
     * @return
     */
    public <T> Object getDocument(T object, String index, String type, String id) {
        Get get = ((io.searchbox.core.Get.Builder)(new io.searchbox.core.Get.Builder(index, id)).type(type)).build();
        JestResult result = null;

        try {
            result = this.client.execute(get);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return result.getSourceAsObject(object.getClass());
    }

    /**
     * Suggestion
     * @return
     */
    public List<Suggestion> suggest() {
        String suggestionName = "my-suggestion";
        Suggest suggest = (new io.searchbox.core.Suggest.Builder("{  \"" + suggestionName + "\" : {    \"text\" : \"the amsterdma meetpu\",    \"term\" : {      \"field\" : \"body\"    }  }}")).build();
        SuggestResult suggestResult = null;
        List suggestionList = null;

        try {
            suggestResult = (SuggestResult)this.client.execute(suggest);
            log.info("suggestResult.isSucceeded() == " + suggestResult.isSucceeded());
            suggestionList = suggestResult.getSuggestions(suggestionName);
            log.info("suggestionList.size() == " + suggestionList.size());
            Iterator var5 = suggestionList.iterator();

            while(var5.hasNext()) {
                Suggestion suggestion = (Suggestion)var5.next();
                System.out.println(suggestion.text);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return suggestionList;
    }

    /**
     * 查询全部
     * @param index ：文档在哪存放
     * @return
     */
    public <T> List<Hit<T, Void>> searchAll(String index, T o) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = ((io.searchbox.core.Search.Builder)(new io.searchbox.core.Search.Builder(searchSourceBuilder.toString())).addIndex(index)).build();
        SearchResult result = null;
        List hits = null;

        try {
            result = (SearchResult)this.client.execute(search);
            log.info("本次查询共查到：" + result.getTotal() + "个关键字！");
            hits = result.getHits(o.getClass());
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return hits;
    }

    /**
     * 搜索
     * @param keyWord ：搜索关键字
     * @return
     */
    public <T> List<T> createSearch(String keyWord, String index, String type, T o, String field) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery(field, keyWord));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags(new String[]{"<em>"}).postTags(new String[]{"</em>"});
        highlightBuilder.fragmentSize(200);
        Search search = ((io.searchbox.core.Search.Builder)((io.searchbox.core.Search.Builder)(new io.searchbox.core.Search.Builder(searchSourceBuilder.toString())).addIndex(index)).addType(type)).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).build();
        SearchResult result = null;
        List resultList = null;

        try {
            result = (SearchResult)this.client.execute(search);
            log.info("本次查询共查到：" + result.getTotal() + "个结果！");
            resultList = result.getSourceAsObjectList(o.getClass());
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        return resultList;
    }

    /**
     * bulkIndex操作
     * @param index
     * @param type
     * @param o
     * @param <T>
     */
    public <T> void bulkIndex(String index, String type, T o) {
        Bulk bulk = (new io.searchbox.core.Bulk.Builder()).defaultIndex(index).defaultType(type).addAction(Arrays.asList((new io.searchbox.core.Index.Builder(o)).build())).build();

        try {
            this.client.execute(bulk);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    /**
     * 创建索引
     * @param o ：返回对象
     * @param index ：文档在哪存放
     * @param type ： 文档表示的对象类别
     * @return
     */
    public <T> JestResult createIndex(T o, String index, String type) {
        Index index1 = ((io.searchbox.core.Index.Builder)((io.searchbox.core.Index.Builder)(new io.searchbox.core.Index.Builder(o)).index(index)).type(type)).build();
        JestResult jestResult = null;

        try {
            jestResult = this.client.execute(index1);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return jestResult;
    }


    /**
     * 搜索事件流图表数据
     * @param param
     * @return
     */
    public JsonObject searchEvent(String param) {
        JsonObject returnData = (new JsonParser()).parse(param).getAsJsonObject();
        Search search = ((io.searchbox.core.Search.Builder)((io.searchbox.core.Search.Builder)(new io.searchbox.core.Search.Builder(returnData.toString())).addType("event")).addIndex("pi")).build();
        SearchResult result = null;

        try {
            result = (SearchResult)this.client.execute(search);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return result.getJsonObject();
    }

    public <T> void save(T o, String index, String type) {
        try {
            JestResult var4 = this.client.execute(((io.searchbox.core.Index.Builder)((io.searchbox.core.Index.Builder)(new io.searchbox.core.Index.Builder(o)).index(index)).type(type)).build());
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
