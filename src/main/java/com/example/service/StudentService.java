package com.example.service;

import com.example.entity.Student;
import java.util.List;

/**
 * (Student)表服务接口
 *
 * @author wyl
 * @since 2021-10-10 11:26:21
 */
public interface StudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param studentId 主键
     * @return 实例对象
     */
    Student findById(Integer studentId);

    /**
     * 查询所有
     *
     * @return 对象数组
     */
    List<Student> findAll();

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    int insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param studentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer studentId);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteBatch(int[] ids);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param studentNum
     * @param studentName
     * @return
     */
    List<Student> findList(int currentPage, int pageSize, String studentNum, String studentName);

    /**
     * 根据学号查询
     * @param studentNum
     * @return
     */
    Student findByStudentNum(Integer studentNum);

    /**
     * 修改时唯一性检查
     * @param studentNum
     * @param studentId
     * @return
     */
    Student findByStudentNumAndId(Integer studentNum, Integer studentId);
}
