package com.yueqian.faq.service;

import java.sql.SQLException;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.dao.AdminDao;
import com.yueqian.faq.dao.UserDao;
import com.yueqian.faq.domain.UserInfo;

public class UserService {

	private static UserService instance;

	public synchronized static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	private UserService() {

	}

	/**
	 * �����˺Ų�ѯ�û���Ϣ
	 * 
	 * @param account
	 *            �˺�
	 * @param admin
	 *            �Ƿ�Ϊ����Ա
	 * @return �û���Ϣ
	 */
	public UserInfo findInfoByAccount(String account, boolean admin) {
		UserInfo info = null;
		try {
			if (admin) {
				info = AdminDao.getInstance().findInfoByAccount(account);
			} else {
				info = UserDao.getInstance().findInfoByAccount(account);
			}
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * ������ע����û���Ϣ
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean saveInfo(UserInfo userInfo) {
		int count = 0;
		try {
			if (userInfo.isAdmin()) {
				// TODO �����¹���Ա
			} else {
				count = UserDao.getInstance().saveInfo(userInfo);
			}
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}

		return count > 0;
	}
}
