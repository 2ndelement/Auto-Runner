package org._2ndelement.autorunner.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org._2ndelement.autorunner.entity.Admin;
import org._2ndelement.autorunner.service.AdminService;
import org._2ndelement.autorunner.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 27813
* @description 针对表【admin(管理员表)】的数据库操作Service实现
* @createDate 2023-04-11 00:42:47
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




