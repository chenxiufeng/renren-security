package io.renren.service.impl;

import io.renren.common.utils.R;
import io.renren.entity.GoodsEntity;
import io.renren.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.naming.factory.BeanFactory;
import org.apache.velocity.util.ArrayListWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author llc
 * @Description
 * @Date 2019/3/26 13:37
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Override
    public R goodsList(Map<String, Object> map) {
        String input = map.get("key").toString();
        List<GoodsEntity> list=new ArrayList();
        // 需要爬取商品信息的网站地址
        String url = "https://list.tmall.com/search_product.htm?q=" + input;
        // 动态模拟请求数据
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");

        CloseableHttpResponse response=null;
        try {
            response= httpclient.execute(httpGet);
        // 获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            // 如果状态响应码为200，则获取html实体内容或者json文件
            if (statusCode == 200) {
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                // 提取HTML得到商品信息结果
                Document doc = null;
                // doc获取整个页面的所有数据
                doc = Jsoup.parse(html);
                //输出doc可以看到所获取到的页面源代码
//      System.out.println(doc);
                // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析
                Elements ulList = doc.select(".view");
                Elements liList = ulList.select(".product");
                // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                for (Element item : liList) {
                    // 商品ID
                    String id = item.attr("data-id");
                    System.out.println("商品ID：" + id);
                    // 商品名称
                    String name = item.select(".productTitle").select("a").attr("title");
                    System.out.println("商品名称：" + name);
                    // 商品价格
                    String price = item.select(".productPrice").select("em").attr("title");
                    System.out.println("商品价格：" + price);
                    // 商品网址
                    String goodsUrl = item.select(".productTitle").select("a").attr("href");
                    System.out.println("商品网址：" + goodsUrl);
                    // 商品图片网址
                    String imgUrl = item.select(".productImg-wrap").select("a").select("img").attr("src");
                    if(StringUtils.isBlank(imgUrl)){
                        imgUrl = item.select(".productImg-wrap").select("a").select("img").attr("data-ks-lazyload");
                    }
                    System.out.println("商品图片网址：" + imgUrl);
                    System.out.println("------------------------------------");

                    //没有必设置的值
                    GoodsEntity goodsEntity = new GoodsEntity();
                    goodsEntity.setId(id);
                    goodsEntity.setName(name);
                    goodsEntity.setPrice(new BigDecimal(price));
                    goodsEntity.setUrl(imgUrl);
                    list.add(goodsEntity);
                }
            }
        }catch (Exception e){

        }
        finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return R.ok().put("list",list);
    }
}
