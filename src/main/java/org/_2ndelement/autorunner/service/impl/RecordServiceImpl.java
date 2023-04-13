package org._2ndelement.autorunner.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org._2ndelement.autorunner.entity.Record;
import org._2ndelement.autorunner.service.RecordService;
import org._2ndelement.autorunner.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
* @author 27813
* @description 针对表【record(记录表)】的数据库操作Service实现
* @createDate 2023-04-11 00:42:47
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

}




