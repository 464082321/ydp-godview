package com.ydp.godview.mapper.setting;

import java.util.List;

import org.junit.Test;

import com.ydp.godview.dao.SettingLogDao;
import com.ydp.godview.model.SettingLogDto;
import com.ydp.godview.test.base.AbstractTestMapper;

public class LogSettingTest extends AbstractTestMapper {
	
	@Test
	public void addSetting() {
		try {
			SettingLogDao settingLogDao = sqlSession.getMapper(SettingLogDao.class);
			
			SettingLogDto settingLogDto = new SettingLogDto();
			settingLogDto.setMethodName("com.john.CInterface.cmethod()");
			settingLogDto.setIsOpen("0");
			settingLogDao.addSetting(settingLogDto);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addSettingForTest() {
		try {
			SettingLogDao settingLogDao = sqlSession.getMapper(SettingLogDao.class);
			
			SettingLogDto settingLogDto = null;
			settingLogDto = new SettingLogDto();
			settingLogDto.setMethodName("com.john.AInterface.amethod()");
			settingLogDto.setIsOpen("1");
			settingLogDao.addSetting(settingLogDto);
			
			settingLogDto = new SettingLogDto();
			settingLogDto.setMethodName("com.john.AInterface.a1method()");
			settingLogDto.setIsOpen("1");
			settingLogDao.addSetting(settingLogDto);
			
			settingLogDto = new SettingLogDto();
			settingLogDto.setMethodName("com.john.BInterface.bmethod()");
			settingLogDto.setIsOpen("1");
			settingLogDao.addSetting(settingLogDto);
			
			settingLogDto = new SettingLogDto();
			settingLogDto.setMethodName("com.john.CInterface.cmethod()");
			settingLogDto.setIsOpen("1");
			settingLogDao.addSetting(settingLogDto);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void querySetting() {
		try {
			SettingLogDao settingLogDao = sqlSession.getMapper(SettingLogDao.class);
//			List<SettingLogDto> list = settingLogDao.querySettings("1");
			List<SettingLogDto> list = settingLogDao.querySettings(null);
			for(SettingLogDto settingLogDto : list) {
				System.out.println(settingLogDto.getMethodName());
				System.out.println(settingLogDto.getIsOpen());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delSetting() {
		try {
			SettingLogDao settingLogDao = sqlSession.getMapper(SettingLogDao.class);
			settingLogDao.delSetting("com.john.CInterface.cmethod()");
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void udSetting() {
		try {
			SettingLogDao settingLogDao = sqlSession.getMapper(SettingLogDao.class);
			SettingLogDto settingLogDto = new SettingLogDto();
			settingLogDto.setMethodName("com.john.BInterface.bmethod()");
			settingLogDto.setIsOpen("1");
			settingLogDao.udSetting(settingLogDto);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
