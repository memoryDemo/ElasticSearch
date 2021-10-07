package cn.wiki.hotel.service.impl;

import cn.wiki.hotel.mapper.HotelMapper;
import cn.wiki.hotel.pojo.Hotel;
import cn.wiki.hotel.service.IHotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Memory
 */
@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
}
