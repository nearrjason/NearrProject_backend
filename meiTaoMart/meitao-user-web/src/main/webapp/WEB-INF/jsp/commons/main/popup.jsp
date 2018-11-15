<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--change password popup-->
<div class="changeForm" id="changeForm">
	<h2>设置新密码</h2>
	<form id="changePasswordForm" class="form-container"
		onsubmit="return false">

		<label for="oldPW">请输入旧密码</label> <input id="oldPassword"
			type="password" placeholder="" name="oldPassword" style="width: 90%">&emsp;
		<br> <label for="newPW">请输入新密码</label> <input id="newPassword"
			type="password" placeholder="" name="newPassword"
			pattern="(?=.*\d)(?=.*[a-z]).{8,}"
			onfocusout="checkPW(this)
			style="width: 90%" >&emsp; <br>
		<label for="newPW-repeat">请重复新密码</label> <input type="password"
			placeholder="" name="newPasswordRepeat" style="width: 90%">&emsp;
		<br>
		<p id="reset_password_error_message" class="error_message"></p>

	</form>
	<button type="button" class="btn cancel" onclick="closeChangeForm()">Cancel</button>
	<button type="button" class="btn" onclick="submitChangePassword()">Submit</button>
</div>



<!--Edit credit card-->
<c:forEach items="${cardList}" var="card" varStatus="status">
	<c:set var="cardNumberStr" value="${fn:trim(card.cardNumber)}"></c:set>
	<div class="form-popup" id="editCreditForm${status.index }">
		<h2>编辑银行卡</h2>
		<form id="editCreditFormSubmit${status.index }" class="form-container"
			onsubmit="return false">
			<input type="text" placeholder="First Name" value="${card.firstName}"
				name="firstName" style="width: 60%">&emsp; <br> <input
				type="text" placeholder="Last Name" value="${card.lastName}"
				name="lastName" style="width: 60%">&emsp; <br> <input
				type="text" placeholder="Card Number"
				value="****&emsp;****&emsp;****&emsp;${fn:substring(cardNumberStr, 12, 16)}"
				disabled style="width: 60%"> <br> <input type="hidden"
				value="${card.cardNumber}" name="cardNumber">
			<div>Month</div>
			<select name="month">
				<option value="01"
					${card.month == "01" ? 'selected="selected"' : ''}>01</option>
				<option value="02"
					${card.month == "02" ? 'selected="selected"' : ''}>02</option>
				<option value="03"
					${card.month == "03" ? 'selected="selected"' : ''}>03</option>
				<option value="04"
					${card.month == "04" ? 'selected="selected"' : ''}>04</option>
				<option value="05"
					${card.month == "05" ? 'selected="selected"' : ''}>05</option>
				<option value="06"
					${card.month == "06" ? 'selected="selected"' : ''}>06</option>
				<option value="07"
					${card.month == "07" ? 'selected="selected"' : ''}>07</option>
				<option value="08"
					${card.month == "08" ? 'selected="selected"' : ''}>08</option>
				<option value="09"
					${card.month == "09" ? 'selected="selected"' : ''}>09</option>
				<option value="10"
					${card.month == "10" ? 'selected="selected"' : ''}>10</option>
				<option value="11"
					${card.month == "11" ? 'selected="selected"' : ''}>11</option>
				<option value="12"
					${card.month == "12" ? 'selected="selected"' : ''}>12</option>
			</select>

			<div>Year</div>
			<select name="year">
				<option value="2018"
					${card.year == "2018" ? 'selected="selected"' : ''}>2018</option>
				<option value="2019"
					${card.year == "2019" ? 'selected="selected"' : ''}>2019</option>
				<option value="2020"
					${card.year == "2020" ? 'selected="selected"' : ''}>2020</option>
				<option value="2021"
					${card.year == "2021" ? 'selected="selected"' : ''}>2021</option>
				<option value="2022"
					${card.year == "2022" ? 'selected="selected"' : ''}>2022</option>
				<option value="2023"
					${card.year == "2023" ? 'selected="selected"' : ''}>2023</option>
				<option value="2024"
					${card.year == "2024" ? 'selected="selected"' : ''}>2024</option>
				<option value="2025"
					${card.year == "2025" ? 'selected="selected"' : ''}>2025</option>
				<option value="2026"
					${card.year == "2026" ? 'selected="selected"' : ''}>2026</option>
				<option value="2027"
					${card.year == "2027" ? 'selected="selected"' : ''}>2027</option>
				<option value="2028"
					${card.year == "2028" ? 'selected="selected"' : ''}>2028</option>
				<option value="2029"
					${card.year == "2029" ? 'selected="selected"' : ''}>2029</option>
				<option value="2030"
					${card.year == "2030" ? 'selected="selected"' : ''}>2030</option>
				<option value="2031"
					${card.year == "2031" ? 'selected="selected"' : ''}>2031</option>
				<option value="2032"
					${card.year == "2032" ? 'selected="selected"' : ''}>2032</option>
				<option value="2033"
					${card.year == "2033" ? 'selected="selected"' : ''}>2033</option>
				<option value="2034"
					${card.year == "2034" ? 'selected="selected"' : ''}>2034</option>
				<option value="2035"
					${card.year == "2035" ? 'selected="selected"' : ''}>2035</option>
				<option value="2036"
					${card.year == "2036" ? 'selected="selected"' : ''}>2036</option>
				<option value="2037"
					${card.year == "2037" ? 'selected="selected"' : ''}>2037</option>
				<option value="2038"
					${card.year == "2038" ? 'selected="selected"' : ''}>2038</option>
			</select> <input type="hidden" name="cardId" value="${card.id }">
			<p id="user_update_card_method${status.index }" class="error_message"></p>

		</form>
		<button type="button" class="btn cancel"
			onclick="closeForm('#editCreditForm${status.index }')">Cancel</button>
		<button
			onclick="confirmUpdate('#editCreditFormSubmit${status.index }', 'card', '${status.index }')"
			type="submit" class="btn">Submit</button>
	</div>
