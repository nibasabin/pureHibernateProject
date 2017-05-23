package com.dao.managers;

import com.dao.entity.UserInformationEntity;

public interface UserManager {
	public void addUser(UserInformationEntity userInformationEntity);
	public String getUserFirstLastName(String emailId);
}
