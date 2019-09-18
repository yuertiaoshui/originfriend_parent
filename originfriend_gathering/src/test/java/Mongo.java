import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxy
 * @date 2019/9/13 - 12:14
 */
public class Mongo {
    public static void main(String[] args) {
        //连接mongo服务器
        MongoClient client = new MongoClient("localhost");
        //得到要操作的数据库
        MongoDatabase database = client.getDatabase("test");
        //得到要操作的集合
        MongoCollection<Document> collection = database.getCollection("student");
        //
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("content","微冷的雨训练营新");
        map.put("userid","1040");
        map.put("visits",100);
        map.put("_id","101");
        map.put("comment",1);
        map.put("thumbup",1);
        Document document=new Document(map);
        collection.insertOne(document);
        //断开连接
        client.close();
    }

    static void select(){
        //链接mongo服务器
        MongoClient client=new MongoClient("localhost");
        //得到要操作的数据库
        MongoDatabase database=client.getDatabase("spitdb");
        //得到要操作的集合
        MongoCollection<Document> spit=database.getCollection("spit");
        //封装查询条件
        // BasicDBObject bson=new BasicDBObject("userid","1013");
        //查询访问量大于10000的
        BasicDBObject bson=new BasicDBObject("visits",new BasicDBObject("$gt",1000));
        //得到集合种所有的文档
        FindIterable<Document> documents = spit.find(bson);
        //遍历数据
        for (Document document:documents){
            System.out.println("内容："+document.getString("content"));
            System.out.println("用户id："+document.getString("userid"));
            System.out.println("访问量:"+document.getInteger("visits"));
        }
        //断开连接
        client.close();
    }

    static void base(){
        //链接mongo服务器
        MongoClient client=new MongoClient("localhost");
        //得到要操作的数据库
        MongoDatabase database=client.getDatabase("spitdb");
        //得到要操作的集合
        MongoCollection<Document> spit=database.getCollection("spit");
        //得到集合种所有的文档
        FindIterable<Document> documents = spit.find();
        //遍历数据
        for (Document document:documents){
            System.out.println("内容："+document.getString("content"));
            System.out.println("用户id："+document.getString("userid"));
            System.out.println("访问量:"+document.getInteger("visits"));
        }
        //断开连接
        client.close();
    }

}
