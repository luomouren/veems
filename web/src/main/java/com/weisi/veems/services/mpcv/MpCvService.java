package com.weisi.veems.services.mpcv;
import com.github.pagehelper.PageInfo;
import com.weisi.veems.models.elasticsearch.Mpcv;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @description:查询历史抄表记录
 * @author:@luomouren.
 * @Date:2017-12-03 15:17
 */
@Repository
public interface MpCvService{
    /**
     * 保存历史抄表示数
     * @param mpcv 历史抄表示数
     */
    void save(Mpcv mpcv) ;

    /**
     * 更新抄表示数
     */
    void update(Mpcv mpcv);

    /**
     * 查询表计示数
     * @param cvId 表计cvId
     */
    PageInfo<Mpcv> searchMpCv(String cvId);

    /**
     * 根据CVID查询表计示数信息
     */
    Mpcv selectByPrimaryKey(String id);

    /**
     * 根据ID删除对象
     * @param id
     */
    void deleteByPrimaryKey(String id);
}
