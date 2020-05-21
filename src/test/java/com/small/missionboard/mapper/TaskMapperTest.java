package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.missionboard.bean.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class TaskMapperTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    TaskMapper taskMapper;

    @Transactional
    @Test
    void testTaskMapper() {
        Task task = new Task();
        task.setReceiverNotes("test");
        taskMapper.insert(task);
    }


    @Test
    void sortByTimePage() {
        List<Task> reverse = taskMapper.sortByTimePage(new Page<>(1, 9), null, "reverse");
        reverse.forEach(System.out::println);
    }


}
    