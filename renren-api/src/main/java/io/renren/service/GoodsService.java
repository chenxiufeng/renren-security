package io.renren.service;

import io.renren.common.utils.R;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface GoodsService {
    R goodsList(Map<String,Object> map);
}
