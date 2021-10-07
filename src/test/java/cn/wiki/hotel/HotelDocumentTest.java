package cn.wiki.hotel;

import cn.wiki.hotel.pojo.Hotel;
import cn.wiki.hotel.pojo.HotelDoc;
import cn.wiki.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class HotelDocumentTest {

  @Resource IHotelService iHotelService;

  private RestHighLevelClient client;

  @Test
  void testInit() {
    System.out.println(client);
  }

  /**
   * 新增文档
   */
  @Test
  void testAddDocument() throws IOException {
    // 根据ID查询酒店数据
    Hotel hotel = iHotelService.getById(396471L);

    // 1.准备请求
    IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
    // 2.准备文档
    request.source(JSON.toJSONString(new HotelDoc(hotel)), XContentType.JSON);
    // 3.发起请求
    IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    // 4.处理响应结果
    System.err.println("响应结果：" + response);
  }

  /**
   * 删除文档
   */
  @Test
  void testDeleteDocument() throws IOException {
    // 根据ID查询酒店数据
    Hotel hotel = iHotelService.getById(396471L);

    // 1.准备请求
    DeleteRequest request = new DeleteRequest("hotel").id(hotel.getId().toString());
    // 2.发起请求
    DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
    // 3.处理响应结果
    System.err.println(
        "删除操作结果："
            + response.getResult()
            + "数据成功执行："
            + response.getShardInfo().getSuccessful()
            + " 条");
  }

  @BeforeEach
  void setUp() {
    this.client =
        new RestHighLevelClient(RestClient.builder(
                //集群模式多个HttpHost.create("yourIP") 逗号隔开
                HttpHost.create("http://127.0.0.1:9200"))
        );
  }

  @AfterEach
  void tearDown() throws IOException {
    this.client.close();
  }
}
