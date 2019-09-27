package com.yueqian.faq.service;

import java.sql.SQLException;

import com.yueqian.faq.common.DBUtils;
import com.yueqian.faq.common.ProblemStatus;
import com.yueqian.faq.dao.ProblemDao;
import com.yueqian.faq.dao.ReplyDao;
import com.yueqian.faq.dao.UserDao;
import com.yueqian.faq.domain.ReplyInfo;

public class ReplyService {
	private static ReplyService instance;

	public synchronized static ReplyService getInstance() {
		if (instance == null) {
			instance = new ReplyService();
		}
		return instance;
	}

	private ReplyService() {

	}

	/**
	 * ����ظ���Ϣ
	 * 
	 * @param info
	 * @return
	 */
	public boolean saveReplyInfo(ReplyInfo info) {
		int count = 0;
		try {
			count = ReplyDao.getInstance().saveReplyInfo(info);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return count > 0;
	}

	public ReplyInfo findInfoByFileId(String fileId) {
		ReplyInfo info = null;
		try {
			info = ReplyDao.getInstance().findInfoByFileId(fileId);
			DBUtils.commit();
		} catch (SQLException e) {
			DBUtils.rollback();
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * ���ɻظ�Ϊ��ѻظ�
	 * 
	 * @param replyId
	 * @return ������
	 */
	public Integer acceptReply(Integer replyId) {

		ReplyInfo replyInfo = null;
		try {
			replyInfo = ReplyDao.getInstance().findInfoByReplytId(replyId);
			// ��replyId��Ӧ�Ļظ���Ϊ��ѻظ�
			int count = ReplyDao.getInstance().acceptReply(replyId);
			// ���ظ��߼�����������ͷ�������Ҫ֪���ظ��ߵ�id����������ͷ�
			if (count > 0) {
				count = UserDao.getInstance().addScore(replyId);
			}
			// �޸������״̬Ϊ���ѽ����
			ProblemDao.getInstance().changeStatus(replyInfo.getProblemId(), ProblemStatus.RESOLVED);
			DBUtils.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback();
		}
		return replyInfo.getProblemId();
	}
}
