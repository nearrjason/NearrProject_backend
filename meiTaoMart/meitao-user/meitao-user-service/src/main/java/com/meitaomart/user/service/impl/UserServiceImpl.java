package com.meitaomart.user.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.meitaomart.common.builder.ShippingInfoBuilder;
import com.meitaomart.common.pojo.ShippingInfo;
import com.meitaomart.common.utils.MeitaoResult;
import com.meitaomart.common.utils.PaymentUtils;
import com.meitaomart.common.utils.ShippingUtils;
import com.meitaomart.common.utils.TaxUtils;
import com.meitaomart.mapper.MeitaoAddressMapper;
import com.meitaomart.mapper.MeitaoBankingCardMapper;
import com.meitaomart.mapper.MeitaoUserMapper;
import com.meitaomart.pojo.MeitaoAddress;
import com.meitaomart.pojo.MeitaoAddressExample;
import com.meitaomart.pojo.MeitaoAddressExample.Criteria;
import com.meitaomart.pojo.MeitaoBankingCard;
import com.meitaomart.pojo.MeitaoBankingCardExample;
import com.meitaomart.pojo.MeitaoUser;
import com.meitaomart.pojo.MeitaoUserExample;
import com.meitaomart.user.service.UserService;
import com.shippo.model.Rate;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private MeitaoUserMapper meitaoUserMapper;
	@Autowired
	private MeitaoAddressMapper meitaoAddressMapper;
	@Autowired
	private MeitaoBankingCardMapper meitaoCardMapper;
	
	@Value("${ADDRESS_TYPE}")
	private String ADDRESS_TYPE;
	@Value("${CARD_TYPE}")
	private String CARD_TYPE;
	@Value("${DEFAULT_COUNTRY}")
	private String DEFAULT_COUNTRY;

	@Override
	public MeitaoResult changePassword(String emailAddress, String newPassword) {
		MeitaoUserExample example = new MeitaoUserExample();
		com.meitaomart.pojo.MeitaoUserExample.Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(emailAddress);
		List<MeitaoUser> meitaoUserList = meitaoUserMapper.selectByExample(example);
		if (meitaoUserList == null || meitaoUserList.size() == 0) {
			return MeitaoResult.build(201, "此邮箱不存在！请更换邮箱");
		}
		Long userId = meitaoUserList.get(0).getId();
		String md5Pass = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		meitaoUserMapper.updatePasswordByPrimaryKey(md5Pass, userId);
		return MeitaoResult.ok();
	}
	
	@Override
	public MeitaoResult changePassword(Long userId, String oldPassword, String newPassword) {
		MeitaoUser user = meitaoUserMapper.selectByPrimaryKey(userId);
		if (oldPassword == null || oldPassword.length() == 0) {
			return MeitaoResult.build(400, "请输入旧密码!");
		}
		
		if (newPassword == null || newPassword.length() == 0) {
			return MeitaoResult.build(400, "新密码不能为空!");
		}
			
		if (!DigestUtils.md5DigestAsHex(oldPassword.getBytes()).equals(user.getPassword())) {
			return MeitaoResult.build(400, "旧密码错误");
		}
		
		String md5Pass = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		meitaoUserMapper.updatePasswordByPrimaryKey(md5Pass, userId);
		return MeitaoResult.ok();
	}

	@Override
	public MeitaoUser getUserByPrimaryId(Long userId) {
		// TODO Auto-generated method stub
		MeitaoUser meitaoUser = meitaoUserMapper.selectByPrimaryKey(userId);
		return meitaoUser;
	}

	@Override
	public List<MeitaoAddress> getAddressListByUserId(Long userId) {
		// TODO Auto-generated method stub
		MeitaoAddressExample example = new MeitaoAddressExample();
		example.setOrderByClause("created_time DESC");
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<MeitaoAddress> list = meitaoAddressMapper.selectByExample(example);

		// 把默认地址放在第一个
		for (int i = 0; i < list.size(); i++) {
			MeitaoAddress curAddress = list.get(i);
			if (curAddress != null && Boolean.TRUE.equals(curAddress.getIsMain())) {
				list.remove(i);
				list.add(0, curAddress);
				break;
			}
		}
		return list;
	}

	@Override
	public MeitaoResult updateUser(MeitaoUser user, Long userId) {
		MeitaoUserExample example = new MeitaoUserExample();
		com.meitaomart.pojo.MeitaoUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userId);

		user.setUpdatedTime(new Date());
		if (user.getPhone() != null && user.getPhone().length() == 0) {
			user.setPhone(null);
		}

		user.setId(userId);
		meitaoUserMapper.updateByPrimaryKeySelective(user);
		
		MeitaoUser updatedUser = getUserByPrimaryId(userId);
		return MeitaoResult.ok(updatedUser);
	}

	@Override
	public List<MeitaoBankingCard> getCardListByUserId(Long userId) {
		MeitaoBankingCardExample example = new MeitaoBankingCardExample();
		example.setOrderByClause("created_time DESC");
		com.meitaomart.pojo.MeitaoBankingCardExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<MeitaoBankingCard> list = meitaoCardMapper.selectByExample(example);

		// 把默认地址放在第一个
		for (int i = 0; i < list.size(); i++) {
			MeitaoBankingCard curCard = list.get(i);
			if (curCard != null && Boolean.TRUE.equals(curCard.getIsMain())) {
				Collections.swap(list, i, 0);
				break;
			}
		}

		return list;
	}

	@Override
	public MeitaoResult setAsDefault(Long id, String type, Long userId) {
		// TODO Auto-generated method stub
		if (ADDRESS_TYPE.equals(type)) {
			MeitaoAddress meitaoAddress = getDefaultAddressByUserId(userId);
			if (meitaoAddress != null) {
				meitaoAddressMapper.setAsDefaultByPrimaryKey(false, meitaoAddress.getId());
			}
			meitaoAddressMapper.setAsDefaultByPrimaryKey(true, id);
		} else if (CARD_TYPE.equals(type)) {
			MeitaoBankingCard meitaoCard = getDefaultCardByUserId(userId);
			if (meitaoCard != null) {
				meitaoCardMapper.setAsDefaultByPrimaryKey(false, meitaoCard.getId());
			}
			meitaoCardMapper.setAsDefaultByPrimaryKey(true, id);
		} else {
			System.out.println("设置默认出错: 无相应类别");
		}
		
		return MeitaoResult.ok();
	}
	
	private MeitaoAddress getDefaultAddressByUserId(Long userId) {
		MeitaoAddressExample example = new MeitaoAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andIsMainEqualTo(Boolean.TRUE);
		List<MeitaoAddress> list = meitaoAddressMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	private MeitaoBankingCard getDefaultCardByUserId(Long userId) {
		MeitaoBankingCardExample example = new MeitaoBankingCardExample();
		com.meitaomart.pojo.MeitaoBankingCardExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andIsMainEqualTo(Boolean.TRUE);
		List<MeitaoBankingCard> list = meitaoCardMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public MeitaoAddress getAddressByPrimaryId(Long id) {
		return meitaoAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public MeitaoBankingCard getCardByPrimaryId(Long id) {
		return meitaoCardMapper.selectByPrimaryKey(id);
	}

	@Override
	public MeitaoResult deleteOne(Long id, String type, Long userId) {
		if (ADDRESS_TYPE.equals(type)) {
			MeitaoAddressExample example = new MeitaoAddressExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId).andIdEqualTo(id);
			meitaoAddressMapper.deleteByExample(example);
		} else if (CARD_TYPE.equals(type)) {
			MeitaoBankingCardExample example = new MeitaoBankingCardExample();
			com.meitaomart.pojo.MeitaoBankingCardExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId).andIdEqualTo(id);
			meitaoCardMapper.deleteByExample(example);
		} else {
			System.out.println("设置默认出错: 无相应类别");
		}
		
		return MeitaoResult.ok();
	}

	@Override
	public MeitaoResult addNewAddress(MeitaoAddress meitaoAddress, Long userId) {
		MeitaoResult checkResult = addressValidation(meitaoAddress, true);
		if (!Integer.valueOf(200).equals(checkResult.getStatus())) {
			return checkResult;
		}
		
		/*String validateAddressMessage = TaxUtils.validateAddress(meitaoAddress.getStreet(), meitaoAddress.getCity(), meitaoAddress.getState(), meitaoAddress.getZipcode(), userId);
		if (validateAddressMessage.length() > 0) {
			int index = validateAddressMessage.indexOf("-");
			validateAddressMessage = validateAddressMessage.substring(index + 1);
			validateAddressMessage = validateAddressMessage.replaceAll("to_", "");
			return MeitaoResult.build(201, validateAddressMessage);
		}*/
		
		ShippingInfo shippingInfo = ShippingInfoBuilder.getInstance().setFirstName(meitaoAddress.getFirstName())
				.setLastName(meitaoAddress.getLastName()).setPhoneNumber(meitaoAddress.getShippingPhone())
				.setStreet(meitaoAddress.getStreet()).setCity(meitaoAddress.getCity())
				.setState(meitaoAddress.getState()).setZip(meitaoAddress.getZipcode()).build();
		
		Rate shippingRate = ShippingUtils.getShippingRate(shippingInfo);
		if (shippingRate == null) {
			return MeitaoResult.build(400, "添加地址失败， 请检查地址信息是否正确！可能原因: city, state, zip code 不匹配！");
		}
		
		
		meitaoAddress.setCountry(DEFAULT_COUNTRY);
		meitaoAddress.setUserId(userId);
		meitaoAddress.setCreatedTime(new Date());
		meitaoAddressMapper.insert(meitaoAddress);
		return MeitaoResult.ok(meitaoAddress);
	}
	
	private MeitaoResult addressValidation(MeitaoAddress address, boolean isAdd) {
		if (address == null) {
			return MeitaoResult.build(400, "无地址信息!");
		}
		
		if (isAdd) {
			if (address.getStreet() == null || address.getStreet().length() == 0) {
				return MeitaoResult.build(400, "请输入 street 信息!");
			}
			
			if (address.getCity() == null || address.getCity().length() == 0) {
				return MeitaoResult.build(400, "请输入 city 信息!");
			}
			
			if (address.getState() == null || address.getState().length() == 0) {
				return MeitaoResult.build(400, "请输入 state 信息!");
			}
			
			if (address.getZipcode() == null || address.getZipcode().length() == 0) {
				return MeitaoResult.build(400, "请输入 zip code 信息!");
			}
			
			if (address.getFirstName() == null || address.getFirstName().length() == 0) {
				return MeitaoResult.build(400, "请输入 First Name!");
			}
			
			if (address.getLastName() == null || address.getLastName().length() == 0) {
				return MeitaoResult.build(400, "请输入 Last Name!");
			}
		}
		
		
		if (address.getStreet() != null) {
			if (address.getStreet().length() > 100) {
				return MeitaoResult.build(400, "street 信息输入长度请勿超过100个字符!");
			}
			
			if (!address.getStreet().matches("[a-zA-Z0-9\\s,.]*")) {
				return MeitaoResult.build(400, "street 必须为英文字符或数字!");
			}
		}
		
		if (address.getCity() != null) {
			if (!address.getCity().matches("[a-zA-Z\\s]*")) {
				return MeitaoResult.build(400, "city 必须为英文字符!");
			}
			
			if (address.getCity().length() > 20) {
				return MeitaoResult.build(400, "city 信息输入长度请勿超过20个字符!");
			}
		}
		
		if (address.getState() != null) {
			if (address.getState().length() > 20) {
				return MeitaoResult.build(400, "state 信息输入长度请勿超过20个字符!");
			}
			
			if (!address.getState().matches("[a-zA-Z]{2}")) {
				return MeitaoResult.build(400, "State 必须为2位英文字符！");
			}
		}
		
		if (address.getZipcode() != null) {
			if (!address.getZipcode().matches("[0-9]{5}")) {
				return MeitaoResult.build(400, "zip code 格式不正确: zip code 应为5位数字!");
			}
		}
		
		
		if (address.getFirstName() != null) {
			if (address.getFirstName().length() > 20) {
				return MeitaoResult.build(400, "地址 First Name 信息输入长度请勿超过20个字符!");
			}
			
			if (!address.getFirstName().matches("[a-zA-Z]*")) {
				return MeitaoResult.build(400, "地址 First Name 必须为英文字符！");
			}
		}
		
		if (address.getLastName() != null) {
			if (address.getLastName().length() > 20) {
				return MeitaoResult.build(400, "地址 Last Name 信息输入长度请勿超过20个字符!");
			}
			
			if (!address.getLastName().matches("[a-zA-Z]*")) {
				return MeitaoResult.build(400, "地址  Last Name 必须为英文字符！");
			}
		}
		
		return MeitaoResult.ok();
	}
	
	@Override
	public MeitaoResult addNewCard(MeitaoBankingCard card, Long userId) {
		MeitaoResult checkResult = cardValidation(card, true);
		if (!Integer.valueOf(200).equals(checkResult.getStatus())) {
			return checkResult;
		}
		card.setUserId(userId);
		card.setCreatedTime(new Date());
		card.setUpdatedTime(new Date());
		meitaoCardMapper.insert(card);
		return MeitaoResult.ok(card);
	}
	
	private MeitaoResult cardValidation(MeitaoBankingCard card, boolean isAdd) {
		if (card == null) {
			return MeitaoResult.build(400, "无银行卡信息!");
		}
		
		if (isAdd) {
			if (card.getCardNumber() == null) {
				return MeitaoResult.build(400, "请输入银行卡号!");
			}
			
			if (card.getFirstName() == null || card.getFirstName().length() == 0) {
				return MeitaoResult.build(400, "银行卡 First Name 不能为空!");
			}
			
			if (card.getLastName() == null || card.getLastName().length() == 0) {
				return MeitaoResult.build(400, "银行卡 Last Name 不能为空!");
			}
			
			if (card.getMonth() == null) {
				return MeitaoResult.build(400, "无银行卡Month信息!");
			}
			
			if (card.getYear() == null) {
				return MeitaoResult.build(400, "无银行卡Year信息!");
			}
			
			if (card.getCardNumber() != null) {
				MeitaoBankingCardExample example = new MeitaoBankingCardExample();
				com.meitaomart.pojo.MeitaoBankingCardExample.Criteria criteria = example.createCriteria();
				criteria.andCardNumberEqualTo(card.getCardNumber());
				List<MeitaoBankingCard> list = meitaoCardMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					return MeitaoResult.build(400, "该银行卡号已存在!");
				} 
			}
		}
		
		
		if (card.getFirstName() != null && !card.getFirstName().matches("[a-zA-Z]*")) {
			return MeitaoResult.build(400, "银行卡 First Name 必须为英文字符！");
		}
		
		if (card.getLastName() != null && !card.getLastName().matches("[a-zA-Z]*")) {
			return MeitaoResult.build(400, "银行卡 Last Name 必须为英文字符！");
		}
		
		String checkResultMessage = "";
		if (isAdd) {
			checkResultMessage = PaymentUtils.checkCard(card.getCardNumber().toString(), card.getMonth(), card.getYear());
		} else if (card.getCardNumber() != null) {
			checkResultMessage = PaymentUtils.checkCard(card.getCardNumber().toString(), card.getMonth() != null ? card.getMonth() : "12", card.getYear() != null ? card.getYear() : "2030");
		}
		
		if (checkResultMessage.length() > 0) {
			return MeitaoResult.build(201, checkResultMessage);
		}
		
		return MeitaoResult.ok();
	}

	@Override
	public MeitaoResult updateAddress(MeitaoAddress address, Long addressId, Long userId) {
		MeitaoResult checkResult = addressValidation(address, false);
		if (!Integer.valueOf(200).equals(checkResult.getStatus())) {
			return checkResult;
		}
		
		MeitaoAddressExample example = new MeitaoAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andIdEqualTo(addressId);
		
		address.setUpdatedTime(new Date());
		meitaoAddressMapper.updateByExampleSelective(address, example);
		return MeitaoResult.ok(address);
	}

	@Override
	public MeitaoResult updateCard(MeitaoBankingCard card, Long cardId, Long userId) {
		MeitaoResult checkResult = cardValidation(card, false);
		if (!Integer.valueOf(200).equals(checkResult.getStatus())) {
			return checkResult;
		}
		MeitaoBankingCardExample example = new MeitaoBankingCardExample();
		com.meitaomart.pojo.MeitaoBankingCardExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId).andIdEqualTo(cardId);
		
		card.setUpdatedTime(new Date());
		meitaoCardMapper.updateByExampleSelective(card, example);
		return MeitaoResult.ok(card);
	}

}