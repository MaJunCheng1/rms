package com.ma.rms.biz;

import com.ma.rms.domain.locate;

public interface locateBiz {
	//根据locid查找地址
	locate findLocate(int id);
}
