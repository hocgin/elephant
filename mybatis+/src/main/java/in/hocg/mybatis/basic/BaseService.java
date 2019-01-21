package in.hocg.mybatis.basic;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * ExampleMapper, Example
 *
 * @param <M> Mapper 接口
 * @param <T> 对象实体
 * @author hocgin
 */
public abstract class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
