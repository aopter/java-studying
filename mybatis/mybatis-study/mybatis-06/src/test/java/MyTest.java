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
        Teacher teacher = mapper.getTeacherById(1);

        System.out.println(teacher);
        sqlSession.close();
    }

    @Test
    public void getTeacherInfo(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudentInfo2();

        for(Student student:students){
            System.out.println(student);

        }

        sqlSession.close();
    }
}
