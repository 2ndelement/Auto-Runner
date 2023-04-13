package org._2ndelement.autorunner.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org._2ndelement.autorunner.entity.ProxyUser;
import org._2ndelement.autorunner.service.ProxyUserService;
import org._2ndelement.autorunner.mapper.ProxyUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 27813
* @description 针对表【proxy_user(代跑用户表)】的数据库操作Service实现
* @createDate 2023-04-11 00:42:47
*/
@Service
public class ProxyUserServiceImpl extends ServiceImpl<ProxyUserMapper, ProxyUser>
    implements ProxyUserService{

}