</c:forEach>

<!--Add credit card-->
<div class="addForm" id="addCreditForm">
	<h2>添加银行卡</h2>
	<div class="form-container2">
		<div class="container-left">

			<form id="addCardFormSubmit" onsubmit="return false">
				<h2>信用卡信息</h2>
				<br>
				<!-- <label for="">First Name</label><br>
				<input type="text" name="firstName"
					>&emsp; <br> 
				<label for="">Last Name</label><br>
				<input type="text"
					name="lastName" >&emsp;
				<br>  -->
				
				<div class="billing-name">
					<div>
						<label for="">First Name</label><br> <input type="text" value=""
							name="name" style="width: 90%"> 
					</div>

					<div>
						<label for="">Last Name</label><br> <input type="text" value=""
							name="name" style="width: 90%"> 
					</div>
				</div>
				
				<label for="">Card Number</label><br>
				
				<div class="card-num-logo">
                    <input type="text"
					name="cardNumber" style="width: 65%">
                    <img src="/icons/image-cvv.svg" alt="">
                </div>
				

				<div>Month</div>
				<div>
					<select name="month">
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
				</div>
				<div>Year</div>
				<div>
					<select name="year">
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
						<option value="2031">2031</option>
						<option value="2032">2032</option>
						<option value="2033">2033</option>
						<option value="2034">2034</option>
						<option value="2035">2035</option>
						<option value="2036">2036</option>
						<option value="2037">2037</option>
						<option value="2038">2038</option>
					</select>
				</div>
			</form>
		</div>
		<div class="container-right">
			<form id="addBillingAddressFormSubmit" onsubmit="return false">
				<h2>账单地址</h2>
				<br>
				<div class="billing-name">
					<div>
						<label for="">First Name</label><br> <input type="text" value=""
							name="firstName" style="width: 90%"> 
					</div>

					<div>
						<label for="">Last Name</label><br> <input type="text" value=""
							name="lastName" style="width: 90%"> 
					</div>
				</div>
				
				<label for="">Phone
							Number</label><br>
				<input type="text" value="" name="shippingPhone" style="width: 90%">
				<label for="">Street</label><br> <input type="text" value=""
					name="street" style="width: 90%"> <br>
				<div class="city-state-zip">
					<div>
						<label for="">City</label> <input type="text" value="" name="city"
							style="width: 95%">
					</div>
					<div class="billing-state">
						<label for="">State</label>
						<!-- <input type="text" placeholder="State" value="" name="state"  style="width:95%"> -->
						<select name="state" style="width: 95%">
							<option value="AL"></option>
							<option value="AL">AL</option>
							<option value="AK">AK</option>
							<option value="AR">AR</option>
							<option value="AZ">AZ</option>
							<option value="CA">CA</option>
							<option value="CO">CO</option>
							<option value="CT">CT</option>
							<option value="DC">DC</option>
							<option value="DE">DE</option>
							<option value="FL">FL</option>
							<option value="GA">GA</option>
							<option value="HI">HI</option>
							<option value="IA">IA</option>
							<option value="ID">ID</option>
							<option value="IL">IL</option>
							<option value="IN">IN</option>
							<option value="KS">KS</option>
							<option value="KY">KY</option>
							<option value="LA">LA</option>
							<option value="MA">MA</option>
							<option value="MD">MD</option>
							<option value="ME">ME</option>
							<option value="MI">MI</option>
							<option value="MN">MN</option>
							<option value="MO">MO</option>
							<option value="MS">MS</option>
							<option value="MT">MT</option>
							<option value="NC">NC</option>
							<option value="NE">NE</option>
							<option value="NH">NH</option>
							<option value="NJ">NJ</option>
							<option value="NM">NM</option>
							<option value="NV">NV</option>
							<option value="NY">NY</option>
							<option value="ND">ND</option>
							<option value="OH">OH</option>
							<option value="OK">OK</option>
							<option value="OR">OR</option>
							<option value="PA">PA</option>
							<option value="RI">RI</option>
							<option value="SC">SC</option>
							<option value="SD">SD</option>
							<option value="TN">TN</option>
							<option value="TX">TX</option>
							<option value="UT">UT</option>
							<option value="VT">VT</option>
							<option value="VA">VA</option>
							<option value="WA">WA</option>
							<option value="WI">WI</option>
							<option value="WV">WV</option>
							<option value="WY">WY</option>
						</select>
					</div>
					<div>
						<label for="">Zip Code</label> <input type="text" value=""
							name="zipcode" style="width: 95%">
					</div>
				</div>
			</form>
		</div>
	</div>
	<p id="user_add_card" class="error_message"></p>
	<button type="button" class="btn cancel"
		onclick="closeAddForm('#addCreditForm')">Cancel</button>
	<button onclick="confirmAdd('#addCardFormSubmit', 'card')"
		type="submit" class="btn">Submit</button>
