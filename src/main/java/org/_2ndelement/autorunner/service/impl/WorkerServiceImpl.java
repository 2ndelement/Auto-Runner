package org._2ndelement.autorunner.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org._2ndelement.autorunner.entity.Worker;
import org._2ndelement.autorunner.service.WorkerService;
import org._2ndelement.autorunner.mapper.WorkerMapper;
import org.springframework.stereotype.Service;

/**
* @author 27813
* @description 针对表【worker(工作机表)】的数据库操作Service实现
* @createDate 2023-04-13 12:03:34
*/
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker>
    implements WorkerService{

}




