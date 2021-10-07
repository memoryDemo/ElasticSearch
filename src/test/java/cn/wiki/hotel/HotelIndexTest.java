package cn.wiki.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static cn.wiki.constant.HotelConstants.HOTEL_MAPPING_TEMPLATE;

public class HotelIndexTest {
  private RestHighLevelClient client;

  @Test
  void testInit() {
    System.out.println(client);
  }

  /** 创建索引库 */
  @Test
  void createHotelIndex() {
    // 1.创建请求
    CreateIndexRequest request = new CreateIndexRequest("test");
    // 2.准备请求参数，封装DSL语句
    request.source(HOTEL_MAPPING_TEMPLATE, XContentType.JSON);
    // 3.发起请求
    CreateIndexResponse response = null;
    try {
      response = client.indices().create(request, RequestOptions.DEFAULT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 4.处理响应结果
    System.out.println("打印响应结果" + response);
  }

  /**
   * 删除索引库
   */
  @Test
  void TestDeleteHotelIndex() throws IOException {
    // 1.创建请求
    DeleteIndexRequest request = new DeleteIndexRequest("test");
    // 2.发起请求
    AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
    // 3.处理响应结果
    System.out.println(response);
  }

  @Test
  void TestExistsHotelIndex() throws IOException {
    // 1.创建请求
    GetIndexRequest request = new GetIndexRequest("test");
    // 2.发起请求
    boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
    // 3.处理响应结果
    System.err.println(exists ? "索引库已经存在！" : "索引库尚未创建！");
  }

  @BeforeEach
  void setUp() {
    this.client =
        new RestHighLevelClient(RestClient.builder(HttpHost.create("http://127.0.0.1:9200")));
  }

  @AfterEach
  void tearDown() throws IOException {
    this.client.close();
  }
}