</div>

<!--delete credit card popup-->
<div class="popup-box" id="deleteCreditForm">
	<p class="question">确定删除信用卡？</p>
	<input id="" type="hidden" deleteType="card">
	<button type="submit" class="confirm bttn"
		onclick="confirmDelete('#deleteCreditForm')">确认</button>
	<button type="button" class="not-confirm bttn"
		onclick="closepopup('#deleteCreditForm')">取消</button>
</div>



<!--Edit address popup-->
<c:forEach items="${addressList}" var="address" varStatus="status">
	<div class="form-popup" id="editAddressForm${status.index }">
		<h2>编辑地址</h2>
		<form id="editAddressFormSubmit${status.index }"
			class="form-container" onsubmit="return false">

			<input type="text" placeholder="First Name:" name="firstName"
				style="width: 60%" value="${address.firstName }">&emsp; <br>
			<input type="text" placeholder="Last Name:" name="lastName"
				style="width: 60%" value="${address.lastName }">&emsp; <br>


			<div>Shipping Phone:</div>
			<!-- <input type="text" placeholder="Shipping Phone:"
			name="shippingPhone" style="width: 60%">&emsp; <br> -->
			<div class="email-phone-fill keep-parallel">
				<!-- <input type="email" name="emailaddr" class="emailaddr-fill fillup"> -->
				<input id="userUpdateAddressShippingPhoneOne${status.index }"
					class="phonenum-fill fillup" type="number" placeholder=""
					maxlength="3" value="${fn:substring(address.shippingPhone, 0, 3) }" />
				<div class="phonemark">-</div>
				<input id="userUpdateAddressShippingPhoneTwo${status.index }"
					class="phonenum-fill fillup" type="number" placeholder=""
					maxlength="3" value="${fn:substring(address.shippingPhone, 3, 6) }" />
				<div class="phonemark">-</div>
				<input id="userUpdateAddressShippingPhoneThree${status.index }"
					class="phonenum-fill fillup" type="number" placeholder=""
					maxlength="4"
					value="${fn:substring(address.shippingPhone, 6, 10) }" />
			</div>

			<input id="userUpdateAddressShippingPhone${status.index }"
				name="shippingPhone" type="hidden" class="emailaddr-fill fillup"
				value=""> <br> <input type="text" placeholder="Street:"
				name="street" style="width: 60%" value="${address.street }">&emsp;
			<br> <input type="text" placeholder="City:" name="city"
				style="width: 30%" value="${address.city }">&emsp; <br>






			<%-- <input type="text"
				placeholder="State: e.g. NJ" name="state" style="width: 20%"
				value="${address.state }">&emsp; <br>  --%>


			<div class="city-state-zip-fill keep-parallel">
				<select class="state-fill fillup" name="state">
					<option value="" selected="selected">--State--</option>
					<option value="AL"
						${address.state == "AL" ? 'selected="selected"' : ''}>AL</option>
					<option value="AK"
						${address.state == "AK" ? 'selected="selected"' : ''}>AK</option>
					<option value="AR"
						${address.state == "AR" ? 'selected="selected"' : ''}>AR</option>
					<option value="AZ"
						${address.state == "AZ" ? 'selected="selected"' : ''}>AZ</option>
					<option value="CA"
						${address.state == "CA" ? 'selected="selected"' : ''}>CA</option>
					<option value="CO"
						${address.state == "CO" ? 'selected="selected"' : ''}>CO</option>
					<option value="CT"
						${address.state == "CT" ? 'selected="selected"' : ''}>CT</option>
					<option value="DC"
						${address.state == "DC" ? 'selected="selected"' : ''}>DC</option>
					<option value="DE"
						${address.state == "DE" ? 'selected="selected"' : ''}>DE</option>
					<option value="FL"
						${address.state == "FL" ? 'selected="selected"' : ''}>FL</option>
					<option value="GA"
						${address.state == "GA" ? 'selected="selected"' : ''}>GA</option>
					<option value="HI"
						${address.state == "HI" ? 'selected="selected"' : ''}>HI</option>
					<option value="IA"
						${address.state == "IA" ? 'selected="selected"' : ''}>IA</option>
					<option value="ID"
						${address.state == "ID" ? 'selected="selected"' : ''}>ID</option>
					<option value="IL"
						${address.state == "IL" ? 'selected="selected"' : ''}>IL</option>
					<option value="IN"
						${address.state == "IN" ? 'selected="selected"' : ''}>IN</option>
					<option value="KS"
						${address.state == "KS" ? 'selected="selected"' : ''}>KS</option>
					<option value="KY"
						${address.state == "KY" ? 'selected="selected"' : ''}>KY</option>
					<option value="LA"
						${address.state == "LA" ? 'selected="selected"' : ''}>LA</option>
					<option value="MA"
						${address.state == "MA" ? 'selected="selected"' : ''}>MA</option>
					<option value="MD"
						${address.state == "MD" ? 'selected="selected"' : ''}>MD</option>
					<option value="ME"
						${address.state == "ME" ? 'selected="selected"' : ''}>ME</option>
					<option value="MI"
						${address.state == "MI" ? 'selected="selected"' : ''}>MI</option>
					<option value="MN"
						${address.state == "MN" ? 'selected="selected"' : ''}>MN</option>
					<option value="MO"
						${address.state == "MO" ? 'selected="selected"' : ''}>MO</option>
					<option value="MS"
						${address.state == "MS" ? 'selected="selected"' : ''}>MS</option>
					<option value="MT"
						${address.state == "MT" ? 'selected="selected"' : ''}>MT</option>
					<option value="NC"
						${address.state == "NC" ? 'selected="selected"' : ''}>NC</option>
					<option value="NE"
						${address.state == "NE" ? 'selected="selected"' : ''}>NE</option>
					<option value="NH"
						${address.state == "NH" ? 'selected="selected"' : ''}>NH</option>
					<option value="NJ"
						${address.state == "NJ" ? 'selected="selected"' : ''}>NJ</option>
					<option value="NM"
						${address.state == "NM" ? 'selected="selected"' : ''}>NM</option>
					<option value="NV"
						${address.state == "NV" ? 'selected="selected"' : ''}>NV</option>
					<option value="NY"
						${address.state == "NY" ? 'selected="selected"' : ''}>NY</option>
					<option value="ND"
						${address.state == "ND" ? 'selected="selected"' : ''}>ND</option>
					<option value="OH"
						${address.state == "OH" ? 'selected="selected"' : ''}>OH</option>
					<option value="OK"
						${address.state == "OK" ? 'selected="selected"' : ''}>OK</option>
					<option value="OR"
						${address.state == "OR" ? 'selected="selected"' : ''}>OR</option>
					<option value="PA"
						${address.state == "PA" ? 'selected="selected"' : ''}>PA</option>
					<option value="RI"
						${address.state == "RI" ? 'selected="selected"' : ''}>RI</option>
					<option value="SC"
						${address.state == "SC" ? 'selected="selected"' : ''}>SC</option>
					<option value="SD"
						${address.state == "SD" ? 'selected="selected"' : ''}>SD</option>
					<option value="TN"
						${address.state == "TN" ? 'selected="selected"' : ''}>TN</option>
					<option value="TX"
						${address.state == "TX" ? 'selected="selected"' : ''}>TX</option>
					<option value="UT"
						${address.state == "UT" ? 'selected="selected"' : ''}>UT</option>
					<option value="VT"
						${address.state == "VT" ? 'selected="selected"' : ''}>VT</option>
					<option value="VA"
						${address.state == "VA" ? 'selected="selected"' : ''}>VA</option>
					<option value="WA"
						${address.state == "WA" ? 'selected="selected"' : ''}>WA</option>
					<option value="WI"
						${address.state == "WI" ? 'selected="selected"' : ''}>WI</option>
					<option value="WV"
						${address.state == "WV" ? 'selected="selected"' : ''}>WV</option>
					<option value="WY"
						${address.state == "WY" ? 'selected="selected"' : ''}>WY</option>
				</select>
			</div>



			<input type="text" placeholder="Zip Code:" name="zipcode"
				style="width: 20%" value="${address.zipcode }"> <input
				type="hidden" name="addressId" value="${address.id }">
			<p id="user_update_address_method${status.index }"
				class="error_message"></p>
		</form>
		<button type="button" class="btn cancel"
			onclick="closeAddForm('#editAddressForm${status.index }')">Cancel</button>
		<button
			onclick="confirmUpdate('#editAddressFormSubmit${status.index }', 'address', '${status.index }')"
			type="submit" class="btn">Submit</button>
	</div>
