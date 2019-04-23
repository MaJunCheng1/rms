package com.ma.rms.dao;

import com.ma.rms.domain.locate;

public interface locateDao {
	//通过locid获取地址
	locate findLocate(int id);
}
