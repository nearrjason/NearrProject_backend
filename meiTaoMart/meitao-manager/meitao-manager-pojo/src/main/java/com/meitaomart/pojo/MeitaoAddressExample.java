package com.meitaomart.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeitaoAddressExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public MeitaoAddressExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andStreetIsNull() {
			addCriterion("street is null");
			return (Criteria) this;
		}

		public Criteria andStreetIsNotNull() {
			addCriterion("street is not null");
			return (Criteria) this;
		}

		public Criteria andStreetEqualTo(String value) {
			addCriterion("street =", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetNotEqualTo(String value) {
			addCriterion("street <>", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetGreaterThan(String value) {
			addCriterion("street >", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetGreaterThanOrEqualTo(String value) {
			addCriterion("street >=", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetLessThan(String value) {
			addCriterion("street <", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetLessThanOrEqualTo(String value) {
			addCriterion("street <=", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetLike(String value) {
			addCriterion("street like", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetNotLike(String value) {
			addCriterion("street not like", value, "street");
			return (Criteria) this;
		}

		public Criteria andStreetIn(List<String> values) {
			addCriterion("street in", values, "street");
			return (Criteria) this;
		}

		public Criteria andStreetNotIn(List<String> values) {
			addCriterion("street not in", values, "street");
			return (Criteria) this;
		}

		public Criteria andStreetBetween(String value1, String value2) {
			addCriterion("street between", value1, value2, "street");
			return (Criteria) this;
		}

		public Criteria andStreetNotBetween(String value1, String value2) {
			addCriterion("street not between", value1, value2, "street");
			return (Criteria) this;
		}

		public Criteria andCityIsNull() {
			addCriterion("city is null");
			return (Criteria) this;
		}

		public Criteria andCityIsNotNull() {
			addCriterion("city is not null");
			return (Criteria) this;
		}

		public Criteria andCityEqualTo(String value) {
			addCriterion("city =", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotEqualTo(String value) {
			addCriterion("city <>", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityGreaterThan(String value) {
			addCriterion("city >", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityGreaterThanOrEqualTo(String value) {
			addCriterion("city >=", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLessThan(String value) {
			addCriterion("city <", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLessThanOrEqualTo(String value) {
			addCriterion("city <=", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLike(String value) {
			addCriterion("city like", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotLike(String value) {
			addCriterion("city not like", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityIn(List<String> values) {
			addCriterion("city in", values, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotIn(List<String> values) {
			addCriterion("city not in", values, "city");
			return (Criteria) this;
		}

		public Criteria andCityBetween(String value1, String value2) {
			addCriterion("city between", value1, value2, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotBetween(String value1, String value2) {
			addCriterion("city not between", value1, value2, "city");
			return (Criteria) this;
		}

		public Criteria andStateIsNull() {
			addCriterion("state is null");
			return (Criteria) this;
		}

		public Criteria andStateIsNotNull() {
			addCriterion("state is not null");
			return (Criteria) this;
		}

		public Criteria andStateEqualTo(String value) {
			addCriterion("state =", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotEqualTo(String value) {
			addCriterion("state <>", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThan(String value) {
			addCriterion("state >", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThanOrEqualTo(String value) {
			addCriterion("state >=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThan(String value) {
			addCriterion("state <", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThanOrEqualTo(String value) {
			addCriterion("state <=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLike(String value) {
			addCriterion("state like", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotLike(String value) {
			addCriterion("state not like", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateIn(List<String> values) {
			addCriterion("state in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotIn(List<String> values) {
			addCriterion("state not in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateBetween(String value1, String value2) {
			addCriterion("state between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotBetween(String value1, String value2) {
			addCriterion("state not between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andZipcodeIsNull() {
			addCriterion("zipcode is null");
			return (Criteria) this;
		}

		public Criteria andZipcodeIsNotNull() {
			addCriterion("zipcode is not null");
			return (Criteria) this;
		}

		public Criteria andZipcodeEqualTo(String value) {
			addCriterion("zipcode =", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeNotEqualTo(String value) {
			addCriterion("zipcode <>", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeGreaterThan(String value) {
			addCriterion("zipcode >", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeGreaterThanOrEqualTo(String value) {
			addCriterion("zipcode >=", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeLessThan(String value) {
			addCriterion("zipcode <", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeLessThanOrEqualTo(String value) {
			addCriterion("zipcode <=", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeLike(String value) {
			addCriterion("zipcode like", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeNotLike(String value) {
			addCriterion("zipcode not like", value, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeIn(List<String> values) {
			addCriterion("zipcode in", values, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeNotIn(List<String> values) {
			addCriterion("zipcode not in", values, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeBetween(String value1, String value2) {
			addCriterion("zipcode between", value1, value2, "zipcode");
			return (Criteria) this;
		}

		public Criteria andZipcodeNotBetween(String value1, String value2) {
			addCriterion("zipcode not between", value1, value2, "zipcode");
			return (Criteria) this;
		}

		public Criteria andCountryIsNull() {
			addCriterion("country is null");
			return (Criteria) this;
		}

		public Criteria andCountryIsNotNull() {
			addCriterion("country is not null");
			return (Criteria) this;
		}

		public Criteria andCountryEqualTo(String value) {
			addCriterion("country =", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryNotEqualTo(String value) {
			addCriterion("country <>", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryGreaterThan(String value) {
			addCriterion("country >", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryGreaterThanOrEqualTo(String value) {
			addCriterion("country >=", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryLessThan(String value) {
			addCriterion("country <", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryLessThanOrEqualTo(String value) {
			addCriterion("country <=", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryLike(String value) {
			addCriterion("country like", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryNotLike(String value) {
			addCriterion("country not like", value, "country");
			return (Criteria) this;
		}

		public Criteria andCountryIn(List<String> values) {
			addCriterion("country in", values, "country");
			return (Criteria) this;
		}

		public Criteria andCountryNotIn(List<String> values) {
			addCriterion("country not in", values, "country");
			return (Criteria) this;
		}

		public Criteria andCountryBetween(String value1, String value2) {
			addCriterion("country between", value1, value2, "country");
			return (Criteria) this;
		}

		public Criteria andCountryNotBetween(String value1, String value2) {
			addCriterion("country not between", value1, value2, "country");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Long value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Long value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Long value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Long value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Long value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Long> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Long> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Long value1, Long value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Long value1, Long value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andIsMainIsNull() {
			addCriterion("is_main is null");
			return (Criteria) this;
		}

		public Criteria andIsMainIsNotNull() {
			addCriterion("is_main is not null");
			return (Criteria) this;
		}

		public Criteria andIsMainEqualTo(Boolean value) {
			addCriterion("is_main =", value, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainNotEqualTo(Boolean value) {
			addCriterion("is_main <>", value, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainGreaterThan(Boolean value) {
			addCriterion("is_main >", value, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainGreaterThanOrEqualTo(Boolean value) {
			addCriterion("is_main >=", value, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainLessThan(Boolean value) {
			addCriterion("is_main <", value, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainLessThanOrEqualTo(Boolean value) {
			addCriterion("is_main <=", value, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainIn(List<Boolean> values) {
			addCriterion("is_main in", values, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainNotIn(List<Boolean> values) {
			addCriterion("is_main not in", values, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainBetween(Boolean value1, Boolean value2) {
			addCriterion("is_main between", value1, value2, "isMain");
			return (Criteria) this;
		}

		public Criteria andIsMainNotBetween(Boolean value1, Boolean value2) {
			addCriterion("is_main not between", value1, value2, "isMain");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneIsNull() {
			addCriterion("shipping_phone is null");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneIsNotNull() {
			addCriterion("shipping_phone is not null");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneEqualTo(String value) {
			addCriterion("shipping_phone =", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneNotEqualTo(String value) {
			addCriterion("shipping_phone <>", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneGreaterThan(String value) {
			addCriterion("shipping_phone >", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("shipping_phone >=", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneLessThan(String value) {
			addCriterion("shipping_phone <", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneLessThanOrEqualTo(String value) {
			addCriterion("shipping_phone <=", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneLike(String value) {
			addCriterion("shipping_phone like", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneNotLike(String value) {
			addCriterion("shipping_phone not like", value, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneIn(List<String> values) {
			addCriterion("shipping_phone in", values, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneNotIn(List<String> values) {
			addCriterion("shipping_phone not in", values, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneBetween(String value1, String value2) {
			addCriterion("shipping_phone between", value1, value2, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andShippingPhoneNotBetween(String value1, String value2) {
			addCriterion("shipping_phone not between", value1, value2, "shippingPhone");
			return (Criteria) this;
		}

		public Criteria andFirstNameIsNull() {
			addCriterion("first_name is null");
			return (Criteria) this;
		}

		public Criteria andFirstNameIsNotNull() {
			addCriterion("first_name is not null");
			return (Criteria) this;
		}

		public Criteria andFirstNameEqualTo(String value) {
			addCriterion("first_name =", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameNotEqualTo(String value) {
			addCriterion("first_name <>", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameGreaterThan(String value) {
			addCriterion("first_name >", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameGreaterThanOrEqualTo(String value) {
			addCriterion("first_name >=", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameLessThan(String value) {
			addCriterion("first_name <", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameLessThanOrEqualTo(String value) {
			addCriterion("first_name <=", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameLike(String value) {
			addCriterion("first_name like", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameNotLike(String value) {
			addCriterion("first_name not like", value, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameIn(List<String> values) {
			addCriterion("first_name in", values, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameNotIn(List<String> values) {
			addCriterion("first_name not in", values, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameBetween(String value1, String value2) {
			addCriterion("first_name between", value1, value2, "firstName");
			return (Criteria) this;
		}

		public Criteria andFirstNameNotBetween(String value1, String value2) {
			addCriterion("first_name not between", value1, value2, "firstName");
			return (Criteria) this;
		}

		public Criteria andLastNameIsNull() {
			addCriterion("last_name is null");
			return (Criteria) this;
		}

		public Criteria andLastNameIsNotNull() {
			addCriterion("last_name is not null");
			return (Criteria) this;
		}

		public Criteria andLastNameEqualTo(String value) {
			addCriterion("last_name =", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameNotEqualTo(String value) {
			addCriterion("last_name <>", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameGreaterThan(String value) {
			addCriterion("last_name >", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameGreaterThanOrEqualTo(String value) {
			addCriterion("last_name >=", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameLessThan(String value) {
			addCriterion("last_name <", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameLessThanOrEqualTo(String value) {
			addCriterion("last_name <=", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameLike(String value) {
			addCriterion("last_name like", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameNotLike(String value) {
			addCriterion("last_name not like", value, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameIn(List<String> values) {
			addCriterion("last_name in", values, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameNotIn(List<String> values) {
			addCriterion("last_name not in", values, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameBetween(String value1, String value2) {
			addCriterion("last_name between", value1, value2, "lastName");
			return (Criteria) this;
		}

		public Criteria andLastNameNotBetween(String value1, String value2) {
			addCriterion("last_name not between", value1, value2, "lastName");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeIsNull() {
			addCriterion("created_time is null");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeIsNotNull() {
			addCriterion("created_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeEqualTo(Date value) {
			addCriterion("created_time =", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeNotEqualTo(Date value) {
			addCriterion("created_time <>", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeGreaterThan(Date value) {
			addCriterion("created_time >", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("created_time >=", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeLessThan(Date value) {
			addCriterion("created_time <", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
			addCriterion("created_time <=", value, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeIn(List<Date> values) {
			addCriterion("created_time in", values, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeNotIn(List<Date> values) {
			addCriterion("created_time not in", values, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeBetween(Date value1, Date value2) {
			addCriterion("created_time between", value1, value2, "createdTime");
			return (Criteria) this;
		}

		public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
			addCriterion("created_time not between", value1, value2, "createdTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeIsNull() {
			addCriterion("updated_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeIsNotNull() {
			addCriterion("updated_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeEqualTo(Date value) {
			addCriterion("updated_time =", value, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeNotEqualTo(Date value) {
			addCriterion("updated_time <>", value, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeGreaterThan(Date value) {
			addCriterion("updated_time >", value, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("updated_time >=", value, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeLessThan(Date value) {
			addCriterion("updated_time <", value, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
			addCriterion("updated_time <=", value, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeIn(List<Date> values) {
			addCriterion("updated_time in", values, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeNotIn(List<Date> values) {
			addCriterion("updated_time not in", values, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
			addCriterion("updated_time between", value1, value2, "updatedTime");
			return (Criteria) this;
		}

		public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
			addCriterion("updated_time not between", value1, value2, "updatedTime");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}