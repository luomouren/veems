package com.weisi.veems.services.mpcv.impl;
import com.github.pagehelper.PageInfo;
import com.weisi.veems.models.elasticsearch.Mpcv;
import com.weisi.veems.repository.ElasticSearchRepository;
import com.weisi.veems.services.mpcv.MpCvService;
import io.searchbox.client.JestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @description:保存历史抄表示数
 * @author:@luomouren.
 * @Date:2017-12-03 16:45
 */
@Service
public class MpCvServiceImpl extends ElasticSearchRepository implements MpCvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MpCvServiceImpl.class);

    @Override
    public void save(Mpcv mpcv)  {
        super.save(mpcv,Mpcv.INDEX_NAME,Mpcv.TYPE);
    }

    @Override
    public void update(Mpcv mpcv) {
        super.updateDocument(mpcv,Mpcv.INDEX_NAME,Mpcv.TYPE,mpcv.getId());
    }

    /**
     * 分页查询测点的cv信息
     * @param searchContent
     * @return
     */
    @Override
    public PageInfo<Mpcv> searchMpCv(String searchContent) {
            List<Mpcv>  cvList = super.createSearch(searchContent,Mpcv.INDEX_NAME,Mpcv.TYPE,new Mpcv(),"cvId");
            return new PageInfo<Mpcv>(cvList);
    }

    @Override
    public Mpcv selectByPrimaryKey(String id) {
        Mpcv mpcv = (Mpcv) super.getDocument(new Mpcv(), Mpcv.INDEX_NAME,Mpcv.TYPE,id);
        return mpcv;
    }

    @Override
    public void deleteByPrimaryKey(String id) {
      deleteDocument(Mpcv.INDEX_NAME,Mpcv.TYPE,id);
    }
}
