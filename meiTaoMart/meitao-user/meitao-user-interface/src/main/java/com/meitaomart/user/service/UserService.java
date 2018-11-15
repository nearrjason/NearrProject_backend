package com.meitaomart.user.service;

import java.util.List;

import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoUser;

public interface UserService {
	MeitaoResult changePassword(Long userId, String oldPassword, String newPassword);
	MeitaoResult changePassword(String emailAddress, String newPassword);
	
	MeitaoUser getUserByPrimaryId(Long userId);
	
	List<MeitaoAddress> getAddressListByUserId(Long userId);
	MeitaoAddress getAddressByPrimaryId(Long id);
	
	MeitaoBankingCard getCardByPrimaryId(Long id);
	List<MeitaoBankingCard> getCardListByUserId(Long userId);
	
	MeitaoResult updateUser(MeitaoUser user, Long userId);
	MeitaoResult setAsDefault(Long id, String type, Long userId);
	
	MeitaoResult addNewAddress(MeitaoAddress address, Long userId);
	MeitaoResult addNewCard(MeitaoBankingCard card, Long userId);
	
	MeitaoResult updateAddress(MeitaoAddress address, Long addressId, Long userId);
	MeitaoResult updateCard(MeitaoBankingCard card, Long cardId, Long userId);
	
	MeitaoResult deleteOne(Long id, String type, Long userId);
}
