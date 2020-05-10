package com.small.missionboard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.missionboard.bean.entity.Task;

import java.util.List;

public interface TaskMapper extends BaseMapper<Task> {

    /**
     * reverse传入 null 表示最新的创建时间排在最前面，否则表示逆序
     */
    List<Task> sortByTimePage(Page<Task> page, String reverse);

    List<Task> sortBySenderCredit(Page<Task> page, String reverse);

    List<Task> notAcceptedList(String senderId);

    List<Task> ongoingList(String userId);

    List<Task> finishedList(String userId);

    List<Task> timeoutNotSubmittedList(String userId);

}
