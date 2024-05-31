package com.example.secHandShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secHandShop.entity.RecordState;
import com.example.secHandShop.repository.RecordDao;
import com.example.secHandShop.repository.RecordItemDao;
import com.example.secHandShop.repository.RecordStateDao;

@SpringBootTest
public class RecordTest {

	@Autowired
	private RecordDao recordDao;

	@Autowired
	private RecordItemDao itemDao;

	@Autowired
	private RecordStateDao stateDao;

	@Test
	public void findByRecordIdTest() {
		RecordState res = stateDao.findByRecordId(1);
		if (res == null) {
			System.out.println("not found!");
			return;
		}
		System.out.println("res:" + res.getStateTime());
	}

}
