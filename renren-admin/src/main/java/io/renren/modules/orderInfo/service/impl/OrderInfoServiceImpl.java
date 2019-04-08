package io.renren.modules.orderInfo.service.impl;

import io.renren.common.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.orderInfo.dao.OrderInfoDao;
import io.renren.modules.orderInfo.entity.OrderInfoEntity;
import io.renren.modules.orderInfo.service.OrderInfoService;


@Service("orderInfoService")
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoEntity> implements OrderInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderInfoEntity> page = this.selectPage(
                new Query<OrderInfoEntity>(params).getPage(),
                new EntityWrapper<OrderInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R queryPageOrderList(Map<String, Object> params) {

        Page<OrderInfoEntity> page = new Page<>(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        Integer current = Integer.parseInt(params.get("page").toString());

        Integer limit = Integer.parseInt(params.get("limit").toString());
        if (current > 0) {
            current--;
        }
        params.put("current", current * limit);
        List<OrderInfoEntity> list = this.baseMapper.queryPageOrderList(params);
        page.setRecords(list);
        Integer total = this.baseMapper.selectOrderTotal(params); //单独查询一遍总数
        page.setTotal(total);
        PageUtils pageUtils = new PageUtils(page);
        return R.ok().put("page", pageUtils);
    }

}
