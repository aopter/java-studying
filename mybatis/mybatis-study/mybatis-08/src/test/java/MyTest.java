import com.kai.dao.BlogMapper;
import com.kai.pojo.Blog;
import com.kai.util.IDutils;
import com.kai.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/2 16:03
 * @projectName mybatis-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {


    @Test
    public  void addBlog(){

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setAuthor("小7");
        blog.setCreate_time(new Date());
        blog.setId(IDutils.getID());
        blog.setViews(9939);
        blog.setTitle("77如此简单");

        mapper.addBlog(blog);

        sqlSession.close();
    }


    @Test
    public void testGetBlogs(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("title","java如此简单");
        map.put("author","小刘");
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        List<Blog> blogs = mapper.queryBlogs(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        sqlSession.close();
    }


}
