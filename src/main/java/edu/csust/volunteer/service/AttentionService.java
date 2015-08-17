package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Attention;
import edu.csust.volunteer.vo.PictureVO;

public interface AttentionService {
	public List<PictureVO> getMyFriend(String userno, int maxSize);

	public int getAttentionMeNum(String userno);
	
	public List<Attention> getRelationUsers(String userno, int param,
			int current, int pagesize);

	public List<PictureVO> getMyAllFriend(String userno);

	public List<PictureVO> getMyAllAttentionMe(String userno);

	public boolean attentionUser(String userno, String currentUser);

	public boolean isAttention(String userno, String currentUser);

	public boolean cancelAttention(String userno, String currentUser);

}
