package scu.cj.community.service;

import scu.cj.community.mapper.AdMapper;
import scu.cj.community.model.Ad;
import scu.cj.community.model.AdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.cj.community.model.Advertisement;

import java.util.List;

@Service
public class AdService {
    @Autowired
    private AdMapper adMapper;

    public List<Ad> list(String pos) {
        AdExample navExample = new AdExample();
        navExample.createCriteria()
                .andStatusEqualTo(1)
                .andPosEqualTo(pos)
                .andGmtStartLessThan(System.currentTimeMillis())
                .andGmtEndGreaterThan(System.currentTimeMillis());
        return adMapper.selectByExample(navExample);
    }


    public List<Advertisement> getAll(){
        return adMapper.getAll();

    }
}