</c:forEach>


<!--Add address card-->
<div class="addForm" id="addAddressForm">
	<h2>添加新地址</h2>
	<form id="addAddressFormSubmit" class="form-container"
		onsubmit="return false">


		<input type="text" placeholder="First Name:" name="firstName"
			style="width: 60%">&emsp; <br> <input type="text"
			placeholder="Last Name:" name="lastName" style="width: 60%">&emsp;
		<br>



		<div style="font-size: 12px; margin-left: 15px; margin-top: 10px;">Shipping
			Phone:</div>
		<!-- <input type="text" placeholder="Shipping Phone:"
			name="shippingPhone" style="width: 60%">&emsp; <br> -->

		<div class="email-phone-fill keep-parallel">
			<!-- <input type="email" name="emailaddr" class="emailaddr-fill fillup"> -->
			<input id="userAddAddressShippingPhone1" class="phonenum-fill fillup"
				type="number" placeholder="" maxlength="3" value="" />
			<div class="phonemark">-</div>
			<input id="userAddAddressShippingPhone2" class="phonenum-fill fillup"
				type="number" placeholder="" maxlength="3" value="" />
			<div class="phonemark">-</div>
			<input id="userAddAddressShippingPhone3" class="phonenum-fill fillup"
				type="number" placeholder="" maxlength="4" value="" />
		</div>

		<input id="userAddAddressShippingPhone" name="shippingPhone"
			type="hidden" value=""> <br> <input type="text"
			placeholder="Street:" name="street" style="width: 60%">&emsp;
		<br> <input type="text" placeholder="City:" name="city"
			style="width: 30%">&emsp; <br>

		<!-- <input type="text"
			placeholder="State: e.g. NJ" name="state" style="width: 20%">&emsp;
		<br> -->
		<div class="city-state-zip-fill keep-parallel">
			<select class="state-fill fillup" name="state">
				<option value="" selected="selected">--State--</option>
				<option value="AL">AL</option>
				<option value="AK">AK</option>
				<option value="AR">AR</option>
				<option value="AZ">AZ</option>
				<option value="CA">CA</option>
				<option value="CO">CO</option>
				<option value="CT">CT</option>
				<option value="DC">DC</option>
				<option value="DE">DE</option>
				<option value="FL">FL</option>
				<option value="GA">GA</option>
				<option value="HI">HI</option>
				<option value="IA">IA</option>
				<option value="ID">ID</option>
				<option value="IL">IL</option>
				<option value="IN">IN</option>
				<option value="KS">KS</option>
				<option value="KY">KY</option>
				<option value="LA">LA</option>
				<option value="MA">MA</option>
				<option value="MD">MD</option>
				<option value="ME">ME</option>
				<option value="MI">MI</option>
				<option value="MN">MN</option>
				<option value="MO">MO</option>
				<option value="MS">MS</option>
				<option value="MT">MT</option>
				<option value="NC">NC</option>
				<option value="NE">NE</option>
				<option value="NH">NH</option>
				<option value="NJ">NJ</option>
				<option value="NM">NM</option>
				<option value="NV">NV</option>
				<option value="NY">NY</option>
				<option value="ND">ND</option>
				<option value="OH">OH</option>
				<option value="OK">OK</option>
				<option value="OR">OR</option>
				<option value="PA">PA</option>
				<option value="RI">RI</option>
				<option value="SC">SC</option>
				<option value="SD">SD</option>
				<option value="TN">TN</option>
				<option value="TX">TX</option>
				<option value="UT">UT</option>
				<option value="VT">VT</option>
				<option value="VA">VA</option>
				<option value="WA">WA</option>
				<option value="WI">WI</option>
				<option value="WV">WV</option>
				<option value="WY">WY</option>
			</select>
		</div>



		<input type="text" placeholder="Zip Code:" name="zipcode"
			style="width: 20%">
		<p id="user_add_address" class="error_message"></p>
	</form>
	<button type="button" class="btn cancel"
		onclick="closeAddForm('#addAddressForm')">Cancel</button>
	<button type="submit"
		onclick="confirmAdd('#addAddressFormSubmit', 'address')" class="btn">Submit</button>
</div>

<!--Cancel button click popup-->
<div class="popup-box" id="deleteAddressForm">
	<p class="question">确定删除此地址？</p>
	<input id="" type="hidden" deleteType="address">
	<button type="submit" class="confirm bttn"
		onclick="confirmDelete('#deleteAddressForm')">确认</button>
	<button type="button" class="not-confirm bttn"
		onclick="closepopup('#deleteAddressForm')">取消</button>
</div>