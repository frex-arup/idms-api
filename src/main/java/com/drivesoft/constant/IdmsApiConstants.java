package com.drivesoft.constant;

public class IdmsApiConstants {
    private IdmsApiConstants() {}

    public static final String GET_USER_AUTHORIZATION_TOKEN = "/api/authenticate/GetUserAuthorizationToken?username=%s&password=%s&InstitutionID=%s";

    public static final String GET_ACCOUNT_LIST = "/api/Account/GetAccountList?Token=%s&LayoutID=%s&AccountStatus=%s&PageNumber=%s";
}
