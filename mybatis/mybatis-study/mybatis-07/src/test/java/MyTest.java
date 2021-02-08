import com.kai.dao.StudentMapper;
import com.kai.dao.TeacherMapper;
import com.kai.pojo.Student;
import com.kai.pojo.Teacher;
import com.kai.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2021/2/1 18:56
 * @projectName mybatis-study
 * @className MyTest.java
 * @description TODO
 */
public class MyTest {

    @Test
    public void testGetTeacher(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

            System.out.println(mapper.getTeacherById(1));



        sqlSession.close();
    }


}
