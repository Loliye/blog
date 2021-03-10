package com.mikufans.blog.infrastructure.repository.meta;

import com.mikufans.blog.domain.aggregate.meta.MetaCond;
import com.mikufans.blog.infrastructure.repository.meta.MetaPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MetaRespository {
    /**
     * 添加项目
     * @param meta
     * @return
     */
    int addMeta(MetaPo meta);

    /**
     * 删除项目
     * @param mid
     * @return
     */
    int deleteMetaById(@Param("mid") Integer mid);

    /**
     * 更新项目
     * @param meta
     * @return
     */
    int updateMeta(MetaPo meta);

    /**
     * 根据编号获取项目
     * @param mid
     * @return
     */
    MetaPo getMetaById(@Param("mid") Integer mid);


    /**
     * 根据条件查询
     * @param metaCond
     * @return
     */
    List<MetaPo> getMetasByCond(MetaCond metaCond);

    /**
     * 根据类型获取meta数量
     * @param type
     * @return
     */
    Long getMetasCountByType(@Param("type") String type);

    /**
     * 根据sql查询
     * @param paraMap
     * @return
     */
    List<MetaPo> selectFromSql(Map<String, Object> paraMap);
}
